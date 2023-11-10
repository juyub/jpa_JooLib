<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사원 목록 보기</title>
<%@ page isELIgnored="false"%>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-1.11.1.min.js"
	integrity="sha256-VAvG3sHdS5LqTT+5A/aeq/bZGa/Uj04xKxY8KM/w9EE="
	crossorigin="anonymous"></script>
</head>

<body>
	<div class="container">
		<div class="col-md-offset-1 col-md-10">

			<h3 class="text-center">Spring MVC5, Spring Data JPA, Oracle
				CRUD 예제</h3>

			<hr />
			<br />
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">
						사원 목록보기 
						<input type="button" value="사원추가"
							onclick="window.location.href='showForm'; return false;"
							class="btn btn-primary" />
					</div>
				</div>

				<div class="panel-body">
					<table class="table table-striped table-bordered">
						<tr>
							<th>아이디</th>
							<th>비번</th>
							<th>이름</th>
							<th>연락처</th>
							<th>대여가능</th>
							<th>가입일</th>
							<th>권한</th>
							<th>수정/삭제</th>
						</tr>

						<!-- 사원목록로프 -->
						<c:forEach var="user" items="${users}">
							<!-- 수정링크  -->
							<c:url var="updateLink" value="/updateUser">
								<c:param name="userno" value="${user.userno}" />
							</c:url>
							<!-- 삭제링크 -->
							<c:url var="deleteLink" value="/delete">
								<c:param name="userno" value="${user.userno}" />
							</c:url>

							<tr>
								<td>${user.userid}</td>
								<td>${user.password}</td>
								<td>${user.name}</td>
								<td>${user.phone}</td>
								<td>${user.borrown}</td>
								<td>${user.joindate}</td>
								<td>${user.role}</td>
								<td><a href="${updateLink}">Update</a>
									| <a href="${deleteLink}"
									onclick="if (!(confirm('삭제하시겠습니까?'))) return false">Delete</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>

</body>

</html>