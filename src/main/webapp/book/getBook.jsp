<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	function checkForm() {
		let borrowForm = document.forms[0];
		let availablen = parseInt("${book.availablen}");
		let borrown = parseInt("${login.borrown}");
		let userno1 = parseInt("${login.userNo}");
		let userno2 = parseInt("${borrow.userNo}");
		
		if (availablen <= 0) {
			alert('대여가능도서가 없습니다.');
			return false;
		}
		if (borrown <= 0) {
			alert('대출가능권수를 초과하였습니다.');
			return false;
		}
		if (userno1 == userno2) {
		      alert('이미 대출하신 도서입니다');
		      return false;
		}

		return true;
	}
</script>
</head>
<body>
	<header>
		<jsp:include page="../main/topMenu.jsp" />
	</header>
	
	<section style="display: flex; justify-content: center;">
	<div>
	
	
	<form action="updateBook" method="post"> 
    <input name="bookNo" type="hidden" value="${ book.bookNo }"> 
    <table border="1"> 
        <tr>
            <td rowspan="5" align="center">
                <img src="${book.image}" width="100">
                <input type="hidden" name="image" value="${book.image}"/>
            </td>
            <td>제목</td> 
            <td colspan="3"><input type="text" name="title" value="${book.title}" style="width: 80%;" ${login.role == 'admin' ? '' : 'readonly'} /></td> 
        </tr>
        <tr>
            <td>isbn</td> 
            <td colspan="3"><input type='text' name='isbn' value='${book.isbn}' maxlength='13' ${login.role == 'admin' ? '' : 'readonly'} /></td>
        </tr>
        <tr>  
            <td>저자</td>  
            <td><input type='text' name='author' value='${book.author}' ${login.role == 'admin' ? '' : 'readonly'} /></td>
			<td >장르</td>
			<td><input type='text' name='category' value='${book.category}' ${login.role == 'admin' ? '' : 'readonly'} /></
		</tr>
		<tr> 
            <td>출판사</td> 
            <td><input type="text" name="publisher" value="${book.publisher}" ${login.role == 'admin' ? '' : 'readonly'} /></td>
            <td>보유권수</td>
            <td><input type="text" name="totaln" value="${book.totaln}" ${login.role == 'admin' ? '' : 'readonly'} /></td>
        </tr>
        <tr>
        	<td>출판연도</td> 
            <td>
            <input type="text" name="publicationyear" value="${book.publicationyear}" ${login.role == 'admin' ? '' : 'readonly'} />
            </td> 
            <td>대여가능권수</td>
            <td>
            <input type="text" name="availablen" value="${book.availablen}" ${login.role == 'admin' ? '' : 'readonly'} />
            </td>
        </tr>
        <tr>
      		<td colspan=5>
      		<textarea name=description rows=10 cols=100 ${login.role == 'admin'? '' :'readonly'}>${ book.description }</textarea>
      		</td>
    	</tr>
        <tr>
            <td>	
            	<c:choose>
           			<c:when test="${book.availablen == 0}">
                		대여불가
            		</c:when>
           			<c:otherwise>
              		  	대여가능
           		 	</c:otherwise>
        		</c:choose>
            </td>
            <td>
        		대여가능권수 : ${book.availablen}권 
        	</td>
        </tr>
        <c:if test="${ login.role == 'admin' }">
            <tr>
                <td>
                    <input type="submit" value="수정" />
                    <button><a href="deleteBook?bookNo=${ book.bookNo }">삭제</a></button>
                </td>
            </tr>
        </c:if>
    </table>
	</form>
	
	<c:if test="${ login.role == 'user' }">
	<br>
    <form action="borrowBook" method="post" onsubmit="return checkForm()">
        <input name="bookNo" type="hidden" value="${ book.bookNo }">
		<input name="userNo" type="hidden" value="${ login.userNo }"> 
        <input name="userNo" type="hidden" value="${ borrow.userNo }">
        <%-- <input name="userno" type="hidden" value="${ login.borrown }">  --%>
        <button type="submit">대출</button>
    </form>
	</c:if>
	</div>
	</section>
	
</body>
</html>
