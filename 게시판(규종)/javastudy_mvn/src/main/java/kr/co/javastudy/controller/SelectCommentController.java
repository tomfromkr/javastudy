package kr.co.javastudy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.co.javastudy.common.MyAppSqlConfig;
import kr.co.javastudy.mapper.BoardMapper;
import kr.co.javastudy.vo.Comment;

@WebServlet("/comment-select.do")
public class SelectCommentController extends HttpServlet{
	
	private BoardMapper mapper;
	
	public SelectCommentController() {
		mapper = MyAppSqlConfig.getSqlSession().getMapper(BoardMapper.class);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		int no = Integer.parseInt(request.getParameter("no"));
		List<Comment> list = mapper.selectCommentByBoardNo(no);
		PrintWriter out = response.getWriter();
		out.println(new Gson().toJson(list));
		out.close();
	}
	
}
