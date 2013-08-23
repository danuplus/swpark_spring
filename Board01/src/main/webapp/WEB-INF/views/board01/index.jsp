<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항</title>
<link rel="stylesheet" type="text/css" href="resources/css/board.css" />
</head>
<body>
<div id="content">
	<h3>공지 사항</h3>
	<div align="right">전체 ${maxPage } 페이지 중에 현재 ${curPage } 페이지</div>
	<table>
		<tr>
			<th align="center" width="50">글 번호</th>
			<th align="center">제목</th>
			<th align="center" width="50">조회수</th>
			<th align="center" width="50">작성자</th>
			<th align="center" width="150">작성일</th>
		</tr>
		<c:forEach var="board" items="${boards }" >
		<tr>
			<td align="center">${board.no }</td>
			<td align="left"><a href="view?no=${board.no }&page=${curPage }">${board.title }</a></td>
			<td align="center">${board.ref }</td>
			<td align="center">${board.writer }</td>
			<td align="center"><fmt:formatDate value="${board.wdate }" type="both" pattern="yyyy/MM/dd a hh:mm:ss"/></td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center">
				<c:if test="${curPage > 1 }">
					<a href="index?page=1">&lt;&lt;첫 페이지</a>
				</c:if>&nbsp;&nbsp;
				<c:if test="${curPage > 2 }">
					<a href="index?page=${curPage-1 }">&lt;이전 페이지</a>
				</c:if>&nbsp;&nbsp;
				<c:if test="${curPage < maxPage-1 }">
					<a href="index?page=${curPage+1 }">다음 페이지&gt;</a>
				</c:if>&nbsp;&nbsp;
				<c:if test="${curPage < maxPage }">
					<a href="index?page=${maxPage }">마지막 페이지&gt;&gt;</a>
				</c:if>
			</td>
		</tr>
	</table>
	<br />
	<div align="center">
		<input type="button" value="새 글 쓰기" onclick="location.href='add?page=${curPage}'" />
	</div>
</div>
</body>
</html>