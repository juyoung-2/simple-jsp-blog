<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<section class="bg-light">
        <div class="container-sm">
            <div class="row align-items-center justify-content-between">
                <a class="navbar-brand h1 text-center" href="UserController?cmd=loginPage">로그인</a>
            </div>
            <form class="needs-validation" novalidate>
                <div class="form-group">
               		<label class="form-label mt-4" for="u_id">아이디</label>
					<input type="text" class="form-control" id="u_id" name="u_id" maxlength="12">
                </div>
				<div class="form-group">
					<label class="form-label mt-4" for="mPw">비밀번호</label>
					<input type="password" class="form-control" id="uPw" name="uPw"  maxlength="16">
	                <div class="invalid-feedback" id="loginValidState"></div>
				</div>
				<div class="btn-div">
					<button type="button" id="loginBtn" class="btn btn-primary">로그인</button>
					<button type="button" id="mainBtn" class="btn btn-secondary" onclick="location.href='MainController?cmd=main'">>홈으로</button>
                </div>
                <input type="hidden" name="cmd" value="login">
            </form>
        </div>
    </section>
</body>
<script type="text/javascript" src="js/login.js"></script>
</html>