package kr.co.javastudy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.javastudy.common.MyAppSqlConfig;
import kr.co.javastudy.mapper.BoardMapper;

@WebServlet("/list.do")
public class SelectBoardController extends HttpServlet {

	private BoardMapper mapper;
	public SelectBoardController() {
		mapper = MyAppSqlConfig.getSqlSession().getMapper(BoardMapper.class);
	}
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("board", mapper.selectBoard());
		request.getRequestDispatcher("/WEB-INF/list.jsp").forward(request, response);
	}
}
