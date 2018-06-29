package com.jt.talk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jt.user.bean.User;
import com.jt.util.Message;

@WebServlet(urlPatterns="/talk/toSigleTalkingPage")
public class ToSigleTalkingPage extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		Message message = new Message();
		
		User user = (User) req.getSession().getAttribute("userInfo");		
		if(user==null){
			message.setCode(-1);
			message.setMessage("ÇëµÇÂ¼");
			req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
		}else{
			req.setAttribute("userId", user.getId());
			req.setAttribute("id", req.getParameter("id"));
			req.setAttribute("nickName", req.getParameter("nickName"));
			req.getRequestDispatcher("/pages/sigleTalking.jsp").forward(req, resp);
		}
	}
	
	
}
