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
	<h3>새 글 쓰기</h3>
	<form action="add" method="post" onsubmit="return checkForm(this)">
	<table>
		<tr>
			<th width="60">작성자</th>
			<td><input type="text" name="writer" size="15" />
				<span class="error-text" id="error_writer"></span>
			</td>
		</tr>
		<tr>
			<th>암호</th>
			<td><input type="password" name="password" size="15" />
				<span class="error-text" id="error_password"></span>
			</td>
		</tr>
		<tr>
			<th>암호확인</th>
			<td><input type="password" name="password2" size="15" />
				<span class="error-text" id="error_password2"></span>
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" size="80" />
				<span class="error-text" id="error_title"></span>
			</td>
		</tr>
		<tr>
			<th height="300">내용</th>
			<td><textarea rows="20" cols="80" name="content"></textarea><br /><br />
				<span class="error-text" id="error_content"></span>
			</td>
		</tr>
	</table><br />
	<div align="center">
		<input type="submit" value="등 록" />&nbsp;
		<input type="reset" value="리 셋" />&nbsp;
		<input type="button" value="목 록" onclick="location.href='index?page=${page}'" />
	</div>
	</form>
</div>
</body>
</html>