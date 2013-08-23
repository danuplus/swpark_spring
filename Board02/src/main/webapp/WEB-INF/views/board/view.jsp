<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>답글형 게시판</title>
<link rel="stylesheet" type="text/css" href="resources/css/board.css" />
</head>
<body>
<div id="content">
	<h3>글 보기</h3>
	<table>
		<tr>
			<th width="50">작성자</th>
			<td>${board.writer }</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td><fmt:formatDate value="${board.wdate }" type="both" pattern="yyyy/MM/dd a hh:mm:ss"/></td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${board.title }</td>
		</tr>
		<tr>
			<th height="300">내용</th>
			<td valign="top">${board.content }</td>
		</tr>
	</table><br />
	<div align="center">
		<input type="button" value="답 글" onclick="location.href='answer?no=${board.no}&page=${page }'" />&nbsp;
		<input type="button" value="편 집" onclick="location.href='check?no=${board.no }&page=${page }&job=update'" />&nbsp;
		<input type="button" value="삭 제" onclick="location.href='check?no=${board.no }&page=${page }&job=delete'" />&nbsp;
		<input type="button" value="목 록" onclick="location.href='index?page=${page}'" />
	</div>
</div>
</body>
</html>