package com.jt.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jt.user.bean.User;
import com.jt.user.service.UserService;
import com.jt.util.Message;
import com.jt.util.WriteMessage;

@WebServlet(urlPatterns="/user/register")
public class Register extends HttpServlet{
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
		Message message = new Message();
		
		String userName = req.getParameter("userName");
		if(userService.checkUserName(userName)){
			message.setCode(-1);
			message.setMessage("用户名重复,请重新输入");
			WriteMessage.write(resp, message);
			return;
		}
		
		String password = req.getParameter("password");
		
		String nickName = req.getParameter("nickName");
		
		User user = new User();
		
		user.setUserName(userName);
		user.setPassword(password);
		user.setNickName(nickName);
		
		boolean flg = userService.saveUser(user);
		
		if(flg){
			message.setCode(1);
			message.setMessage("注册成功");
		}else{
			message.setCode(-1);
			message.setMessage("注册失败");
		}
		
		WriteMessage.write(resp, message);
	}
}
