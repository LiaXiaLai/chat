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

@WebServlet(urlPatterns="/user/login")
public class Login extends HttpServlet{
	
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
		String password = req.getParameter("password");
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user = userService.getUserByUserNameAndPassword(user);
		if(user==null){
			message.setCode(-1);
			message.setMessage("对不起,用户名密码错误");
		}else{
			message.setCode(1);
			message.setMessage("登录成功");
			
			req.getSession().setAttribute("userInfo", user);
		}
		
		WriteMessage.write(resp, message);
		//http请求每一次都是独立的
		//会话保持    	JSESSIONID = 654F6ASFASKFJ
		//第一次使用session的时候才会创建session对象
		
		//查找request中的cookie有没有JSESSIONID
		//如果没有,生成一个, 创建一个Session对象, 
		//使用JSESSIONID的值作为可以, Session对象作为值存入到ApplicationContext;
		//通过response将JSESSIONID的值写回到浏览器中存储
		
		
		
		
	}


}
