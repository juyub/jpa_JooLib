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
			<c:if test="${ not empty login }">
	    	<td colspan="5">
				<button><a href="addBoardPage">글쓰기</a></button>
	    	</td>
	    	</c:if>
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
            <c:when test="${empty boardList}">
                <tr height="10">
                    <td colspan="5">
                        <p align="center">
                            <b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
                        </p>
                    </td>
                </tr>
            </c:when>
            <c:when test="${!empty boardList}">
                <c:forEach var="board" items="${boardList}" varStatus="boardNum">
                    <tr align="center">
                        <td width="5%">${boardNum.count}</td>
                        <td width="10%">${board.userId}</td>
                        <td align='left' width="35%">
						  <%-- <c:choose>
						    <c:when test='${board.level > 1 }'>
						      <c:forEach begin="1" end="${board.level }" step="1">
						        <span style="padding-left: 25px"></span>
						      </c:forEach>
						      <span style="font-size: 12px;">[답변]</span>
						      <a class='cls1'
						          href="getBoard?id=${board.id}">${board.title}</a>
						    </c:when>
						    <c:otherwise>
						      <span style="padding-right: 30px"></span>
						      <a class='cls1'
						          href="getBoard?id=${board.id}">${board.title }</a>
						    </c:otherwise>
						  </c:choose> --%>
						  <a class='cls1'
						          href="getBoard?id=${board.id}">${board.title}</a>
                        </td>
                        <td width="15%"><fmt:formatDate value="${board.regTime}" pattern="yy/MM/dd HH:mm:ss"/></td>
                        <td width="10%">${board.hit}</td>
                    </tr>
                </c:forEach>
            </c:when>
        </c:choose>
    </table>
	<br>
	<div align="center"	>
        <c:if test="${page.hasPrevious()}"><a href="?page=${page.number - 1}&size=${page.size}">Previous</a></c:if>
        <c:forEach begin="0" end="${page.totalPages - 1}" varStatus="i">
            <c:choose>
                <c:when test="${i.index == page.number}">
                    ${i.index + 1}
                </c:when>
                <c:otherwise>
                    <a href="?page=${i.index}&size=${page.size}">${i.index + 1}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${page.hasNext()}"><a href="?page=${page.number + 1}&size=${page.size}">Next</a></c:if>
    </div>
    <%-- <table align="center">
        <tr>
            <!-- 이전 버튼 -->
            <td>
                <c:choose>
                    <c:when test="${currentPage - 1 > 0}">
                        <a href="getBoardList?pageNo=${currentPage-1}">이전</a>
                    </c:when>
                    <c:otherwise>
                        <span class="disabled">이전</span>
                    </c:otherwise>
                </c:choose>
            </td>

            <!-- 페이지 번호 -->
            <c:forEach begin="${startPage}" end="${endPage}" var="page">
                <td>
                    <c:choose>
                        <c:when test="${page == currentPage}">
                            <span>${page}</span>
                        </c:when>
                        <c:otherwise>
                            <a href="getBoardList?pageNo=${page}">${page}</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </c:forEach>

            <!-- 다음 버튼 -->
            <td>
                <c:choose>
                    <c:when test="${currentPage + 1 <= totalPageCount}">
                        <a href="getBoardList?pageNo=${currentPage+1}">다음</a>
                    </c:when>
                    <c:otherwise>
                        <span class="disabled">다음</span>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table> --%>
	<br>
	<form action="searchBoard" method="post">
	<table align="center">
		<tr>
		<td>
			<input type="text" name="search">
		</td>
		<td>
			 <input type="submit" value="검색">
		</td>
		</tr>
	</table>
	</form>
	
</section>
</body>
</html>
