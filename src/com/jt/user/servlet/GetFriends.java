package com.jt.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jt.user.bean.User;
import com.jt.user.service.FriendsService;
import com.jt.util.Message;
import com.jt.util.WriteMessage;

@WebServlet(urlPatterns="/user/getFriends")
public class GetFriends extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FriendsService friendsService = FriendsService.SERVICE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Message message = new Message();
		
		User user = (User)req.getSession().getAttribute("userInfo");
		
		if(user==null){
			message.setCode(-1);
			message.setMessage("请先登录");
			WriteMessage.write(resp, message);
			return;
		}
		
		List<User> list = friendsService.getFriends(user.getId());
		
		message.setCode(1);
		message.setMessage("成功获取好友列表");
		message.setData(list);
		
		WriteMessage.write(resp, message);
	}
	
	
	
	
	
}
