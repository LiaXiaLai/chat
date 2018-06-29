package com.jt.talk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jt.talk.bean.SingleTalking;
import com.jt.talk.service.TalkingService;
import com.jt.user.bean.User;
import com.jt.util.Message;
import com.jt.util.WriteMessage;

@WebServlet(urlPatterns="/talk/sendSingleMessage")
public class SendSingleMessage extends HttpServlet{
	
	private TalkingService service = TalkingService.SERVICE;
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
			message.setMessage("请登录");
			WriteMessage.write(resp, message);
			return;
		}
		
		long toUserId = Long.parseLong(req.getParameter("id"));
		
		String content = req.getParameter("content");
		
		long createTime = Long.parseLong(req.getParameter("createTime"));
		SingleTalking singleTalking = new SingleTalking();
		
		
		singleTalking.setFromUserId(user.getId());
		singleTalking.setToUserId(toUserId);
		singleTalking.setContent(content);
		singleTalking.setCreateTime(createTime);
		
		boolean flg = service.saveSingleMessage(singleTalking);
		
		if(flg){
			message.setCode(1);
			message.setMessage("发送成功成功");
		}else{
			message.setCode(-1);
			message.setMessage("发送失败");
		}
		WriteMessage.write(resp, message);
	}
	
	
}
