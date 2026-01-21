<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/join.css">
</head>
<body>
	<section class="bg-light">
        <div class="container">
               <h1 style="text-align: center; margin-bottom: 20px">회원 가입</h1> 
          
            <form id="joinForm" novalidate>
            <!-- 아이디 -->
                <div class="form-group">
               		<label for="u_id">아이디</label>
              			<div class="input-group">
						<input type="text" id="u_id" name="u_id" placeholder="영어 소문자로 시작, 영어 + 숫자 조합 3~12 글자" maxlength="12">
						<button  type="button" id="duplicateCkBtn" style="padding:8px 12px;">중복확인</button>
					    </div>
						<div class="invalid-feedback" id="u_idValidState"></div>
                </div>
                
             <!-- 비밀번호 -->
				<div class="form-group">
					<label for="uPw">비밀번호</label><br>
					<input type="password" id="uPw" name="uPw" placeholder="영어 소문자, 대문자, 숫자 8~16 글자">
					<div class="invalid-feedback" id="uPwValidState"></div>
				</div>
			<!-- 비밀번호 재확인 -->
				<div class="form-group">
					<label for="uPwRe">비밀번호 재확인</label> 
					<input type="password"  id="uPwRe" name="uPwRe">
					<div class="invalid-feedback" id="uPwReValidState"></div>
				</div>
			<!-- 이름 -->
                <div class="form-group">
               		<label for="uName" >이름</label>
                    <input type="text" id="uName" name="uName" maxlength="12" placeholder="한글, 영어 2~12 글자">
                </div>
				<div class="form-group">
					<label for="uEmail">이메일</label>
					<input type="text" class="form-control" id="uEmail" name="uEmail" placeholder="@ 포함 전체 이메일">
				</div>
				<div class="btn-div">
					<button type="button" id="joinBtn">회원가입</button>
					<button type="button" id="resetBtn">다시작성</button>
					<button type="button" id="mainBtn" onclick="location.href='MainController?cmd=main'">홈으로</button>
                </div>
                <input type="hidden" name="cmd" value="join">
            </form> 
        </div>
    </section>
</body>
<script type="text/javascript" src="js/join.js"></script>
</html>