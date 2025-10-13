package com.kh.jsp.controller.board;

import java.io.IOException;

import com.kh.jsp.model.vo.Board;
import com.kh.jsp.model.vo.Member;
import com.kh.jsp.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class NomalWriteController
 */
@WebServlet("/insert.bo")
public class InsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		Member loginMember = (Member)session.getAttribute("loginMember");
//		
//		if(loginMember == null) {
//			request.setAttribute("errorMsg", "정상적인 접근이 아닙니다.");
//			request.getRequestDispatcher("views/common/error.jsp").forward(request, response);
//			return;
//		}
//		
//		int categoryNo = Integer.parseInt(request.getParameter("category"));
//		String title = request.getParameter(request.getParameter("title"));
//		String content = request.getParameter("content");
//		int userNo = loginMember.getMemberNo();
//		Board b = Board.insertCreateBoard(categoryNo, title, content, userNo);
//		
//		int result = new BoardService().insertBoard(b);
//		
//		if(result > 0) {
//			request.getSession().setAttribute("alertMsg", "성공적으로 글작성을 완료하였습니다.");
//			response.sendRedirect(request.getContextPath() + "/list.bo");
//		} else { //가입실패
//			request.setAttribute("errorMsg", "글작성에 실패하였습니다.");
//			request.getRequestDispatcher("views/common/error.jsp");
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
