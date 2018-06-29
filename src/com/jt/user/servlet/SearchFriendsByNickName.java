package com.jt.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jt.user.bean.User;
import com.jt.user.service.UserService;
import com.jt.util.Message;
import com.jt.util.WriteMessage;

@WebServlet("/user/searchFriendsByNickName")
public class SearchFriendsByNickName extends HttpServlet{
	
	
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
		String nickName = req.getParameter("nickName");
		List<User> list = userService.getUserByNickName(nickName);
		
		Message message = new Message();
		message.setCode(1);
		message.setData(list);
		WriteMessage.write(resp, message);
	}
	
	
	
}
