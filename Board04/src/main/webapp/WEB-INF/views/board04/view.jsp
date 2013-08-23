<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>답글 및 덧글 형 게시판</title>
<link rel="stylesheet" type="text/css" href="resources/css/board.css" />
<script type="text/javascript" src="resources/js/util.js"></script>
<script type="text/javascript">
function checkForm(f) {
	var result = true;
	if(isNull(f.writer, document.getElementById("error_writer"), "작성자를 입력하세요!!")) result = false;
	if(isNull(f.password, document.getElementById("error_password"), "암호를 입력하세요!!")) result = false;
	if(!isEqual(f.password, f.password2, document.getElementById("error_password2"), "암호가 일치하지 않습니다!!")) result = false;
	if(isNull(f.memo, document.getElementById("error_memo"), "덧글을 입력하세요!!")) result = false;
	return result;
}
</script>
</head>
<body>
<div id="content">
	<h3>글 보기</h3>
	<table>
		<tr>
			<th width="60" align="center">작성자</th>
			<td>${board.writer }</td>
		</tr>
		<tr>
			<th align="center">작성일</th>
			<td><fmt:formatDate value="${board.wdate }" type="both" pattern="yyyy/MM/dd a hh:mm:ss"/></td>
		</tr>
		<tr>
			<th align="center">제목</th>
			<td>${board.title }</td>
		</tr>
		<tr>
			<th align="center">내용</th>
			<td height="300" valign="top">${board.content }</td>
		</tr>
	</table><br />
	<div align="center">
		<input type="button" value="답 글" onclick="location.href='answer?no=${board.no}&page=${page}'" />&nbsp;
		<input type="button" value="편 집" onclick="location.href='check?no=${board.no}&page=${page}&job=edit'" />&nbsp;
		<input type="button" value="삭 제" onclick="location.href='check?no=${board.no}&page=${page}&job=remove'" />&nbsp;
		<input type="button" value="목 록" onclick="location.href='index?page=${page }'" />
	</div><br />
	<c:if test="${not empty replies }">
	<h3>덧글 목록</h3>
	<table>
		<tr>
			<th width="60" align="center">작성자</th>
			<th align="center">내용</th>
			<th width="160" align="center">작성일</th>
		</tr>
		<c:forEach items="${replies }" var="reply">
		<tr>
			<td align="center">${reply.writer }</td>
			<td>${reply.memo }
				<span class="small"><a href="check?no=${board.no}&page=${page}&rno=${reply.no}&job=remove_reply">삭제</a></span>
			</td>
			<td align="center">
				<fmt:formatDate value="${reply.wdate }" type="both" pattern="yyyy/MM/dd a hh:mm:ss"/>&nbsp;
			</td>
		</tr>
		</c:forEach>
	</table><br />
	</c:if>
	<h3>덧글 쓰기</h3>
	<sf:form modelAttribute="reply" method="post" action="add_reply" onsubmit="return checkForm(this)">
	<input type="hidden" name="no" value="${board.no }" />
	<input type="hidden" name="page" value="${page }" />
	<table>
		<tr>
			<th width="60">작성자</th>
			<td>
				<sf:input path="writer" name="writer" size="15" />
				<span class="error-text" id="error_writer"></span>
			</td>
		</tr>
		<tr>
			<th>암호</th>
			<td>
				<sf:password path="password" name="password" size="15" />
				<span class="error-text" id="error_password"></span>
			</td>
		</tr>
		<tr>
			<th>암호확인</th>
			<td>
				<input type="password" name="password2" size="15" />
				<span class="error-text" id="error_password2"></span>
			</td>
		</tr>
		<tr>
			<th>덧글</th>
			<td>
				<sf:input path="memo" name="memo" size="100" /><br />
				<span class="error-text" id="error_memo"></span>
			</td>
		</tr>
	</table><br />
	<div align="center">
		<input type="submit" value="등 록" />
	</div>
	</sf:form>
</div>
</body>
</html>