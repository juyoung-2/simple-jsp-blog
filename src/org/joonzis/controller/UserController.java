package org.joonzis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joonzis.service.MainService;
import org.joonzis.service.MainServiceImpl;
import org.joonzis.vo.MVO;
import org.joonzis.vo.UVO;



@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UserController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String cmd = request.getParameter("cmd");
		if (cmd == null) {
			cmd = "mainPage";
		}
		
		String path = "";
		HttpSession session = request.getSession();
		MainService mservice = new MainServiceImpl();
		
		switch (cmd) {
		case "mainPage":
			path = "index.jsp";
			break;
		case "myPage":
		    UVO loginUser = (UVO) session.getAttribute("loginUser");
		    
		    if (loginUser == null) {
		        response.sendRedirect("UserController?cmd=loginPage");
		        return;
		    }
		    
		    List<MVO> myList = mservice.selectMyPostList(loginUser.getU_id());
		    request.setAttribute("myList", myList);
		    
			path = "project/my_page.jsp";
			break;
		case "loginPage":
			path = "project/login_page.jsp";
			break;
		case "joinPage":                 
			path = "project/join_page.jsp";
			break;
		case "logout":
			session.invalidate(); 
			response.sendRedirect("index.jsp");
			return;
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
