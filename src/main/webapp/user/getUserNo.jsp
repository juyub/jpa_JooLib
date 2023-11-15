<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/main.css">
<style>
td {
  padding: 5px;
}
</style>
<script>
	function confirmDelete() {
	    confirm("정말로 탈퇴하시겠습니까?")
	}
	
/* 	function confirmDelete() {
	    if (confirm("정말로 탈퇴하시겠습니까?")) {
	        location.href = "deleteUser.do?userno=${user.userno}";
	    }
	} */
	
	<c:if test="${deleteFailed != null}">
   		alert("대여중인 도서가 있어 탈퇴가 불가합니다");
	</c:if>
	
	function togglePasswordVisibility(id) {
		  var input = document.getElementById(id);
		  if (input.type === "password") {
		    input.type = "text";
		  } else {
		    input.type = "password";
		  }
	}
</script>
<style>
table {
  border-collapse: collapse;
}
</style>
</head>
<body>
	<header>
		<jsp:include page="../main/topMenu.jsp" />
	</header>

	<section style="display: flex; justify-content: center;">
		<div>
		<form action="updateUser" method="post">
			<table border="1">
				<tr>
					<td>회원번호</td>
					<td><input name="userNo" type="text" value="${user.userNo}"
						readonly /></td>
				</tr>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="userId" value="${user.userId}"
						readonly /></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
					<span>
					<input type="password" name="password" id="password"
						value="${user.password}" /> <br>
					<input type="checkbox" onclick="togglePasswordVisibility('password')"> 비밀번호 보기
					</span>	
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="${user.name}"
						${ login.role == 'admin' ? '' : 'readonly'} /></td>
				</tr>
				<tr>
					<td>연락처</td>
					<td><input type="text" name="phone" value="${user.phone}" /></td>
				</tr>
				<tr>
					<td>대출가능권수</td>
					<td><input type="text" name="borrown" value="${user.borrown}"
						${ login.role == 'admin' ? '' : 'readonly'} /></td>
				</tr>
				<tr>
					<td>가입일</td>
					<td>
						<fmt:formatDate value="${user.joindate}" pattern="yy/MM/dd" var="formattedDate" />
       				 	<input type="text" name="joindate" value="${formattedDate}" readonly />
					</td>
				</tr>
				<tr>
					<td>권한</td>
					<td>
					 <input type="text" name="role" value="${user.role}"
						${ login.role == 'admin' ? '' : 'readonly'} />
						<%-- <select name="role" ${ login.role == 'admin' ? '' : 'readonly'}>
          					  <option value="admin" ${user.role == 'admin' ? 'selected' : ''}>admin</option>
           					 <option value="user" ${user.role == 'user' ? 'selected' : ''}>user</option>
       					 </select>	 --%>
				</td>
				</tr>
				<tr>
					<td colspan="2">
					<input type="submit" value="수정" />
					</td>
				</tr>
			</table>
		</form>
		<br>
		<form action="deleteUser" method="post" onsubmit="confirmDelete()">
        <input name="userNo" type="hidden" value="${ user.userNo }"> 
        <button type="submit">탈퇴</button>
    	</form>
		</div>
	</section>
</body>
</html>
