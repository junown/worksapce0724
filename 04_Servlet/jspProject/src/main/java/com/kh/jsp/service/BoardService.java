package com.kh.jsp.service;

import static com.kh.jsp.common.JDBCTemplate.close;
import static com.kh.jsp.common.JDBCTemplate.commit;
import static com.kh.jsp.common.JDBCTemplate.getConnection;
import static com.kh.jsp.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.jsp.common.vo.PageInfo;
import com.kh.jsp.model.dao.BoardDao;
import com.kh.jsp.model.vo.Attachment;
import com.kh.jsp.model.vo.Board;
import com.kh.jsp.model.vo.Category;
import com.kh.jsp.model.vo.Reply;

public class BoardService {
	
	public int selectAllBoardCount(){
		Connection conn = getConnection();
		
		int listCount = new BoardDao().selectAllBoardCount(conn);
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Board> selectAllBoard(PageInfo pi){
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectAllBoard(conn, pi);
		close(conn);
		
		return list;
	}
	
	public int increaseCount(int boardNo) {
		Connection conn = getConnection();
		
		int result = new BoardDao().increaseCount(conn, boardNo);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public Board selectBoardByBoardNo(int boardNo) {
		Connection conn = getConnection();
		
		Board board = new BoardDao().selectBoardByBoardNo(conn, boardNo);
	
		close(conn);
		return board;
	}
	
	public Attachment selectAttachment(int boardNo) {
		Connection conn = getConnection();
		
		Attachment at = new BoardDao().selectAttachment(conn, boardNo);
		
		close(conn);
		return at;
	}
	
	public ArrayList<Category> selectAllCategory() {
		Connection conn = getConnection();
		
		ArrayList<Category> categroyList = new BoardDao().selectAllCategory(conn);
	
		close(conn);
		return categroyList;
	}
	
	public int updateBoard(Board b, Attachment at) {
		Connection conn = getConnection();
		
		BoardDao bDao = new BoardDao();
		
		int result1 = bDao.updateBoard(conn, b);
		
		int result2 = 1;
		if(at != null) {
			if(at.getFileNo() != 0) {
				result2 = bDao.updateAttachment(conn, at);
			}else {
				result2 = bDao.insertNewAttachment(conn, at);
			}
		}
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result1 * result2;
	}
	
	public int insertBoard(Board b, Attachment at) {
		Connection conn = getConnection();
		
		BoardDao bDao = new BoardDao();
		
		int result = bDao.insertBoard(conn, b);
		
		if(at != null) {
			result *= bDao.insertAttachment(conn, at);
		}
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int deleteBoard(int boardNo) {
		Connection conn = getConnection();
		
		int result = new BoardDao().deleteBoard(conn, boardNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public ArrayList<Reply> selectReplyByBoardNo(int boardNo){
		Connection conn = getConnection();
		
		ArrayList<Reply> list = new BoardDao().selectReplyByBoardNo(conn, boardNo);
		
		close(conn);
		return list;
	}
	
	public int insertReply(Reply r) {
		Connection conn = getConnection();
		
		int result = new BoardDao().insertReply(conn, r);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	public int deleteReply(int replyNo) {
		Connection conn = getConnection();
		
		int result = new BoardDao().deleteReply(conn, replyNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
	}
}
