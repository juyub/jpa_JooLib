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
<script>
	function showAlertAndRedirect() {
		alert('접근 권한이 없습니다.');
		location.href = "index";
	}
</script>

</head>
<body>

		<header>
			<jsp:include page="../main/topMenu.jsp" />
		</header>
		<section style="display: flex; justify-content: center;">
			<div>
			<c:choose> 
				<c:when test="${ empty borrowList }"> 
			 		대출중인 도서가 없습니다.
			 	</c:when>
			 	<c:otherwise> 
			<table class="bottom-line" border="1">
				<tr>
					<td>no</td>
					<td>도서</td>
					<td>아이디</td>
					<td>대출일</td>
					<td>만료일</td>
					<td>반납</td>
				</tr>
				<c:forEach var="borrow" items="${ borrowList }">
					<tr class="bottom-line">
						<form action="returnBook" method="post">
							<td>${ borrow.borrowNo }</td>
							<td>${ borrow.book.title }</td>
							<td>
							${ borrow.user.name }(${ borrow.user.userId })
							</td>	
							<td>
							<fmt:formatDate value="${ borrow.borrowdate }" pattern="yy/MM/dd" var="formattedBorrowDate" />
							${formattedBorrowDate}
							</td>
							<td>
							<fmt:formatDate value="${borrow.duedate}" pattern="yy/MM/dd" var="formattedDueDate" />
							${formattedDueDate}
							</td>
							<c:if test="${borrow.returndate == null}">
								<td>
									대출중
								</td>
							</c:if>
							<c:if test="${borrow.returndate != null}">
								<td>
									<fmt:formatDate value="${borrow.returndate}" pattern="yy/MM/dd" var="formattedReturnDate" />
									${formattedReturnDate}
								</td>
							</c:if>
							<c:if test="${borrow.returndate == null}">
								<td>
								<input name="userNo" type="hidden" value="${ borrow.user.userNo }">
								<input name="bookNo" type="hidden" value="${ borrow.book.bookNo }">
								<input name="borrowNo" type="hidden" value="${ borrow.borrowNo }">
								<input	type="submit" value="반납">
								</td>
							</c:if>
						</form>
					</tr>
				</c:forEach>
			</table>
			
				 	</c:otherwise>
			</c:choose>  
			</div>
		</section>
		
	<c:if test="${ login.role != 'admin'}">
		<script>
			showAlertAndRedirect();
		</script>
	</c:if>
</body>
</html>