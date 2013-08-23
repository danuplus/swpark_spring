<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>폼 검증 예제</title>
<link rel="stylesheet" type="text/css" href="resources/style.css" />
</head>
<body>
<div id="content">
	<h3>가입</h3> 
	<form:form modelAttribute="user" method="post" action="result">
	<spring:hasBindErrors name="user">
		<c:forEach items="${errors.globalErrors }" var="error">
			<span class="error-text">[<spring:message code="${error.code }" />]</span>
		</c:forEach>
	</spring:hasBindErrors><br />
	<table>
		<tr>
			<th width="60" align="center">이름</th>
			<td><form:input path="name" size="15" />
				<form:errors path="name" cssClass="error-text" />
			</td>
		</tr>
		<tr>
			<th align="center">암호</th>
			<td><form:password path="password" size="15" />
				<form:errors path="password" cssClass="error-text" />
			</td>
		</tr>
		<tr>
			<th align="center">이메일</th>
			<td><form:input path="email" size="30" />
				<form:errors path="email" cssClass="error-text" />
			</td>
		</tr>
		<tr>
			<th align="center">전화</th>
			<td><form:input path="phone" size="30" />
				<form:errors path="phone" cssClass="error-text" />
			</td>
		</tr>		
	</table><br />
	<div align="center">
		<input type="submit" value="확인" />
	</div>
	</form:form>
</div>	
</body>
</html>