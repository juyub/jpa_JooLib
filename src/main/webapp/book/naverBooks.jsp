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
                	console.log(data);
                	
                    var books = data; // data는 이미 JavaScript 객체이므로 파싱할 필요가 없습니다
                    $('#result').empty();
                    $.each(books.items, function(index, book) {
                        var html = '<table border="1" style="width: 100%;" id="books">';
                        html += '<tr>';
                        html += '<td rowspan="4" width="120" align="center"><img src="' + book.image + '" width="100"></td>';
                        html += '<td>' + book.title + '</td>';
                        html += '</tr><tr>';
                        html += '<td>' + book.author + ' | ' + book.publisher + ' | ' + book.pubdate + '</td>';
                        html += '</tr><tr>';
                        html += '<td>' + book.isbn + '</td>';
                        html += '</tr><tr>';
                        html += '<td>';
                        html += '<form action="addBookPage" method="post">';
                        html += '<input type="hidden" name="image" value="' + book.image + '">';
                        html += '<input type="hidden" name="title" value="' + book.title + '">';
                        html += '<input type="hidden" name="author" value="' + book.author + '">';
                        html += '<input type="hidden" name="publisher"value="' + book.publisher + '">';
                        html += '<input type="hidden" name="publicationyear" value="' + book.pubdate + '">';
                        html += '<input type="hidden" name="isbn" value="' + book.isbn + '">';
                        html += '<input type="hidden" name="description" value="' + book.description + '">';
                        html += '<button type="submit">등록</button>';
                        html += '</form>';
                        html += '</td>';
                        html += '</tr><tr>';
                        html += '<td colspan="2">' + book.description + '</td>';
                        html += '</tr></table>';
                        $('#result').append(html);
                    });
                    /* $.each(books.items, function(index, book) {
                        var html = '<div>';
                        html += '<h2>' + book.title + '</h2>';
                        html += '<p>' + book.author + '</p>';
                        html += '<a href="' + book.link + '">자세히 보기</a>'; // 링크 추가
                        html += '</div>';
                        $('#result').append(html);
                    }); */
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
		<div id="result"></div>
	</section>
</body>
</html>
