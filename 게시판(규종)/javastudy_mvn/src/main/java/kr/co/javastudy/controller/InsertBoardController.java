package kr.co.javastudy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.javastudy.common.MyAppSqlConfig;
import kr.co.javastudy.mapper.BoardMapper;
import kr.co.javastudy.vo.Board;

@WebServlet("/insert.do")
public class InsertBoardController extends HttpServlet {
	
	private BoardMapper mapper;
	
	public InsertBoardController() {
		mapper = MyAppSqlConfig.getSqlSession().getMapper(BoardMapper.class);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Board board = new Board();
		board.setContent(request.getParameter("content"));
		board.setTitle(request.getParameter("title"));
		board.setWriter(request.getParameter("writer"));
		
		mapper.insertBoard(board);
		response.sendRedirect("list.do");
	}
}