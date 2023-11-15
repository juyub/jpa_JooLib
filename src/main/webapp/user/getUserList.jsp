<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<style>
table {
  border-collapse: collapse;
}
td {
  padding: 5px;
}
</style>
</head>
<body>

	<header>
		<jsp:include page="../main/topMenu.jsp" />
	</header>
	
	<section style="display: flex; justify-content: center;">
		<div>
		<form action="searchUser" method="post">
			<select name="searchBy" id="searchBy">
				<option value="name">이름</option>
				<option value="phone">연락처</option>
			</select>
			<input type="text" name="search">
			<input type="submit" value="검색">
		</form>
		<br> <br>
		
		<table border="1">
			<tr>
				<td>no</td>
				<td>아이디</td>
				<td>이름</td>
				<td>연락처</td>
				<td>가입일</td>
			</tr>
			<c:forEach var="user" items="${ userList }">
				<tr>
					<td>${ user.userNo }</td>
					<td><a href="getUserId?userid=${ user.userid }">${ user.userId }</a></td>
					<td>${ user.name }</td>
					<td>${ user.phone }</td>
					<td>
					<fmt:formatDate value="${user.joindate}" pattern="yy/MM/dd" var="formattedDate" />
					${formattedDate}
					</td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</section>

</body>
</html>