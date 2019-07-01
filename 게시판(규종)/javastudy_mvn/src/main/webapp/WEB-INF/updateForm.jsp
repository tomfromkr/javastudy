<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>글수정 폼</h1>
	<form action="update.do" method="post">
		<div>제목 : <input type="text" name="title" value="${board.title}"/></div>
		<div>내용 : <input type="text" name="content" value="${board.content}"/></div>
		<div>글쓴이 : ${board.writer}</div>
		<input type="hidden" name="no" value="${board.no}"/>
		<button>입력</button>
	</form>
</body>
</html>