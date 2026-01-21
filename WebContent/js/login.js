/*------------form 관련 요소들---------------*/
const f = document.forms[0];

/*------------함수---------------*/
document.querySelectorAll("button").forEach(btn => {
	btn.addEventListener('click',()=>{
		let type = btn.id;
		
		if(type === 'loginBtn'){
			login();
		}else if(type === 'mainBtn'){
			location.href = 'UserController?cmd=mainPage';
		}
		
	});
}); 
// 로그인
function login(){

	if(f.u_id.value == '' || f.uPw.value == ''){
		alert("회원 정보를 입력하세요.");
		return;
	}
	
	let formData = new FormData(f);
	let jsonData = JSON.stringify(
				Object.fromEntries(formData.entries())
				);
	
	fetch('UserAsyncController', {
		method : 'POST',
		body : jsonData,
		headers : {
			'Content-type' : 'application/json; charset=UTF-8'
		}
	})
	.then(response => response.json())
	.then(data => {
			if(data.result === 'success'){
				location.href = 'UserController?cmd=mainPage';
			}else{
				alert("아이디 혹은 비밀번호가 일치하지 않습니다.")
			}
			
	})
	.catch(err => console.log(err));
}	
function goMyPage() {
    fetch("UserAsyncController", {
        method: "POST",
        body: new URLSearchParams({ cmd: "myPage" })
    })
    .then(res => res.json())
    .then(data => {
        if (data.result === "success") {
            location.href = "UserController?cmd=myPage";
        } else {
            // 로그인 안 됨 → 로그인 페이지로 이동
            alert("로그인이 필요합니다.");
            location.href = "UserController?cmd=loginPage";
        }
    });
}	