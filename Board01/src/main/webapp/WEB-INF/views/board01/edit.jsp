<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항 편집</title>
<link rel="stylesheet" type="text/css" href="resources/css/board.css" />
<script type="text/javascript" src="resources/js/util.js"></script>
<script type="text/javascript">
function checkForm(f) {
	var result = true;
	if(isNull(f.writer, document.getElementById("error_writer"), "작성자를 입력하세요!!")) result = false;
	if(isNull(f.title, document.getElementById("error_title"), "제목을 입력하세요!!")) result = false;
	if(isNull(f.password, document.getElementById("error_password"), "암호를 입력하세요!!")) result = false;
	if(!isEqual(f.password, f.password2, document.getElementById("error_password2"), "암호가 일치하지 않습니다!!")) result = false;
	if(isNull(f.notice, document.getElementById("error_notice"), "내용을 입력하세요!!")) result = false;
	return result;
}
</script>
</head>
<body>
<div id="content">
	<h3>공지 사항 편집</h3>
	<form action="update" method="post" onsubmit="return checkForm(this)">
	<input type="hidden" name="page" value="${curPage }" />
	<input type="hidden" name="no" value="${board.no }" />
	<table>
		<tr>
			<th width="60" align="center">작성자</th>
			<td align="left">
				<input type="text" size="15" name="writer" value="${board.writer }"/>&nbsp;
				<span class="error-text" id="error_writer"></span>
			</td>
		</tr>
		<tr>
			<th width="60" align="center">제목</th>
			<td align="left">
				<input type="text" size="80" name="title" value="${board.title }"/>&nbsp;
				<span class="error-text" id="error_title"></span>
			</td>
		</tr>
		<tr>
			<th width="60" align="center">새 암호</th>
			<td align="left">
				<input type="password" size="15" name="password" />&nbsp;
				<span class="error-text" id="error_password"></span>
			</td>
		</tr>
		<tr>
			<th width="50" align="center">암호확인</th>
			<td align="left">
				<input type="password" size="15" name="password2" />&nbsp;
				<span class="error-text" id="error_password2"></span>
			</td>
		</tr>
		<tr>
			<th width="50" align="center">내용</th>
			<td align="left">
				<textarea rows="20" cols="80" name="notice">${board.notice }</textarea><br /><br />
				<span class="error-text" id="error_notice"></span>
			</td>
	</table>
	<br />
	<div align="center">
		<input type="submit" value="확 인" />&nbsp;
		<input type="reset" value="리 셋" />&nbsp;
		<input type="button" value="목 록" onclick="location.href='index?page=${curPage }'"/>
	</div>
	</form>
</div>
</body>
</html>