package com.kh.jpa.service;

import com.kh.jpa.dto.BoardDto;
import com.kh.jpa.entity.Board;
import com.kh.jpa.entity.BoardTag;
import com.kh.jpa.entity.Member;
import com.kh.jpa.entity.Tag;
import com.kh.jpa.enums.CommonEnums;
import com.kh.jpa.repository.BoardRepository;
import com.kh.jpa.repository.MemberRepository;
import com.kh.jpa.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true) //readOnly=true 데이터를 조회만하고, DML은 하지 않는 트랜잭션
public class BoardServiceImpl implements BoardService {

    private final MemberRepository memberRepository;
    private final TagRepository tagRepository;
    private final BoardRepository boardRepository;
    private final String FILE_PATH = "C:\\devtool\\upload";

    @Override
    @Transactional // readOnly 해제 (데이터 저장을 위해 필요)
    public Long createBoard(BoardDto.Create createDto) throws IOException {
        // 게시글 작성
        // 작성자 찾기 -> 객체관점의 코드를 작성할 것이기 때문에 key를 직접 외래키로 insert하지 않고
        // 작성자를 찾아서 참조관계를 만들어 준다.
        // spring, jpa

        Member member = memberRepository.findById(createDto.getUser_id())
                        .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다"));

        String changeName = null;
        String originName = null;

        if(createDto.getFile() != null && !createDto.getFile().isEmpty()) {
            originName = createDto.getFile().getOriginalFilename();
            changeName = UUID.randomUUID().toString() + "_" + originName;

            File uploadDir = new File(FILE_PATH);
            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            createDto.getFile()
                    .transferTo(new File(FILE_PATH + changeName));
        }

        Board board = createDto.toEntity();
        board.changeMember(member);
        board.changeFile(originName, changeName);

        if(createDto.getTags() != null && !createDto.getTags().isEmpty()) {
            // tag가 전달됨 ["srping", "jpa"]
            for(String tagName : createDto.getTags()) {
                //tag를 이름으로 조회해서 없으면 새로 만들자
                Tag tag = tagRepository.findByTagName(tagName)
                        .orElseGet(() -> tagRepository.save(Tag.builder() //없다면 예외발생이 아닌 생성
                                                                .tagName(tagName)
                                                                .build()));
                board.addTag(tag);
//                BoardTag boardTag = BoardTag.builder()
//                        .tag(tag)
//                        .build();
//
//                boardTag.changeBoard(board);
            }
        }

        boardRepository.save(board);
        return board.getBoardId();
    }

    @Override
    public BoardDto.Response getBoardDetail(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        List<String> tagNames = board.getBoardTags()
                .stream()
                .map(boardTag -> boardTag.getTag().getTagName())
                .toList();


        return BoardDto.Response.of(
                board.getBoardId(),
                board.getBoardTitle(),
                board.getBoardContent(),
                board.getOriginName(),
                board.getChangeName(),
                board.getCount(),
                board.getMember().getUserId(),
                board.getMember().getUserName(),
                board.getCreateDate(),
                tagNames
        );
    }

    @Override
    public Page<BoardDto.Response> getBoardList(Pageable pageable) {

        Page<Board> page = boardRepository.findByStatus(CommonEnums.Status.Y, pageable);

        return page.map(board -> BoardDto.Response.ofSimple(
                board.getBoardId(),
                board.getBoardTitle(),
                board.getOriginName(),
                board.getCount(),
                board.getMember().getUserId(),
                board.getCreateDate()
        ));
    }

    @Override
    @Transactional // readOnly 해제 (데이터 수정을 위해 필요)
    public BoardDto.Response updateBoard(Long boardId, BoardDto.Update updateDto) throws IOException {
        // 1. 게시글 조회 (존재하지 않으면 예외 발생)
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        // 2. 파일 처리 (새로운 파일이 업로드된 경우)
        String changeName = board.getChangeName(); // 기존 파일명 유지
        String originName = board.getOriginName(); // 기존 파일명 유지

        if(updateDto.getFile() != null && !updateDto.getFile().isEmpty()) {
            // 기존 파일이 있다면 삭제 (선택사항 - 필요시 구현)
            // File oldFile = new File(FILE_PATH + board.getChangeName());
            // if(oldFile.exists()) oldFile.delete();

            // 새 파일 저장
            originName = updateDto.getFile().getOriginalFilename();
            changeName = UUID.randomUUID().toString() + "_" + originName;

            File uploadDir = new File(FILE_PATH);
            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            updateDto.getFile()
                    .transferTo(new File(FILE_PATH + changeName));
        }

        // 3. 게시글 정보 수정 (영속 상태의 board를 수정하므로 트랜잭션 완료 시점에 자동으로 UPDATE 쿼리 실행)
        board.putUpdate(
                updateDto.getBoard_title(),
                updateDto.getBoard_content(),
                originName,
                changeName
        );

        // 4. 태그 처리 (기존 태그 제거 후 새 태그 추가)
        if(updateDto.getTags() != null && !updateDto.getTags().isEmpty()) {
            // 기존 태그 모두 제거
            board.clearTags();

            // 새 태그 추가
            for(String tagName : updateDto.getTags()) {
                Tag tag = tagRepository.findByTagName(tagName)
                        .orElseGet(() -> tagRepository.save(Tag.builder()
                                                                .tagName(tagName)
                                                                .build()));
                board.addTag(tag);
            }
        } else {
            // 태그가 전달되지 않았으면 기존 태그 유지 (또는 모두 제거 - 요구사항에 따라)
            // board.clearTags(); // 태그를 모두 제거하려면 이 줄 주석 해제
        }

        // 5. 수정된 게시글 정보를 Response DTO로 변환하여 반환
        List<String> tagNames = board.getBoardTags()
                .stream()
                .map(boardTag -> boardTag.getTag().getTagName())
                .toList();

        return BoardDto.Response.of(
                board.getBoardId(),
                board.getBoardTitle(),
                board.getBoardContent(),
                board.getOriginName(),
                board.getChangeName(),
                board.getCount(),
                board.getMember().getUserId(),
                board.getMember().getUserName(),
                board.getCreateDate(),
                tagNames
        );
    }
}
