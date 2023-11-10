<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/main.css">
<!-- jQuery 라이브러리 추가 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- AJAX를 사용한 검색 기능 구현 -->
<script>
	$(document).ready(function() {
		$("#searchForm").on("submit", function(event) {
			event.preventDefault();
			let query = $("input[name='query']").val();

			$.ajax({
				url : "${contextPath}/naverBooks",
				method : "GET",
				data : {
					query : query
				},
				success : function(data) {
					$("#books").html(data);
				},
				error : function(xhr, status, error) {
					console.error("Error occurred: " + error);
				}
			});
		});
	});
</script>
<style>
table {
	border-collapse: collapse;
}

td {
	padding: 5px;
}

#books {
	margin: 5px;
	background-color:white;
}
/* a {
  text-decoration: none;
  color: inherit;
}
section {
  margin-left: 5%;
  margin-right: 5%;
  padding: 20px;
} */
</style>
</head>
<body>

	<header>
		<jsp:include page="../main/topMenu.jsp" />
	</header>

	<section>
		<form action="naverBooks" method="get">
			<input type="text" name="query" placeholder="도서 검색">
			<button type="submit">검색</button>
		</form>
		<br>
		<c:forEach var="book" items="${books}">
			<table border="1" style="width: 100%;" id="books">
				<tr>
					<td rowspan="4" width="120" align="center"><img src="${book.image}" width="100"></td>
					<td>${book.title}</td>
				</tr>
				<tr>
					<td >${book.author} | ${book.publisher}
					<fmt:formatDate value="${book.pubdate}" pattern="yyyy/MM/dd" var="fm_pubdate" />
					| ${fm_pubdate}
					</td>
				</tr>
				<tr>
					<td>
						${book.isbn}
					</td>
				</tr>
				<tr>
					<td>
						<form action="addBookPage" method="post">
	                    <input type="hidden" name="image" value="${book.image}">
	                    <input type="hidden" name="title" value="${book.title}">
	                    <input type="hidden" name="author" value="${book.author}">
	                    <input type="hidden" name="publisher"value="${book.publisher}">
	                    <fmt:formatDate value="${book.pubdate}" pattern="yyyy" var="pubyear" />
	                    <input type='hidden' name='publicationyear' value='${pubyear}'>
	                    <input type='hidden' name='isbn' value='${book.isbn}'>
	                    <input type='hidden' name='description' value='${book.description}'>
                    
					    <button type='submit'>등록</button> <!-- style='float: right;' -->
					</form>
					</td>
				</tr>
				<tr>
					<td colspan="2">${book.description}</td>
				</tr>
			</table>
		</c:forEach>
	</section>

</body>
</html>
