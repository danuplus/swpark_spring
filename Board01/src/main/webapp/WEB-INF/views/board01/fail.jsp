<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>암호 오류</title>
<link rel="stylesheet" type="text/css" href="resources/css/board.css" />
</head>
<body>
	<div id="content">
		<table>
			<tr>
				<th>원인</th>
			</tr>
			<tr>
				<td>암호가 일치하지 않습니다.</td>
			</tr>
		</table><br />
		<div align="center">
			<input type="button" value="확인" onclick="location.href='check?no=${no}&page=${curPage}&target=${target}'" />
		</div>
	</div>
</body>
</html>