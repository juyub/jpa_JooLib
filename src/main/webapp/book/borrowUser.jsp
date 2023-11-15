<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<c:choose> 
			<c:when test="${ empty borrowUser }"> 
		 		대여중인 도서가 없습니다.
		 	</c:when>
		 	<c:otherwise> 	
				<table border="1">
					<tr >
						<td>no</td>
						<td>제목</td>
						<td>대출일</td>
						<td>대출기일</td>
						<td>반납</td>
					</tr>
					<c:forEach var="borrow" items="${ borrowUser }">
						<tr class="bottom-line">
							<td>${ borrow.borrowNo }</td>
							<td>${ borrow.book.title }</td>
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
									대여중
								</td>
							</c:if>
							<c:if test="${borrow.returndate != null}">
								<td>
									<fmt:formatDate value="${borrow.returndate}" pattern="yy/MM/dd" var="formattedReturnDate" />
									${formattedReturnDate}
								</td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>  
		 </div>
		
	</section>
</body>
</html>