package kr.co.javastudy.mapper;

import java.util.List;

import kr.co.javastudy.vo.Board;
import kr.co.javastudy.vo.Comment;

public interface BoardMapper {
	List<Board> selectBoard();
	void insertBoard(Board board);
	Board selectBoardByNo(int no);
	void deleteBoard(int no);
	void updateBoard(Board board);
	
//	댓글
	List<Comment> selectCommentByBoardNo(int no);
	
	void insertComment(Comment comment);
	
	void deleteComment(int commentNo);
	
	void updateComment(Comment comment);	

}
