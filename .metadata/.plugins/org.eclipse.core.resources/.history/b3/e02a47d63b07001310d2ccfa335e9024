<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>한 줄 메모장</title>
<link rel="stylesheet" type="text/css" href="resources/css/memo.css" />
<script type="text/javascript" src="resources/js/util.js"></script>
<script type="text/javascript">
function checkForm(f) {
	var result = true;
	if (isNull(f.writer, document.getElementById("error_writer"), "작성자를 입력해주세요")) result = false;
	if (isNull(f.password, document.getElementById("error_password"), "암호를 입력해주세요")) result = false;
	if (!isEqual(f.password, f.password2, document.getElementById("error_password2"), "암호가 일치하지 않습니다")) result = false;
	if (isNull(f.memo, document.getElementById("error_memo"), "메모를 입력해주세요")) result = false;
	return result;
}
</script>
</head>
<body>
	<div id="content">
		<h3>글 목록</h3>
		<div align="right">
			전체 ${maxPage } 페이지 중에 ${curPage } 페이지
		</div>
		<table>
			<tr>
				<th width="50">글번호</th>
				<th>메모</th>
				<th width="50">작성자</th>
				<th width="150">작성일</th>
			</tr>
			<c:forEach var="memo" items="${memos }">
				<tr>
					<td align="center">${memo.no }</td>
					<td>${memo.memo }&nbsp;&nbsp;<a href="remove?no=${memo.no }&page=${curPage }" class="small">삭제</a></td>
					<td align="center">${memo.writer }</td>
					<td align="center">
						<fmt:formatDate value="${memo.wdate }" type="both" pattern="yyyy/MM/dd a hh:mm:ss"/>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" align="center">
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
		<h3>메모 쓰기</h3>
		<form action="add" method="post" onsubmit="return checkForm(this)">
			<input type="hidden" name="page" value="${curPage }" />
			<table>
				<tr>
					<th align="center" width="80">작성자</th>
					<td>
						<input type="text" name="writer" size="20" />
						<span class="error-text" id="error_writer"></span>
					</td>
				</tr>
				<tr>
					<th align="center">암호</th>
					<td>
						<input type="password" name="password" size="20" />
						<span class="error-text" id="error_password"></span>
					</td>
				</tr>
				<tr>
					<th align="center">암호확인</th>
					<td>
						<input type="password" name="password2" size="20" />
						<span class="error-password2" id="error_password2"></span>
					</td>
				</tr>
				<tr>
					<th align="center">메모</th>
					<td>
						<textarea rows="3" cols="85" name="memo"></textarea><br /><br />
						<span class="error-text" id="error_memo"></span>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="등록" /><br />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>