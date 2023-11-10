<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록창</title>
<style>
table {
    border-collapse: collapse;
}
td {
    padding: 5px;
}
.cls1 {
	text-decoration: none;
}
</style>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/main.css">
</head>
<body>
    <header>
        <jsp:include page="../main/topMenu.jsp" />
    </header>
<section>

	<table align="center" border="0" width="80%">
		<tr>
	    	<td colspan="5">
				<button><a href="addBoard">글쓰기</a></button>
	    	</td>
    	</tr>
	</table>
	<br>
    <table align="center" border="1" width="80%">
    	
        <tr height="10" align="center" bgcolor="lightgreen">
            <td></td>
            <td>작성자</td>
            <td>제목</td>
            <td>작성일</td>
            <td>조회수</td>
        </tr>
        <c:choose>
            <c:when test="${empty searchList}">
                <tr height="10">
                    <td colspan="5">
                        <p align="center">
                            <b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
                        </p>
                    </td>
                </tr>
            </c:when>
            <c:when test="${!empty searchList}">
                <c:forEach var="search" items="${searchList}" varStatus="boardNum">
                    <tr align="center">
                        <td width="5%">${searchNum.count}</td>
                        <td width="10%">${search.id}</td>
                        <td align='left' width="35%">
						  <c:choose>
						    <c:when test='${search.level > 1 }'>
						      <c:forEach begin="1" end="${search.level }" step="1">
						        <span style="padding-left: 25px"></span>
						      </c:forEach>
						      <span style="font-size: 12px;">[답변]</span>
						      <a class='cls1'
						          href="getBoard?boardNO=${search.boardNO}">${search.title}</a>
						    </c:when>
						    <c:otherwise>
						      <span style="padding-right: 30px"></span>
						      <a class='cls1'
						          href="getBoard?boardNO=${search.boardNO}">${search.title }</a>
						    </c:otherwise>
						  </c:choose>
                        </td>
                        <td width="15%"><fmt:formatDate value="${search.regtime}" pattern="yy/MM/dd HH:mm:ss"/></td>
                        <td width="10%">${search.hit}</td>
                    </tr>
                </c:forEach>
            </c:when>
        </c:choose>
    </table>
	<br>
    <table align="center">
        <tr>
            <!-- 이전 버튼 -->
			<td>
			    <c:choose>
			        <c:when test="${currentPage - 1 > 0}">
			            <a href="searchBoard?pageNo=${currentPage-1}">이전</a>
			        </c:when>
			        <c:otherwise>
			            <span class="disabled">이전</span>
			        </c:otherwise>
			    </c:choose>
			</td>
			
			<!-- 페이지 번호 -->
			<c:forEach begin="${startPage}" end="${endPage}" var="pageNum">
			    <td>
			        <c:choose>
			            <c:when test="${pageNum == currentPage}">
			                <span>${pageNum}</span>
			            </c:when>
			            <c:otherwise>
			                <a href="searchBoard?pageNo=${pageNum}">${pageNum}</a>
			            </c:otherwise>
			        </c:choose>
			    </td>
			</c:forEach>
			
			<!-- 다음 버튼 -->
			<td>
			    <c:choose>
			        <c:when test="${currentPage + 1 <= totalPageCount}">
			            <a href="searchBoard?pageNo=${currentPage+1}">다음</a>
			        </c:when>
			        <c:otherwise>
			            <span class="disabled">다음</span>
			        </c:otherwise>
			    </c:choose>
			</td>
        </tr>
    </table>
	
	<form action="searchBoard" method="get">
	    <input type="text" name="search" value="${search}"> 
	    <input type="submit" value="검색">
	</form>
	
</section>
</body>
</html>
