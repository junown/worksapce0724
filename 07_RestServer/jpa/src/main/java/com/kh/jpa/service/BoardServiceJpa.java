package com.kh.jpa.service;

import com.kh.jpa.dto.BoardDto;
import com.kh.jpa.entity.Board;
import com.kh.jpa.entity.Member;
import com.kh.jpa.entity.Tag;
import com.kh.jpa.enums.CommonEnums;
import com.kh.jpa.repository.BoardJPARepository;
import com.kh.jpa.repository.MemberJPARepository;
import com.kh.jpa.repository.TagJPARepository;
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
public class BoardServiceJpa implements BoardService{

    private final MemberJPARepository memberJPARepository;
    private final BoardJPARepository boardJPARepository;
    private final TagJPARepository tagJPARepository;
    private final String FILE_PATH = "C:\\devtool\\upload";

    
    @Override
    @Transactional
    public Long createBoard(BoardDto.Create createDto) throws IOException {
        Member member = memberJPARepository.findById(createDto.getUser_id())
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다"));

        String changeName = null;
        String originName = null;

        if (createDto.getFile() != null && !createDto.getFile().isEmpty()) {
            originName = createDto.getFile().getOriginalFilename();
            changeName = UUID.randomUUID().toString() + "_" + originName;

            File uploadDir = new File(FILE_PATH);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            createDto.getFile()
                    .transferTo(new File(FILE_PATH + changeName));
        }

        Board board = createDto.toEntity();
        board.changeMember(member);
        board.changeFile(originName, changeName);

        if (createDto.getTags() != null && !createDto.getTags().isEmpty()) {
            // tag가 전달됨 ["srping", "jpa"]
            for (String tagName : createDto.getTags()) {
                //tag를 이름으로 조회해서 없으면 새로 만들자
                Tag tag = tagJPARepository.findByTagName(tagName)
                        .orElseGet(() -> tagJPARepository.save(Tag.builder() //없다면 예외발생이 아닌 생성
                                .tagName(tagName)
                                .build()));
                board.addTag(tag);
            }
        }
        boardJPARepository.save(board);
        return board.getBoardId();
    }


    @Override
    public BoardDto.Response getBoardDetail(Long boardId) {
        Board board = boardJPARepository.findById(boardId)
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
        Page<Board> page = boardJPARepository.findByStatus(CommonEnums.Status.Y, pageable);

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
    @Transactional
    public BoardDto.Response updateBoard(Long boardId, BoardDto.Update updateDto) throws IOException {
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        String changeName = board.getChangeName();
        String originName = board.getOriginName();

        if(updateDto.getFile() != null && !updateDto.getFile().isEmpty()) {
            originName = updateDto.getFile().getOriginalFilename();
            changeName = UUID.randomUUID().toString() + "_" + originName;

            File uploadDir = new File(FILE_PATH);
            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            updateDto.getFile().transferTo(new File(FILE_PATH + changeName));
        }

        board.update(
                updateDto.getBoard_title(),
                updateDto.getBoard_content(),
                originName,
                changeName
        );

        if(updateDto.getTags() != null && !updateDto.getTags().isEmpty()) {
            // 기존 태그 모두 제거
            board.getBoardTags().clear();
            
            // 새 태그 추가
            for(String tagName : updateDto.getTags()) {
                Tag tag = tagJPARepository.findByTagName(tagName)
                        .orElseGet(() -> tagJPARepository.save(Tag.builder()
                                .tagName(tagName)
                                .build()));
                board.addTag(tag);
            }
        }

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
    @Transactional
    public void deleteBoard(Long boardId) {
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        // 첨부 파일이 있으면 삭제
        if(board.getChangeName() != null) {
            File file = new File(FILE_PATH + board.getChangeName());
            if(file.exists()) {
                file.delete();
            }
        }

        // 게시글 삭제 (연관된 BoardTag도 자동 삭제됨 - orphanRemoval=true)
        boardJPARepository.delete(board);
    }
}
