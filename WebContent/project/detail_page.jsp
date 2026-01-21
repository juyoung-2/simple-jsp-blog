<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/detail_page.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="loginUser" value="${sessionScope.loginUser}" />
<div>
	<div class="post_container">
		<div class="post_idx">${mvo.m_idx}</div>
		<h1>${mvo.title }</h1> 
		<div class="post_info">
			${mvo.writer } . ${mvo.reg_date }
		</div>
		<div class="post_content">
			<p>${mvo.content }</p>
			<c:if test="${not empty mvo.filename}">
			 	<p>
			 		<a id="download" href="${mvo.filename}">
			 			${mvo.filename}
			 		</a>
				</p>
			</c:if>
		</div>
		<div class="post_buttons">
			 
					<button onclick="location.href='MainController?cmd=updatePage&m_idx=${mvo.m_idx}'">수정</button>
					<button onclick="removeMain(${mvo.m_idx})">삭제</button>
				
			<button id="homeBtn" onclick="location.href='MainController?cmd=main'">홈으로</button>
		</div>
	</div>
	
	<br/><br/>
		<!-- 댓글 입력 폼 -->
		<form class="comm_form" method="post" >
			<div class="comm_row">	
				<label>댓글 작성자</label>
				<input type="text" name="writer">
			</div>
			<div class="comm_row">
				<label>댓글 내용</label>
				<textarea name="content" placeholder="댓글을 입력하세요."></textarea>
			</div>
			<div class="comm_buttons">
				<input type="button" value="등록" onclick="insert_comm(this.form)"/> &nbsp; &nbsp;
				<input type="reset" value="다시 작성"/>  								
				<input type="hidden" name="m_idx" value="${mvo.m_idx }"/>  
				<input type="hidden" name="cmd" value="insertComm"/>
			</div>		
		</form>
		
		<br/><br/>
		<!-- 댓글 출력 폼 -->
		<div id="commBody">
			 <c:forEach var="cvo" items="${commList}">
				<div class="comm_list">
			    	 <div class="comm_header">
			    	 	<span class="comm-c_idx"></span>
				        <span class="comm_writer"></span>
				        <span class="comm_date"></span>
				    </div>
				
				    <div class="comm_content" id="${cvo.c_idx}">
					    ${cvo.content}
					</div>
				    <div class="comm_actions">
					    <c:if test="${loginUser.u_id == cvo.writer}">
						    <button type="button" onclick="editComm(${cvo.c_idx}, '${cvo.content}')">수정</button>
						    <button type="button" onclick="removeComm(${cvo.c_idx})">삭제</button>
						</c:if>  
				    </div>
				</div>
			</c:forEach>
		</div>
</div>		
</body>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/download.js"></script>
<script type="text/javascript" src="js/comm.js"></script>
</html>