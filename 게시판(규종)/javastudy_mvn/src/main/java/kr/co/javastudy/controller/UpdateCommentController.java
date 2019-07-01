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

@WebServlet("/comment-update.do")
public class UpdateCommentController extends HttpServlet{
	private BoardMapper mapper;
	
	public UpdateCommentController() {
		mapper = MyAppSqlConfig.getSqlSession().getMapper(BoardMapper.class);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String content = request.getParameter("content");
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setCommentNo(commentNo);
		mapper.updateComment(comment);
		
		PrintWriter out = response.getWriter();
		out.println(new Gson().toJson(comment));
		out.close();
	}
	
	
}
