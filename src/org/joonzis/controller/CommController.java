package org.joonzis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.joonzis.service.CommService;
import org.joonzis.service.CommServiceImpl;
import org.joonzis.vo.CVO;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/CommController")
public class CommController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");


		// 분기 판단 cmd
		String cmd = request.getParameter("cmd");
		
		// 비동기 처리를 위한 객체들
		ObjectMapper objectMapper = null;     //JSON과 java 객체를 변환
		String jsonString = null;           // JSON으로 직렬화 된 데이터를 담는 용도
		PrintWriter out = response.getWriter(); // 응답 객체
		JSONObject obj = new JSONObject();    // 응답으로 보내줄 객체
		
		// DB데이를 다루기 위한 객체들
		CVO cvo = null;
		CommService cservice = new CommServiceImpl();

		switch (cmd) {
		case "insertComm":
			cvo = new CVO();
			cvo.setWriter(request.getParameter("writer"));
			cvo.setContent(request.getParameter("content"));
			cvo.setM_idx(Integer.parseInt(request.getParameter("m_idx")));
			
			cservice.insertComm(cvo);
			obj.put("result", "success");
			break;
			
		case "commList":
			int m_idx = Integer.parseInt(request.getParameter("m_idx"));
			
			// cservice.getCommList 메소드 호출
			// cList 라는 참조 변수에 저장
			List<CVO> cList = cservice.getCommList(m_idx);
			objectMapper = new ObjectMapper();
			jsonString = objectMapper.writeValueAsString(cList);
			
			
			obj.put("cList", jsonString);
			break;
			
			// 댓글 삭제
		case "remove" :
			int c_idx = Integer.parseInt(request.getParameter("c_idx"));
			int result = cservice.removeComm(c_idx);
			obj.put("result", "success");
			break;
			
			// 댓글 수정
		case "update":
			int c_idx2 = Integer.parseInt(request.getParameter("c_idx"));
		    String content = request.getParameter("content");

		    cvo = new CVO();
		    cvo.setC_idx(c_idx2);
		    cvo.setContent(content);
		    

		    result = cservice.updateComm(cvo);

		    obj.clear();
		    obj.put("result", result > 0 ? "success" : "fail");

		    break;   
		}
		
		out.println(obj);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
