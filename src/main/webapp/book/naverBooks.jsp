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
<link rel="stylesheet" type="text/css" href="${contextPath}/css/main.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
        $("#searchForm").on("submit", function(event) {
            event.preventDefault();
            let query = $("input[name='query']").val();

            $.ajax({
                url : "${contextPath}/naverBooks",
                method : "GET",
                data : { query : query },
                success : function(data) {
                    var books = JSON.parse(data);
                    $('#result').empty();
                    $.each(books.items, function(index, book) {
                        var html = '<div>';
                        html += '<h2>' + book.title + '</h2>';
                        html += '<p>' + book.author + '</p>';
                        html += '</div>';
                        $('#result').append(html);
                    });
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
#result {
	margin: 5px;
	background-color:white;
}
</style>
</head>
<body>
	<header>
		<jsp:include page="../main/topMenu.jsp" />
	</header>
	<section>
		<form id="searchForm" action="${contextPath}/naverBooks" method="get">
			<input type="text" name="query" placeholder="도서 검색">
			<button type="submit">검색</button>
		</form>
		<br>
		<c:forEach var="book" items="${books['items']}">
		    <div>
		        <h2>${book['title']}</h2>
		        <p>${book['author']}</p>
		    </div>
		</c:forEach>
	</section>
</body>
</html>
