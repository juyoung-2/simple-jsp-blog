// 첨부파일 다운로드
const aEle = document.querySelector("#download");
if(aEle != null){
	aEle.addEventListener('click', (e)=>{

	// 기본 이벤트 방지 함수
	e.preventDefault();
	
	// a 태그에 담긴 href 속성에서 파일 이름 가져오기
	// realPath에 있는 파일을 다운받는 것이 목적
	let filename = e.target.getAttribute("href");
			// e.target = this랑 같음 a태그
			
	// 서블릿으로 전달 할 퀴리스트링 작성
	let sendData = `cmd=download&filename=${filename}`;
	
	//서블릿으로 전송
	location.href = `MainController?${sendData}`;
	
});
}









	
