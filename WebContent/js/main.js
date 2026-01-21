function moveInsertPage(){
	location.href = 'MainController?cmd=insertMainPage';
}

function detail_all(){ 
	//location.href='MainController?cmd=main&pageNum='+pageNum+'&amount=5';
	location.href = 'MainController?cmd=main'
}


function insert(f){
	
	if(!f.writer.value){
		alert("작성자를 입력하세요.");
   				return;
	}
	if(!f.title.value){
		alert("제목을 입력하세요.");
    				return;
	} 
	if(!f.content.value){
		alert("내용을 입력하세요.");
    				return;
	}
		f.action = 'MainController';
		f.submit();
	}
	
const imageInput = document.getElementById("editorImage");
const imageFileName = document.getElementById("imageFileName");
if(imageInput){
	imageInput.addEventListener("change", function () {
		if (this.files.length > 0) {
			imageFileName.textContent = this.files[0].name;
		} else {
			imageFileName.textContent = "선택된 파일 없음";
		}
	});
}
	
function uploadEditorImage() {
	const fileInput = document.getElementById("editorImage");
	const file = fileInput.files[0];

	if (!file) {
		alert("이미지를 선택하세요");
		return;
	}

	const formData = new FormData();
	formData.append("image", file);

	fetch("ImageUploadController", {
		method: "POST",
		body: formData
	})
	.then(res => res.text())
	.then(imgPath => {
		const textarea = document.getElementById("content");
		textarea.value += `\n<img src="${imgPath}" alt="image">\n`;
		fileInput.value = "";
	});
}

function updatePage(){
	location.href = 'MainController?cmd=updatePage';
}


function update(f){
	if(!f.title.value){
		alert("제목을 입력하세요");
		return;
	}
	if(!f.content.value){
		alert("내용을 입력하세요");
		return;
	}
	f.action = 'MainController?cmd=update';
	f.submit();
}

// 게시글 삭제 함수
function removeMain(m_idx){
	 console.log("삭제할 게시글 ID: " + m_idx);
	if(confirm('해당 게시글을 삭제하시겠습니까?')){
		location.href = 'MainController?cmd=remove&m_idx=' + m_idx;
		
	}
	f.action = 'MainController';
	f.submit();
}
// 로그인 안 했을 때 : 글쓰기 버튼 누르면 로그인 페이지로 이동
function moveInsertPage() {
	console.log("글쓰기 버튼 클릭됨");
    fetch("UserAsyncController", {
        method: "POST",
        body: new URLSearchParams({ cmd: "checkLogin" })
    })
    .then(res => res.json())
    .then(data => {
        if (data.result === "success") {
            location.href = "MainController?cmd=insertMainPage";
        } else {
            alert("로그인이 필요합니다.");
            location.href = "UserController?cmd=loginPage";
        }
    });
}

// 페이지 버튼 클릭
document.querySelectorAll('.page-nation li a').forEach( aEle =>{
		aEle.addEventListener('click', (e) => {
			e.preventDefault();
			let pageNum = e.currentTarget.getAttribute('href');
			
			let sendData = 
				'cmd=main&pageNum='+pageNum+'&amount=5';
			location.href = 'MainController?' + sendData;		
		});
	});