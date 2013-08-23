<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지 사항 읽기</title>
<link rel="stylesheet" type="text/css" href="resources/css/board.css" />
</head>
<body>
<div id="content">
	<h3>공지 사항 보기</h3>
	<table>
		<tr>
			<th align="center" width="50">제목</th>
			<td align="left">${board.title }</td>
		</tr>
		<tr>
			<th align="center" width="50">작성자</th>
			<td align="left">${board.writer }</td>
		</tr>
		<tr>
			<th align="center" width="50">작성일</th>
			<td align="left"><fmt:formatDate value="${board.wdate }" type="both" pattern="yyyy/MM/dd a hh:mm:ss" /></td>
		</tr>
		<tr>
			<th align="center" width="50">내용</th>
			<td align="left" valign="top" height="400">${board.notice }</td>
		</tr>
	</table>
	<br />
	<div align="center">
		<input type="button" value="목 록" onclick="location.href='index?page=${curPage}'" />&nbsp;
		<input type="button" value="편 집" onclick="location.href='check?no=${board.no}&page=${curPage }&target=edit'" />&nbsp;
		<input type="button" value="삭 제" onclick="location.href='check?no=${board.no}&page=${curPage }&target=remove'" />
	</div>
</div>
</body>
</html>