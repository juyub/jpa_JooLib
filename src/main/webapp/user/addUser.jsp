<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<%-- <link rel="stylesheet" type="text/css" href="${contextPath}/css/user.css"> --%>
<script>
	function ajaxCheckId(id, callback) {
	  $.ajax({
	    type: "GET",
	    url: "idCheck",
	    data: { userID: id },
	    success: function (response) {
	      if (response.includes("사용 가능")) {
	        // 사용 가능한 ID
	        callback(true);
	      } else {
	        // 중복된 ID
	        callback(false);
	      }
	    },
	    error: function () {
	      alert("아이디 중복 확인 중 오류가 발생했습니다. 다시 시도해 주세요.");
	    },
	  });
	}
	
	function checkForm() {
		let f = document.joinForm;
		if (f.id.value == '') {
			alert('ID를 입력하세요')
			f.id.focus()
			return false;
		}
		if (f.password.value == '') {
			alert('패스워드를 입력하세요')
			f.password.focus()
			return false;
		}
		if (f.password.value != f.passwordCheck.value) {
			alert("비밀번호가 일치하지 않습니다. 다시 확인하시고 입력해 주세요.");
			f.passwordCheck.focus();
			return false;
		}	
		// 중복 ID를 확인하기 위해 AJAX 사용
		ajaxCheckId(f.id.value, function (isAvailable) {
		  if (isAvailable) {
		    // 사용 가능한 ID인 경우 폼 제출
		    f.submit();
		  } else {
		    // 중복된 ID인 경우 경고창 표시
		    alert("존재하는 아이디입니다.");
		  }
		});
		return false; // AJAX가 완료되기를 기다리기 위해 false 반환
		/* return true; */
	}
	
	function formatPhoneNumber(input) {
	    // 입력 내용에서 하이픈을 제거
	    var phoneNumber = input.value.replace(/-/g, '');

	    // 입력 내용이 4자리 이상일 때만 첫 번째 하이픈 추가
	    if (phoneNumber.length >= 4) {
	        phoneNumber = phoneNumber.replace(/(\d{3})(\d+)/, '$1-$2');
	    }

	    // 입력 내용이 7자리 이상일 때만 두 번째 하이픈 추가
	    if (phoneNumber.length >= 8) {
	        phoneNumber = phoneNumber.replace(/(\d{3})-(\d{4})(\d+)/, '$1-$2-$3');
	    }

	    // 변경된 값을 다시 입력 필드에 설정
	    input.value = phoneNumber;
	}
	
	function idCheck(id) {
		var form = document.joinForm;
		if (id == "") {
			alert("아이디를 먼저 입력해주세요!");
			form.userID.focus();
		} else {
			url = "idCheck?userID=" + id;
			window.open(url, "post", "width=300,height=150,left=800,top=400");
		}
	}
	
	function togglePasswordVisibility(userid) {
		  var input = document.getElementById(userid);
		  if (input.type === "password") {
		    input.type = "text";
		  } else {
		    input.type = "password";
		  }
		}
</script>
<style>
html{
	background: url("${contextPath}/image/wall.jpg");
	background-size: 100% 100%;
	background-repeat: no-repeat;
	background-attachment: fixed;
}

body {
	width : 500px;
	padding: 0;
	font-family: verdana, sans-serif;
	font-size: 15px;
	margin: 0 auto;
	margin-top:5%;
}

#user-box {
	position: absolute;
	top: 50%;
	left: 50%;
	padding: 20px;
	transform: translate(-50%, -50%);
	background: rgba(0, 0, 0, .8);
	box-sizing: border-box;
	box-shadow: 0 15px 25px rgba(0, 0, 0, .6);
	border-radius: 10px;
	color: white;
}
.user-box {
	width: 400px;
}

#join-table {
	margin: 5%;
}

.btc {
	display: inline-block;
	color: #fff;
	background-color: #007bff;
	margin: 3%;
	border: 1px solid transparent;
	border-radius: 0.25rem;
	transition: color .15s ease-in-out, background-color .15s ease-in-out,
		border-color .15s ease-in-out, box-shadow .15s ease-in-out;
}

td {
	padding: 5px;
}
</style>

</head>
<body>
	<div id="user-box" class="user-box" align="center">
		<h1>회원가입</h1>
		<form action="addUser" method="post" name="joinForm" >
			<!-- onsubmit="return checkForm()"> -->
			<table id="join-table">
				<tr>
					<td><label for="id">아이디</label></td>
					<td><input type="text" class="form-control" id="id" name="userId"
						pattern="[a-z0-9]+" />
						<span class="tip">* 영어 소문자와 숫자.</span></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="button" value="ID중복확인" class="btc"
						onClick="idCheck(this.form.id.value)"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
					<span>
					<input type="password" class="form-control" id="password"
						name="password" pattern="[a-z0-9]+" required />
					<input type="checkbox" onclick="togglePasswordVisibility('password')"> 비밀번호 보기
					</span><br>
					<span class="tip">* 영어 소문자와 숫자.</span> 
					</td>
				</tr>
				<tr>
					<td>비밀번호<br>확인</td>
					<td>
					<span>
					<input type="password" class="form-control" id="passwordCheck"
							name="passwordCheck" pattern="[a-z0-9]+" required 
							oninput="passwordChecked()" />
					<input type="checkbox" onclick="togglePasswordVisibility('passwordCheck')"> 비밀번호 보기
					</span><br>
					<span class="tip">* 비밀번호를 한번 더 입력하세요.</span>
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" class="form-control" id="name"
						name="name" /></td>
				</tr>
				<tr>
					<td>연락처</td>
					<td><input type="text" class="form-control" id="phone"
						name="phone" maxlength="13"
           				oninput="formatPhoneNumber(this)"
           				/></td>
				</tr>
			</table>
			<button type="submit" class="btn btn-primary">회원가입</button>
		</form>
		<br> 
		<a href="loginPage"> 로그인 </a> &nbsp;&nbsp;&nbsp;
		<a href="index"> 뒤로가기 </a>
	</div>
</body>
</html>