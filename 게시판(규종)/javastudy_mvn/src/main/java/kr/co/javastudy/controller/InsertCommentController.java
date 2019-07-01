package kr.co.javastudy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.co.javastudy.common.MyAppSqlConfig;
import kr.co.javastudy.mapper.BoardMapper;
import kr.co.javastudy.vo.Comment;

@WebServlet("/comment-insert.do")
public class InsertCommentController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private BoardMapper mapper;
	
	public InsertCommentController() {
		mapper = MyAppSqlConfig.getSqlSession().getMapper(BoardMapper.class);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setNo(no);
		comment.setWriter(writer);
		mapper.insertComment(comment);
		
		PrintWriter out = response.getWriter();
		out.println(new Gson().toJson(comment));
		out.close();
	}
	
}
