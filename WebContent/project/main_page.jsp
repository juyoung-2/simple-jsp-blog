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
<div class="layout">
	<div class="side-menu">
		<div class="top-right-auth">
		    <c:choose>
		        <c:when test="${not empty loginUser}">
		            <div class="login-info">
		                👤 ${loginUser.u_id} 님
		            </div>
		            <button type="button"
		                    onclick="location.href='UserController?cmd=logout'">
		                로그아웃
		            </button>
		        </c:when>
		
		        <c:otherwise>
		            <button type="button"
		                    onclick="location.href='UserController?cmd=loginPage'">
		                로그인
		            </button><br>
		            <button type="button"
		                    onclick="location.href='UserController?cmd=joinPage'">
		                회원가입
		            </button>
		        </c:otherwise>
		    </c:choose>
		</div>
		<div class="btn-group" style="margin-top: 60px;">
			<button id="but" type="button"  onclick="moveInsertPage()" >글쓰기</button><br>
			<button id="but" type="button"  onclick="goMyPage()" >마이페이지</button><br>
		</div>
	</div>
	<div class="content-area">
		<div><h1>Blog</h1></div>
		
	<div class="post_box">
			<c:choose>
				<c:when test="${not empty list}"> 
					<c:forEach varStatus="vs" var="mvo" items="${list }">
					<a href="MainController?cmd=detail&m_idx=${mvo.m_idx }" class="post_link">
						<div class="post">
							<div class="post_title">${mvo.title}</div>
							<div class="post_etc">
								<span class="m_idx">${mvo.m_idx}</span>
								<span class="writer">${mvo.writer}</span>
								<span class="reg_date">${mvo.reg_date}</span>
							</div>
							<div class="post_content">
							  <div class="post_text content-summary"> ${mvo.content}</div>
							</div>
							<div class="hit">${mvo.hit}</div>
						</div>
					</a>
					</c:forEach>
				</c:when>
				
				<c:otherwise>
					 <div class="no-post">게시물이 없습니다.</div>
				</c:otherwise>
			</c:choose>
			
			<!-- page -->
		<div class="page-wrap">
		   	<ul class="page-nation">
		      <c:if test="${pageMaker.prev }">
		         <li class="previous">
		            <a href="${pageMaker.startPage-1 }"> &lt; </a>
		         </li>
		      </c:if>
		      <c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }" step="1">
		         <li>
		            <a href="${num }" class="${pageMaker.cri.pageNum == num ? 'active' : '' }"> ${num } </a>
		         </li>
		      </c:forEach>
		      <c:if test="${pageMaker.next }">
		         <li><a href="${pageMaker.endPage+1 }"> &gt; </a></li>
		      </c:if>
		   </ul>
		</div>
	</div>
	</div>
				</div>
</body>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</html>