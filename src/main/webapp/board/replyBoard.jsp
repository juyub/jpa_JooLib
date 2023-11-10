<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>답글쓰기 페이지</title>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/main.css">
</head>
<body>
	<header>
		<jsp:include page="../main/topMenu.jsp" />
	</header>
	<section>
		<h1 style="text-align: center">답글쓰기</h1>
		<form name="frmReply" method="post" action="addReply">
			<input type="hidden" name="boardNO" value="${boardNO}" />
			<table align="center">
				<tr>
					<td align="right">글쓴이&nbsp;</td>
					<td>
						<input type="text" size="5" name="userid" value="${login.userid}" readonly />
					</td>
				</tr>
				<tr>
					<td align="right">글제목&nbsp;</td>
					<td>
						<input type="text" size="58" maxlength="100" name="title" />
					</td>
				</tr>
				<tr>
					<td align="right" valign="top"><br>글내용&nbsp;</td>
					<td>
						<textarea name="content" rows="10" cols="60"
								maxlength="4000"> </textarea>
					</td>
				</tr>
				<tr>
					<td align="right"></td>
					<td>
						<input type=submit value="답글반영하기" />
						<button type="button" onclick="location.href='getBoard?boardNO=${boardNO}' ">취소</button>
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>