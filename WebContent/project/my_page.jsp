<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/page.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="text-align:center; margin-top:20px;">
       <div class="btn-top">
            <button type="button" onclick="location.href='UserController?cmd=logout'">
                로그아웃
            </button>
            <button type="button" onclick="location.href='MainController?cmd=main'">
                홈으로
            </button>
		</div>
        <p style="margin-top: 60px;">
            <button id="but" type="button" onclick="location.href='MainController?cmd=insertMainPage'">
                글쓰기
            </button>
        </p>
    </div>
    <div>
        <h1>My Blog</h1>
    </div>
    <!-- 게시글 목록 -->
    <div class="post_box">
        <c:choose>
            <c:when test="${not empty myList}">
                <c:forEach var="mvo" items="${myList}">
                    <a href="MainController?cmd=detail&m_idx=${mvo.m_idx}" class="post_link">
                        <div class="post">
                            <div class="post_title">${mvo.title}</div>

                            <div class="post_etc">
                                <span class="m_idx">${mvo.m_idx}</span>
                                <span class="writer">${mvo.writer}</span>
                                <span class="reg_date">${mvo.reg_date}</span>
                            </div>

                            <div class="post_content">
                                <p class="content-summary">${mvo.content}</p>
                            </div>

                            <div class="hit">조회수 ${mvo.hit}</div>
                        </div>
                    </a>
                </c:forEach>
            </c:when>

            <c:otherwise>
                <div class="no-post">작성한 게시물이 없습니다.</div>
            </c:otherwise>
        </c:choose>

        <!-- 페이징 -->
        <div class="page-wrap">
            <ul class="page-nation">
                <c:if test="${pageMaker.prev}">
                    <li class="previous">
                        <a href="MainController?cmd=myPage&pageNum=${pageMaker.startPage - 1}">
                            &lt;
                        </a>
                    </li>
                </c:if>

                <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                    <li>
                        <a href="MainController?cmd=myPage&pageNum=${num}"
                           class="${pageMaker.cri.pageNum == num ? 'active' : ''}">
                            ${num}
                        </a>
                    </li>
                </c:forEach>

                <c:if test="${pageMaker.next}">
                    <li>
                        <a href="MainController?cmd=myPage&pageNum=${pageMaker.endPage + 1}">
                            &gt;
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
    
    
    
</body>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</html>