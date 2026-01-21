function insert_comm(f){
	if(!f.writer.value){
		alert(`작성자를 입력하새요`);
		return;
	}
	if(!f.content.value){
		alert(`내용을 입력하새요`);
		return;
	}
	
	
	let formData = new FormData(f);
	
	let serializedData = new URLSearchParams(formData).toString();
	
	
	fetch('CommController?' + serializedData)
		.then(response => response.json())
		.then(data => {
			console.log(data);
			showCommList();
		})
		.catch(err => console.log(err));	
}


function showCommList(){
	let m_idx = new URLSearchParams(location.search).get("m_idx");
	let msg = ``;
	
	let sendData = `cmd=commList&m_idx=${m_idx}`;
	fetch('CommController?' + sendData)
		.then(response => response.json())
		.then(data => {
			let cList = JSON.parse( data.cList );
		
		cList.forEach( cvo => {
		msg += `<div class="comm_list">
					<div class="comm_header">
						<span class="comm-c_idx">${cvo.c_idx}</span>
				        <span class="comm_writer">${cvo.writer}</span>
				        <span class="comm_date">${myTime(cvo.reg_date)}</span>
					</div>
				
					<div class="comm_content" id="${cvo.c_idx}">
				        ${cvo.content}
				    </div>
			
					<div class="comm_actions">
						<button type="button" class="btn-end" onclick="editComm(${cvo.c_idx}, '${cvo.content}')">수정</button>
        				<button type="button" onclick="removeComm(${cvo.c_idx})">삭제</button>
				    </div>
				</div>
		`});	
			if(msg == ''){
				msg += `<div class="comm_list">
							<div class="comm_content" style="text-align:center; color:#888;">
								댓글이 없습니다.
							</div>
						</div>
				`;
			}
			
			document.querySelector("#commBody").innerHTML = msg;
			
		})
		.catch(err => console.log(err));
}
showCommList();

function myTime(unixTimeStamp){
	let myDate = new Date(unixTimeStamp);
	
	let date = myDate.getFullYear() + "-" +
				(myDate.getMonth() + 1) + "-" +
				myDate.getDate();
	return date;
}
// 댓글 수정
function editComm(c_idx, content){
    const commContent = document.getElementById(c_idx); // 댓글 내용 div
    const commList = commContent.parentElement;        // comm_list div
    const commActions = commList.querySelector(".comm_actions"); // 버튼 div

    if(!commContent || !commActions){
        console.error("댓글 div를 찾을 수 없습니다:", c_idx);
        return;
    }

    commContent.innerHTML = `<input type="text" id="edit-${c_idx}" value="${content}">`;

    commActions.innerHTML = `
        <div class="edit-buttons">
            <button class="btn-end" onclick="updateComm(${c_idx})">완료</button>
            <button onclick="showCommList()">취소</button>
        </div>
    `;
}
// 수정 요청
function updateComm(c_idx) {
    const content = document.getElementById("edit-" + c_idx).value;
    let sendData = `cmd=update&c_idx=${c_idx}&content=${encodeURIComponent(content)}`;

    fetch('CommController?' + sendData)
        .then(res => res.json())  // JSON 대신 텍스트로 받기
        .then(data => {
            console.log(data);     // 서버에서 온 문자열 확인
            if (data.result === "success") {
                alert("댓글 수정 완료");
                showCommList();
            } else {
                alert("댓글 수정 실패");
            }
        })
        .catch(err => console.error(err));
}


// 댓글 삭제
function removeComm(c_idx){
	console.log(c_idx);
	if(confirm("댓글을 삭제하시겠습니까?")){
		let sendData = `cmd=remove&c_idx=${c_idx}`;
		fetch('CommController?' + sendData)
		.then(response => response.json())
		.then(data => {
			console.log(data);
			showCommList();
		
	})
	.catch(err => console.log(err));
	} 
	
}