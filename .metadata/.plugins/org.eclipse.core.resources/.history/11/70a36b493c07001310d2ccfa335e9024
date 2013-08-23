<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>암호 확인</title>
<link rel="stylesheet" type="text/css" href="resources/css/memo.css" />
<script type="text/javascript" src="resources/js/util.js"></script>
<script type="text/javascript">
function checkForm(f) {
	var result = true;
	if(isNull(f.password, document.getElementById("error_password"), "암호를 입력하세요")) result = false;
	return result;
}
</script>
</head>
<body>
	<div id="content">
	<h3>암호 확인</h3>
	<form action="remove" method="post" onsubmit="return checkForm(this)">
		<input type="hidden" name="no" value="${no }" />
		<input type="hidden" name="page" value="${page }" />
		<table>
			<tr>
				<th width=50>암호</th>
				<td><input type="password" name="password" />&nbsp;
					<span class="error-text" id="error_password"></span>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="확인" />&nbsp;&nbsp;
					<input type="button" value="취소" onclick="location.href='index?page=${page}'" />
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>