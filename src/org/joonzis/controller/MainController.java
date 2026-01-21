package org.joonzis.controller;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.joonzis.model.Criteria;
import org.joonzis.model.FileDownload;
import org.joonzis.model.PageDTO;
import org.joonzis.service.MainService;
import org.joonzis.service.MainServiceImpl;
import org.joonzis.vo.MVO;
import org.joonzis.vo.UVO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 파일 업로드
		String realPath = request.getServletContext().getRealPath("/upload"); // 업로드 경로
		File uploadDir = new File(realPath);
		if (!uploadDir.exists()) {
		    uploadDir.mkdirs(); // tmp 폴더에도 폴더 생성
		}
		MultipartRequest mr = null;

	
		String cmd = request.getParameter("cmd");
		if (cmd == null) {
			mr = new MultipartRequest(request, realPath, 1024 * 1024 * 10, "utf-8", new DefaultFileRenamePolicy());
			cmd = mr.getParameter("cmd");
		}

		boolean isForward = true;
		String path = "";
		MainService mservice = new MainServiceImpl();
		List<MVO> list = null;
		MVO mvo = null;
		HttpSession session = request.getSession();
		String open = null; 
		
		String pageNum = "";
		String amount = "";
		int parsePageNum = 0;
		int parseAmount = 0;
		
		switch (cmd) {
		// 목록 전체 보기
		case "main":
			//조회수
			open = (String)session.getAttribute("open");
			if(open != null) {
				session.removeAttribute("open");
			}
			pageNum = request.getParameter("pageNum");
			amount = request.getParameter("amount");
			
			if(pageNum != null && amount != null) {
				parsePageNum = Integer.parseInt(pageNum);
				parseAmount = Integer.parseInt(amount);
			}else {
				parsePageNum = 1;
				parseAmount = 5;
			}
			
			Criteria cri = new Criteria(parsePageNum, parseAmount);
			
			list = mservice.getListWithPaging(cri);
			
			int total = mservice.getTotalRecordCount();
			
			PageDTO pdto = new PageDTO(cri, total);
		
			request.setAttribute("list", list);
			request.setAttribute("pageMaker", pdto);
			
			
			path = "project/main_page.jsp";
			break;
		// 게시글 작성 페이지
		case "insertMainPage":
			path = "project/insert_page.jsp";
			break;
		// 게시글 작성
		case "insertMain":
			mvo = new MVO();
			mvo.setWriter(mr.getParameter("writer"));
			mvo.setTitle(mr.getParameter("title"));
			mvo.setContent(mr.getParameter("content"));

			if (mr.getFile("filename") != null) {
				mvo.setFilename(mr.getFilesystemName("filename"));
			} else {
				mvo.setFilename("");
			}

			int result = mservice.getInsertMain(mvo);

			isForward = false;
			path = "MainController?cmd=main";
			break;
		
		case "detail":
			int m_idx = Integer.parseInt(request.getParameter("m_idx"));
			mvo = mservice.getMain(m_idx);
			
			open = (String)session.getAttribute("open");
			if(open == null) {
				session.setAttribute("open", "yes");
				int hit = mvo.getHit() + 1;
				mvo.setHit(hit);
				mservice.updateHit(mvo);
			}
			
			session.setAttribute("mvo", mvo);
			path = "project/detail_page.jsp";
			break;

		case "remove":
			int m_idx2 = Integer.parseInt(request.getParameter("m_idx"));
			//result = mservice.removeMain(m_idx2);// ex 9번에 해당하는 댓글 전ㅊ ㅔ삭제
			result = mservice.removeMain(m_idx2); // 9번 게시글 삭제
			isForward = false;
			path = "MainController?cmd=main";
			break;
			
		case "updatePage":
			path = "project/update_page.jsp";
			break;
		
		case "update":
			String title = mr.getParameter("title");
			String content = mr.getParameter("content");
			int m_idx3 = Integer.parseInt(mr.getParameter("m_idx"));

			mvo = new MVO();
			mvo.setTitle(title);
			mvo.setContent(content);
			mvo.setM_idx(m_idx3);

			File newFile = mr.getFile("filename"); 
			String oldFile = mr.getParameter("oldfile"); 

			if (newFile != null) {
				// 새 첨부 파일 o
				if (oldFile != null) {
					// 기존 파일 o
					File removeFile = new File(realPath + "/" + oldFile);
					if (removeFile.exists()) { 
						removeFile.delete(); 
					}
				}
				mvo.setFilename(newFile.getName());
			} else {
				// 새 첨부 파일 x
				if (oldFile != null) {
					// 기존 첨부 파일 o
					mvo.setFilename(oldFile);
				} else {
					// 기존 첨부 파일 x, 새 첨부 파일 x
					mvo.setFilename("");
				}

			}

			result = mservice.updateMain(mvo);

			isForward = false;
			path = "MainController?cmd=detail&m_idx=" + m_idx3;
			break;
			
		case "download":
			FileDownload fd = new FileDownload();
			fd.doDownload(request, response);
			break;
			
			
		case "myPage":
		 UVO loginUser = (UVO) session.getAttribute("loginUser");

		    if (loginUser == null) {
		        response.sendRedirect("UserController?cmd=loginPage");
		        return;
		    }

		    String u_id = loginUser.getU_id();

		 
		    List<MVO> myList = mservice.selectMyPostList(u_id);
		    request.setAttribute("myList", myList);

		    path = "project/my_page.jsp";
		    break;
			
			

		}
			
		if (isForward) {
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			response.sendRedirect(path);
		}

	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
