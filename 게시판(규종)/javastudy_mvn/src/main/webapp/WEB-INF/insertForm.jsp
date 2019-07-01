<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>글쓰기 폼</h1>
	<form action="insert.do" method="post">
		<div>제목 : <input type="text" name="title" /></div>
		<div>내용 : <input type="text" name="content" /></div>
		<div>글쓴이 : <input type="text" name="writer" /></div>
		<button>입력</button>
	</form>
</body>
</html>