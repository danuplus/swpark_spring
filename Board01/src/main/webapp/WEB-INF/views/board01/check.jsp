<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>암호 확인</title>
<link rel="stylesheet" type="text/css" href="resources/css/board.css" />
<script type="text/javascript" src="resources/js/util.js"></script>
<script type="text/javascript">
function checkForm(f) {
	var result = true;
	if(isNull(f.password, document.getElementById("error_password"), "암호를 입력하세요!!")) result = false;
	return result;
}
</script>
</head>
<body>
<div id="content">
	<h3>암호 확인</h3>
	<form action="${target }" method="post" onsubmit="return checkForm(this)">
	<input type="hidden" name="page" value="${curPage }" />
	<input type="hidden" name="no" value="${no }" />
	<table>
		<tr>
			<th width="50">암호</th>
			<td><input type="password" name="password" size="20"/>&nbsp;&nbsp;
				<span class="error-text" id="error_password"></span>
			</td>
		</tr>
	</table><br />
	<div align="center">
		<input type="submit" value="확 인" />&nbsp;
		<input type="button" value="목 록" onclick="location.href='index?page=${curPage }'" />
	</div>
	</form>
</div>
</body>
</html>