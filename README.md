# JSP Board Project

## 1. 개요
본 프로젝트는 JSP / Servlet / MyBatis 기반의 웹 게시판 프로젝트입니다.  
회원 관리, 게시글 CRUD, 댓글 CRUD, 파일 다운로드, 이미지 업로드 기능을 포함하며  
비동기 통신(fetch)을 적극적으로 활용한 구조입니다.

Controller 중심의 MVC 패턴을 기반으로  
View(JSP), Controller(Servlet), Model(DAO / VO / MyBatis)을 분리하여 설계되었습니다.  


<br><br>
**주요 기능**
- 회원가입 / 로그인 (AJAX)
- 게시글 등록 / 수정 / 삭제 / 조회
- 댓글 등록 / 수정 / 삭제 / 목록 조회 (AJAX)
- 파일 다운로드
- 이미지 업로드
- 로그인 상태 체크

---

## 2. MVC 아키텍처

### ASCII 다이어그램
```text
            +--------+
            |  User  |
            +--------+
                 |
                 v
            +--------------+
            |     JSP      |
            | (View Layer) |
            +--------------+
                 |
        fetch / form submit
                 |
                 v
        +----------------------+
        |     Controller       |
        |  (Servlet Layer)     |
        |                      |
        | MainController       |
        | CommController       |
        | UserController       |
        | UserAsyncController  |
        +----------------------+
                 |
                 v
        +----------------------+
        |       Model          |
        |  DAO / VO / MyBatis  |
        |                      |
        | CDao / CVO           |
        | DBService            |
        +----------------------+
                 |
                 v
            +-----------+
            | Database  |
            +-----------+
 ```

## 3. 프로젝트 패키지 구조

``` text
코드 복사
org.joonzis
 ├── controller
 │   ├── MainController.java
 │   ├── CommController.java
 │   ├── UserController.java
 │   ├── UserAsyncController.java
 │   └── ImageUploadController.java
 │
 ├── dao
 │   └── CDao.java
 │
 ├── vo
 │   └── CVO.java
 │
 └── mybatis
     └── config
         ├── DBService.java
         └── sqlmap.xml
```

## 4. 댓글 기능 구조
- 4.1 댓글 DAO (CDao)
```text
insertComm(CVO)     : 댓글 등록
getCommList(m_idx)  : 게시글 기준 댓글 목록 조회
updateComm(CVO)     : 댓글 수정
removeComm(c_idx)   : 댓글 삭제
```

- 4.2 댓글 흐름
```text
JSP
 ↓
JavaScript (fetch)
 ↓
CommController
 ↓
CDao (MyBatis)
 ↓
DB
댓글 등록 / 수정 / 삭제 후 JSON 응답
 ↓
댓글 목록은 비동기 갱신
 ↓
페이지 리로드 없이 화면 반영
```

## 5. MyBatis 설정 구조

- DBService 역할
```
sqlmap.xml 로드
        ↓
SqlSessionFactory 싱글톤 생성
        ↓
DAO 계층에서 SqlSession 제공
        ↓
SqlSessionFactoryBuilder
        ↓
SqlSessionFactory
        ↓
SqlSession
```

## 6. 회원 기능 (AJAX)
 ### 6.1) 회원가입
- 아이디 중복 체크 (AJAX)

- 정규식 기반 입력 검증

- JSON 형태로 서버 전송

- 성공 시 메인 페이지 이동

### 6.2) 로그인
- JSON 기반 로그인 요청

- 성공 시 세션 생성

- 로그인 상태에 따른 페이지 접근 제어

## 7. 게시글 기능

### 주요 기능

- 게시글 등록

- 게시글 수정

- 게시글 삭제

- 게시글 상세 조회

- 페이지네이션 처리

- 로그인 체크

- 글쓰기 버튼 클릭 시 로그인 여부 확인

- 비로그인 상태 → 로그인 페이지 이동

## 8. 파일 기능
- 8.1 파일 다운로드
```
a 태그 클릭 이벤트 가로채기
            ↓
서블릿으로 파일명 전달
            ↓
실제 경로의 파일 다운로드 처리
```
<br>
- 8.2 이미지 업로드
```
FormData 기반 이미지 업로드
            ↓
업로드 완료 후 이미지 경로 반환
            ↓
게시글 내용에 img 태그 자동 삽입
```
## 9. JavaScript 구조 요약
```text
comment.js
 ├── insert_comm()
 ├── showCommList()
 ├── editComm()
 ├── updateComm()
 └── removeComm()

user_join.js
 ├── validateId()
 ├── join()
 └── 입력값 검증 로직

login.js
 └── login()

main.js
 ├── 게시글 이동
 ├── 페이지네이션
 ├── 파일 다운로드
 └── 이미지 업로드
```
## 10. 데이터 흐름 요약
```text
JSP  → JavaScript(fetch)
     → Servlet ControllerW
     → DAO(MyBatis)
     → Database
     → JSON 응답
     → 화면 갱신
 ```    
## 11. 예외 처리

- 댓글 수정/삭제 시 존재하지 않는 번호 → 실패 응답

- 로그인 없이 접근 시 로그인 페이지로 이동

- 입력값 누락 시 alert 처리

- 파일 다운로드 시 파일 미존재 → 예외 처리

## 12. 주의 사항

- fetch 요청 시 GET / POST 혼용

- JSON 응답 구조 통일 필요

- 문자열 파싱 시 XSS 주의

- 이미지 업로드 경로 접근 권한 주의

## 13. 추후 개선 방향

- 댓글 페이징 처리

- 게시글 / 댓글 권한 분리

- REST API 구조 개선

- 프론트엔드 구조 분리

- README에 JSP 화면 구조 추가