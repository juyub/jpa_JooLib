<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>중복ID체크</title>
    <style>
        .center-section {
            display: flex;
            justify-content: center;
            align-items: center;
        }
        a {
		    text-decoration: none; /* 밑줄 제거 */
		}
    </style>
</head>
<body>
    <br/>
    <div class="center-section">
        <c:choose>
            <c:when test="${check}">
               <b>${userID}</b>는 이미 존재하는 ID입니다.<p/>
            </c:when>
            <c:otherwise>
                <b>${userID}</b>는 사용 가능합니다.<p/>
            </c:otherwise>
        </c:choose>
	</div >
	<br>
	<div align="center">
		<a href="#" onclick="self.close()">닫기</a>
	</div>
	
</body >
</html >



