/*------------form 관련 요소들---------------*/
const f = document.forms[0];
const u_idValidState = document.querySelector("#u_idValidState");
const uPwValidState = document.querySelector("#uPwValidState");
const uPwReValidState = document.querySelector("#uPwReValidState");
let idCk=pwCk=pwReCk=nameCk=emailCk=false;  //검증

/*------------정규식---------------*/
const regExpId = /^[a-z]+[0-9a-z]{3,12}$/;   // 아이디 검증 정규식
const regExpPw = /^[0-9a-zA-Z]{8,16}$/;      // 비밀번호 검증 정규식
const regExpName = /^[가-힣a-zA-Z]{2,12}$/;   // 이름 검증 정규식 
const regExpEmail = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;   // 이메일 검증 정규식

/*------------함수---------------*/
document.querySelectorAll("button").forEach(btn => {
	btn.addEventListener('click',()=>{
		
		let type = btn.id;
		
		if(type === 'duplicateCkBtn'){
			validateId();
		}else if(type === 'joinBtn'){
			join();
		}else if(type === 'resetBtn'){
			f.reset();
		}else if(type === 'mainBtn'){
			location.href = `UserController?cmd=mainPage`;
		}
		
	});
});
// 데이터 검증 완료 함수
function validated(inputTarget, resultState, comment){
   inputTarget.classList.add("is-valid");
   inputTarget.classList.remove("is-invalid");
   if(resultState){
      resultState.classList.add("valid-feedback");
      resultState.classList.remove("invalid-feedback");
      comment ? 
         resultState.innerHTML = comment : 
            resultState.innerHTML = '' ;
   }
}
// 데이터 검증 미완료 함수
function invalidate(inputTarget, resultState, comment){
   inputTarget.classList.remove("is-valid");
   inputTarget.classList.add("is-invalid");
   if(resultState){
      resultState.classList.remove("valid-feedback");
      resultState.classList.add("invalid-feedback");
      comment ? 
         resultState.innerHTML = comment : 
            resultState.innerHTML = '' ;
   }
}
// 검증 스타일 초기화 함수
function Initialization(inputTarget, resultState){
   inputTarget.classList.remove("is-valid");
   inputTarget.classList.remove("is-invalid");
   if(resultState){
      resultState.classList.remove("valid-feedback");
      resultState.classList.remove("invalid-feedback");
      resultState.innerHTML = '';
   }
}
// 아이디 중복 확인
function validateId(){
	let target = f.u_id;
	
	if(target.value == ''){
		Initialization(target, u_idValidState);
		alert("아이디를 입력하세요.");
		idCk = false;
		return;
	}else if(!regExpId.exec(target.value)){
		invalidate(target, u_idValidState, "형식에 맞지 않은 아이디입니다.");
		idCk = false;
		return;
	}
	
	const params = {
		cmd : 'validateId',
		u_id : target.value
	};
	
	const queryString = Object.keys(params)
			.map(key => encodeURIComponent(key) + "=" +
				encodeURIComponent(params[key])
				)
				.join('&');
	
	fetch(`UserAsyncController?${queryString}`)
		.then(response => response.json())
		.then(data => {
			if(data.result == 1){
				invalidate(target, u_idValidState, "중복된 아이디 입니다.");
				idCk = false;
			}else{
				validated(target, u_idValidState, "사용 가능한 아이디 입니다.");
				idCk = true;
			}
		})
		.catch(err => console.log(err))
}

// 비밀번호 입력 검증
f.uPw.addEventListener('keyup', e =>{
	let target = e.currentTarget;
	
	if(target.value == ''){
		Initialization(target, uPwValidState);
		pwCk = false;
	}else if(regExpPw.exec(target.value)){
		validated(target, uPwValidState);
		pwCk = true;
	}else{
		invalidate(target, uPwValidState, "올바른 형식이 아닙니다.");
		pwCk = false;
	}
});

// 비밀번호 확인 입력 검증
f.uPwRe.addEventListener('keyup', e =>{
	let target = e.currentTarget;
	
	if(target.value == ''){
		Initialization(target, uPwReValidState);
		pwReCk = false;
	}else if(target.value === f.uPw.value){
		validated(target, uPwReValidState);
		pwReCk = true;
	}else{
		invalidate(target, uPwReValidState, "비밀번호가 일치하지 않습니다.");
		pwReCk = false;
	}
});

// 이름 입력 검증
f.uName.addEventListener('input', e =>{
	let target = e.currentTarget;
	
	if(target.value == ''){
		Initialization(target);
		nameCk = false;
	}else if(regExpName.exec(target.value)){
		validated(target);
		nameCk = true;
	}else{
		invalidate(target);
		nameCk = false;
	}
});

// 이메일 입력 검증
f.uEmail.addEventListener('keyup', e =>{
	let target = e.currentTarget;
	
	if(target.value == ''){
		Initialization(target);
		emailCk = false;
	}else if(regExpEmail.exec(target.value)){
		validated(target);
		emailCk = true;
	}else{
		invalidate(target);
		emailCk = false;
	}
});

// 회원가입
function join(){
	// post 방식 - json 전달
	// 1. 데이터 검증 완료
	if(!idCk || !pwCk || !pwReCk || !nameCk || !emailCk){
		alert('입력 내용을 확인해주세요');
		return;
	}
	
	// 2. form 데이터들을 json으로 변경
	let formData = new FormData(f);
	let jsonData = JSON.stringify(
				Object.fromEntries(formData.entries())
				);
	console.log(jsonData);
	
	// 3. fetch로 데이터 통신
	fetch('UserAsyncController', {
		method : 'POST',
		body : jsonData,
		headers : {
			'Content-type' : 'application/json; charset=UTF-8'
		}
	})
	.then(response => response.json())
	.then(data => {
			console.log(data);
			
			if(data.result == 1){
				alert("회원가입에 성공했습니다.");
				// 메인으로 이동(index.jsp)
				location.href = 'UserController?cmd=mainPage';
			}else{
				alert("회원가입이 실패했습니다.");
			}
			
			
		})
	.catch(err => console.log(err));
}

