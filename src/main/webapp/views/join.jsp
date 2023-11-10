<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 입력/수정/삭제</title>
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
		<div class="col-md-offset-2 col-md-7">
			<h3 class="text-center">
			Spring MVC5, Spring Data JPA, Oracle CRUD 예제</h3>
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">사원 추가</div>
				</div>
				<div class="panel-body">
					<form:form action="join" cssClass="form-horizontal"
				            method="post" modelAttribute="user">
				
				    <form:hidden path="userno" />
					<div class="form-group">
				        <label for=userid class="col-md-3 control-label">아이디</label>
				        <div class="col-md-9">
				            <form:input path="userid" cssClass="form-control" />
				        </div>
				    </div>
				    <div class="form-group">
				        <label for=password class="col-md-3 control-label">비밀번호</label>
				        <div class="col-md-9">
				            <form:input path="password" cssClass="form-control" />
				        </div>
				    </div>
				    <div class="form-group">
				        <label for="name" class="col-md-3 control-label">사원명</label>
				        <div class="col-md-9">
				            <form:input path="name" cssClass="form-control" />
				        </div>
				    </div>
				    <div class="form-group">
				        <label for="phone" class="col-md-3 control-label">연락처</label>
				        <div class="col-md-9">
				            <form:input path="phone" cssClass="form-control" />
				        </div>
				    </div>
				    <div class="form-group">
				        <div class="col-md-offset-3 col-md-9">
				            <form:button cssClass="btn btn-primary">저장</form:button>
				        </div>
				    </div>
				</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>