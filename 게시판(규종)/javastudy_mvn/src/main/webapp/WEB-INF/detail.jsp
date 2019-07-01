<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>글 목록</h1>
	<table>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>등록날짜</th>
			<th>내용</th>
			<th>작성자</th>
		</tr>
		<tr>
			<td>${board.no}</td>
			<td>${board.title}</td>
			<td><fmt:formatDate value="${board.regDate}" pattern="yy.MM.dd HH:mm:ss" /></td>
			<td>${board.content}</td>
			<td>${board.writer}</td>
		</tr>
	</table>
	
	<a href="<c:url value='updateForm.do?no=${board.no}'/>">수정</a>
	<a href="<c:url value='delete.do?no=${board.no}'/>">삭제</a>
	<a href="<c:url value='list.do'/>">목록</a>
	
	<h3>댓글</h3>
	
	<div>
		<form id="commentRegistForm">
			<input type="hidden" name="no" value="${board.no}" />
			<table width="70%">
			<tr>
				<td>작성자 : <input type="text" name="writer" /></td>
				<td>댓글 내용 : <textarea name="content" rows="2" cols="60"></textarea></td>
				<td><button type="button" id="insertCommentBtn">등록</button></td>
			</tr>	
			</table>
		</form>
	</div>	

	<div id="commentList">
	  <input type="hidden" value="" id="preCommentNo"/>
	  <table width="80%" border="1" id="commentTable">
	  </table>
	</div>
	<script
	  src="https://code.jquery.com/jquery-3.4.1.min.js"
	  crossorigin="anonymous">
	</script>
	<script>
		const boardNo = ${board.no};
	</script>
	<script src="<c:url value='/resources/js/detail.js'/>"></script>
</body>
</html>