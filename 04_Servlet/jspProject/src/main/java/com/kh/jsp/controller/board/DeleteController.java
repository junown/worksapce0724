package com.kh.jsp.controller.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.kh.jsp.model.vo.Member;
import com.kh.jsp.service.BoardService;
import com.kh.jsp.service.MemberService;

/**
 * Servlet implementation class DeleteController
 */
@WebServlet("/deleteForm.bo")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int boardNo = Integer.parseInt(request.getParameter("bno"));
				
		int result = new BoardService().deleteBoard(boardNo);
		
		if(result == 0) {
			request.setAttribute("errorMsg", "게시글 삭제에 실패하였습니다");
			request.getRequestDispatcher("views/common/error.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("alertMsg", "게시글이 삭제되었습니다");;
			response.sendRedirect(request.getContextPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
