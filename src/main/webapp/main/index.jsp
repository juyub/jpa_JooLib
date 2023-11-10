<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/main.css">
<style>
td {
  padding: 5px;
}
</style>
</head>
<body>
	<header>
		<jsp:include page="topMenu.jsp" />
	</header>
	<section style="display: flex; justify-content: center;  align-items: center;">
	메인페이지입니다
	</section>
</body>
</html>