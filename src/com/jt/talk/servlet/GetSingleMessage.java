package com.jt.talk.servlet;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/talk/getSingleMessage")
public class GetSingleMessage extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TalkingService talkingService = TalkingService.SERVICE;

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
			WriteMessage.write(resp, message);
			return;
		}
		SingleTalking singleTalking = new SingleTalking();
		
		long toUserId = Long.parseLong(req.getParameter("id"));
		
		String flg = req.getParameter("flg");
		List<SingleTalking> list = null;
		if(flg!=null){
			singleTalking.setFromUserId(user.getId());
			singleTalking.setToUserId(toUserId);
			list = talkingService.getFromTalkingAll(singleTalking);
		}else{
			singleTalking.setFromUserId(toUserId);
			singleTalking.setToUserId(user.getId());
			singleTalking.setCreateTime(Long.parseLong(req.getParameter("createTime")));
			list = talkingService.getFromTalking(singleTalking);
		}
		
		message.setCode(1);
		message.setMessage("ÇëÇó³É¹¦");
		message.setData(list);
		
		WriteMessage.write(resp, message);
	}
	
	
	

}
