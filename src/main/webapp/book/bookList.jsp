<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/main.css">
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
			<c:if test="${ login.role == 'admin'}">
				<button>
					<a href="naverBooksPage">도서등록</a>
					<!-- <a href="addBookPage">도서등록</a> -->
				</button>
				<br>
				<br>
			</c:if>

			<form action="searchBook" method="post">
			    <input type="text" name="search">
			    <input type="submit" value="검색">
			</form>
			<br> <br>
			<c:choose>
				<c:when test="${ empty bookList }"> 
			 		도서가 없습니다.
			 	</c:when>
				<c:otherwise>
					<table border="1">
						<tr>
							<td>no</td>
							<td>제목</td>
							<td>저자</td>
							<td>출판사</td>
							<td>장르</td>
						</tr>
						<c:forEach var="book" items="${ bookList }">
							<tr>
								<td>${ book.bookNo }</td>
								<td><a href="getBook?bookNo=${ book.bookNo }">${ book.title }</a></td>
								<td>${ book.author }</td>
								<td>${ book.publisher }</td>
								<td>${ book.category }</td>
							</tr>
						</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>
		</div>

		<br>
		<div>
			<table align="center">
				<tr>
					<!-- 이전 버튼 -->
					<td><c:choose>
							<c:when test="${pageNumber - 1 > 0}">
								<a href="getBookList?pageNumber=${pageNumber-1}">이전</a>
							</c:when>
							<c:otherwise>
								<span class="disabled">이전</span>
							</c:otherwise>
						</c:choose></td>
	
					<!-- 페이지 번호 -->
					<c:forEach begin="${1}" end="${totalPage}" var="page">
						<td><c:choose>
								<c:when test="${page == pageNumber}">
									<span>${page}</span>
								</c:when>
								<c:otherwise>
									<a href="getBookList?pageNumber=${page}">${page}</a>
								</c:otherwise>
							</c:choose></td>
					</c:forEach>
	
					<!-- 다음 버튼 -->
					<td><c:choose>
							<c:when test="${pageNumber + 1 <= totalPage}">
								<a href="getBookList?pageNumber=${pageNumber+1}">다음</a>
							</c:when>
							<c:otherwise>
								<span class="disabled">다음</span>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</table>
		</div>
		

	</section>

</body>
</html>