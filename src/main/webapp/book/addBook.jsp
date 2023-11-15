<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script>
	function showAlertAndRedirect() {
		alert('접근 권한이 없습니다.');
		location.href = "indexPage.do";
	}
</script>
</head>
<body>
	<header>
		<jsp:include page="../main/topMenu.jsp" />
	</header>
	<section style="display: flex; justify-content: center;">
		<div>
		<h1>도서 등록</h1>
		<form action="addBook" method="post">
			<table border="1">
				<tr>
					<td rowspan="5" align="center">
						<img src="${param.image}" width="100">
						<input type="hidden" name="image" value="${param.image}"/>
					</td>
					<td >
						제목 
					</td>
					<td colspan="3">
						<input type="text" name="title" value="${param.title}"  style="width: 80%;"/>
					</td>
				</tr>
				<tr>
					<td >
						isbn 
					</td>
					<td colspan="3">
						<input type="text" name="isbn" value="${param.isbn}" maxlength="13"/>
					</td>
				</tr>
				<tr>
					<td>
						저자
					</td>
					<td>
						<input type="text" name="author" value="${param.author}" />
					</td>
					<td>
						장르
					</td>
					<td>
						<input type="text" name="category" />
					</td>
				</tr>
				<tr>
					<td>
						출판사
					</td>
					<td>
						<input type="text" name="publisher" value="${param.publisher}"/>
					</td>
					<td>
						보유권수
					</td>
					<td>
						<input type="text" name="totaln" pattern="[0-9]+" />
					</td>
				</tr>
				<tr>
					<td>
						출판년도
					</td>
					<td>
						<input type="text" name="publicationyear" value="${param.publicationyear}" pattern="[0-9]+"/>
					</td>
					<td>
						대출가능권수
					</td>
					<td>
						<input type="text" name="availablen" pattern="[0-9]+" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<textarea name="description" rows="8" cols="100">${param.description}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="5"><input type="submit" value="등록" /></td>
				</tr>
			</table>
		</form>
		</div>
	</section>
	<c:if test="${ login.role != 'admin' }">
		<script>
			showAlertAndRedirect();
		</script>
	</c:if>
</body>
</html>