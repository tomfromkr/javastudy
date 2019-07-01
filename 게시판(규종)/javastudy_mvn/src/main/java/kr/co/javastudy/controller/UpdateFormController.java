package kr.co.javastudy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.javastudy.common.MyAppSqlConfig;
import kr.co.javastudy.mapper.BoardMapper;

@WebServlet("/updateForm.do")
public class UpdateFormController extends HttpServlet{
	
	private BoardMapper mapper;
	
	public UpdateFormController() {
		mapper = MyAppSqlConfig.getSqlSession().getMapper(BoardMapper.class);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("board", mapper.selectBoardByNo(Integer.parseInt(request.getParameter("no"))) );
		request.getRequestDispatcher("/WEB-INF/updateForm.jsp").forward(request, response);
	}
	
}
