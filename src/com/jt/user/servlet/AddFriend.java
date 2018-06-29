package com.jt.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jt.user.bean.User;
import com.jt.user.service.FriendsService;
import com.jt.util.Message;
import com.jt.util.WriteMessage;

@WebServlet(urlPatterns="/user/addFriend")
public class AddFriend extends HttpServlet{
	
	private FriendsService friendsService = FriendsService.SERVICE;
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
			message.setMessage("请先登录");
			WriteMessage.write(resp, message);
			return;
		}
		
		long friendUserId = Long.parseLong(req.getParameter("id"));
		
		if(user.getId()==friendUserId){
			message.setCode(-1);
			message.setMessage("你是不是有病....");
			WriteMessage.write(resp, message);
			return;
		}
		boolean flg = friendsService.isFriend(user.getId(), friendUserId);
		
		if(flg){
			message.setCode(-1);
			message.setMessage("对不起,你们已经很亲密了..");
			WriteMessage.write(resp, message);
			return;
		}
		
		flg =friendsService.saveFriend(user.getId(), friendUserId);
		
		if(flg){
			message.setCode(1);
			message.setMessage("添加成功");
		}else{
			message.setCode(-1);
			message.setMessage("对不起,添加失败..");
		}
		WriteMessage.write(resp, message);
	}
	
	
	
}
