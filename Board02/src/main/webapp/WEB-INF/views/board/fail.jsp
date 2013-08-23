<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>답글형 게시판</title>
<link rel="stylesheet" type="text/css" href="resources/css/board.css" />
</head>
<body>
<div id="content">
	<h3>데이터베이스 오류</h3>
	<table>
		<tr>
			<th width="50">원인</th>
			<td>암호가 일치하지 않습니다.</td>
		</tr>
	</table><br />
	<div align="center">
		<input type="button" value="목 록" onclick="location.href='index?page=${page }'" />&nbsp;
		<input type="button" value="재시도" onclick="location.href='check?no=${no }&page=${page }&job=${job }'" />
		
	</div>
</div>
</body>
</html>