package com.jt.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jt.user.service.UserService;
import com.jt.util.Message;
import com.jt.util.WriteMessage;
//检查用户名是否可用
@WebServlet(urlPatterns="/user/checkUserName")
public class CheckUserName extends HttpServlet{
	
	private UserService userService = UserService.SERVICE;
	
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
		
		String userName = req.getParameter("userName");
		
		Message message = new Message();
		//检查用户名是否为空 如果是空直接返回
		if("".equals(userName)){
			message.setCode(-1);
			message.setMessage("用户名不能为空");
			WriteMessage.write(resp, message);
			return;
		}
		//检测用户名是否可用 
		if(userService.checkUserName(userName)){
			message.setCode(-1);
			message.setMessage("用户名重复,请重新输入");
		}else{
			message.setCode(1);
			message.setMessage("用户名可用");
		}
		WriteMessage.write(resp, message);
	}
	
	
	
	
}
