<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/insert_page.css">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h2> 새 글 작성</h2>
		<form method="post" enctype="multipart/form-data">
			<table>
				<tbody>
					<tr>
						<th>작성자</th>
						<td>
							<input type="text" name="writer">
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="title"></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<button type="button" onclick="document.getElementById('uploadFile').click();">
								파일 선택
							</button> 
							<input type="file" id="uploadFile" name="filename" style="display:none;">
						</td>
					</tr>
					<tr>
						<th>이미지</th>
						<td>
							<button type="button"
								onclick="document.getElementById('editorImage').click();">
								파일 선택
							</button>
					
							<span id="imageFileName">선택된 파일 없음 </span>
					
							<button id="img-btn" type="button" onclick="uploadEditorImage()">
								이미지 업로드
							</button>
					
							<input type="file"
							       id="editorImage"
							       accept="image/*"
							       style="display:none;">
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea rows="40" cols="90" id="content" name="content" placeholder="나의 일상을 기록해보세요!"></textarea></td>
					</tr>
					<tr>
						<td colspan="2" id="btn">
							<input type="button" value="게시글 저장" onclick="insert(this.form)">&nbsp;&nbsp;
							<input type="reset" value="다시 작성">&nbsp;&nbsp;
							<input type="button" id="homeBtn" value="홈으로" onclick="location.href='MainController?cmd=main'">
							<input type="hidden" name="cmd" value="insertMain">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	
</body>
<script type="text/javascript" src="js/main.js"></script>
</html>