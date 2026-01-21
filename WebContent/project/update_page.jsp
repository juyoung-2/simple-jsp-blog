<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/update_page.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<h1>수정하기</h1>
		<form method="post" action="MainController" enctype="multipart/form-data" >
			<div class="post_idx">${mvo.m_idx}</div>
			<label for ="title">제목</label>
			<input type="text" id="title" name="title" value="${mvo.title }">
			<div class="post_info">${mvo.writer } . ${mvo.reg_date }</div>
			<label for="content">내용</label>
			<textarea rows="10" cols="80" name="content" >${mvo.content }</textarea>
			<label for="filename"></label>
			<input type="file" id="filename" name="filename">
			<c:if test="${not empty mvo.filename}">
			    <p>현재 파일: 
				    <a href="upload/${mvo.filename}" target="_blank">
				    	${mvo.filename}
				    </a>
			    </p>
			</c:if>
					
			<input type="hidden" name="m_idx" value="${mvo.m_idx }">
			<input type="hidden" name="oldfile" value="${mvo.filename}">
			<input type="hidden" name="cmd" value="update">
			
			<!-- 버튼들 -->
			<div class="post_buttons">
				<button type="submit">완료</button>
				<input type="reset" value="다시 작성">&nbsp;&nbsp;
				<button type="button" onclick="location.href='MainController?cmd=main'">홈으로</button>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="js/main.js"></script>
</html>