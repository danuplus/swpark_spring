<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>답글 및 덧글형 게시판</title>
<link rel="stylesheet" type="text/css" href="resources/css/board.css" />
<script type="text/javascript" src="resources/js/util.js"></script>
<script type="text/javascript">
function checkForm(f) {
	var result = true;
	if(isNull(f.writer, document.getElementById("error_writer"), "작성자를 입력하세요!!")) result = false;
	if(isNull(f.password, document.getElementById("error_password"), "암호를 입력하세요!!")) result = false;
	if(!isEqual(f.password, f.password2, document.getElementById("error_password2"), "암호가 일치하지 않습니다!!")) result = false;
	if(isNull(f.title, document.getElementById("error_title"), "제목을 입력하세요!!")) result = false;
	if(isNull(f.content, document.getElementById("error_content"), "내용을 입력하세요!!")) result = false;
	return result;
}
</script>
</head>
<body>
<div id="content">
	<h3>${title }</h3>
	<sf:form modelAttribute="board" method="post" action="${job }" onsubmit="return checkForm(this)">
	<input type="hidden" name="page" value=${page } />
	<c:if test="${not empty no }">
	<input type="hidden" name="no" value=${no } />
	</c:if>
	<table>
		<tr>
			<th width="60" align="center">작성자</th>
			<td><sf:input path="writer" name="writer" size="15"/>
				<span class="error-text" id="error_writer"></span>
			</td>
		</tr>
		<tr>
			<th width="60" align="center">암호</th>
			<td><sf:password path="password" name="password" size="15"/>
				<span class="error-text" id="error_password"></span>
			</td>
		</tr>
		<tr>
			<th width="60" align="center">암호확인</th>
			<td><input type="password" name="password2" size="15" />
				<span class="error-text" id="error_password2"></span>
			</td>
		</tr>	
		<tr>
			<th width="60" align="center">제목</th>
			<td><sf:input path="title" name="title" size="80"/>
				<span class="error-text" id="error_title"></span>
			</td>
		</tr>
		<tr>
			<th width="60" align="center">내용</th>
			<td valign="top">
				<sf:textarea path="content" name="content" cssStyle="margin:0px;height:300px;width:700px;"></sf:textarea>
				<span class="error-text" id="error_content"></span>
			</td>
		</tr>
	</table><br />
	<div align="center">
		<input type="submit" value="등 록" />&nbsp;
		<c:if test="${not empty no }">
		<input type="button" value="취 소" onclick="location.href='view?no=${no}&page=${page}&back=y'" />&nbsp;
		</c:if>
		<input type="button" value="목 록" onclick="location.href='index?page=${page}'" />
	</div>
	</sf:form>
</div>
</body>
</html>