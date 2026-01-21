package org.joonzis.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.joonzis.service.UserService;
import org.joonzis.service.UserServiceImpl;
import org.joonzis.vo.UVO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet("/UserAsyncController")
public class UserAsyncController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UserAsyncController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 쿼리 스트링으로 들어오는 cmd를 저장하기 위한 방식
		String cmd = request.getParameter("cmd");
		
		// 비동기를 처리하기 위한 내용물
		ObjectMapper objectMapper = null; // json과 java객체들을 변환할 때 사용
		String jsonString = null;  // json으로 직렬화 된 데이터를 담는 용도
		PrintWriter out = response.getWriter();  // 응답 객체
		JSONObject obj = new JSONObject();  // 응답 객체에 보내줄 객체
		StringBuilder sb = new StringBuilder();  // json 데이터를 저장하기 위한 객체
		BufferedReader reader = request.getReader();  // json데이터가 들어온 객체
		String line;
		
		
		// 1. json 데이터를 StringBuilder에 저장
		while((line = reader.readLine()) != null) {
			sb.append(line);
		}
		
		// 2. json데이터 자바 객체로 저장
		if(!sb.toString().isEmpty()) {
			try {
				obj = (JSONObject)new JSONParser()
									.parse(sb.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(cmd == null) {
			cmd = (String)obj.get("cmd");
		}
		
		UserService uservice = new UserServiceImpl();
		UVO uvo = null;
		
		HttpSession session = request.getSession();		

		
		switch (cmd) {
		case "validateId":
			String u_id = request.getParameter("u_id");
			obj.put("result", uservice.validateId(u_id));
			break;
			
		case "join":
			// 아이디, 비밀번호, 이름, 이메일
			// vo에 담아 mapper까지 전달
			uvo = new UVO();
			uvo.setU_id((String)obj.get("u_id"));
			uvo.setuPw((String)obj.get("uPw"));
			uvo.setuName((String)obj.get("uName"));
			uvo.setuEmail((String)obj.get("uEmail"));
			
			
			obj.clear();
			obj.put("result", uservice.insertUser(uvo));
			break;
			
		case"login":
			uvo = new UVO();
			uvo.setU_id((String)obj.get("u_id"));
			uvo.setuPw((String)obj.get("uPw"));
			
			UVO returnVO= uservice.doLogin(uvo);
			obj.clear();
			
			 if(returnVO != null){
				 session.setAttribute("loginUser", returnVO);
				 obj.put("result", "success");
				}else {
					obj.put("result", "fail");
				}
			break;
			
		case"myPage":
			UVO loginUser = (UVO)session.getAttribute("loginUser");
			 if (loginUser != null) {
			        // 로그인 되어 있으면 정보 JSON으로 보내기 (AJAX용)
			        obj.put("u_id", loginUser.getU_id());
			        obj.put("uName", loginUser.getuName());
			        obj.put("result", "success");
			    } else {
			        // 로그인 안 됐으면 fail
			        obj.put("result", "fail");
			    }
			    break;
			
		case "checkLogin":
			UVO checkUser = (UVO) session.getAttribute("loginUser");
		    if (checkUser != null) {
		        obj.put("result", "success");
		    } else {
		        obj.put("result", "fail");
		    }
			
			break;
			
		}
	out.print(obj);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
