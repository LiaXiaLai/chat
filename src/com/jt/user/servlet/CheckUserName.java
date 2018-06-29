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
//����û����Ƿ����
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
		//����û����Ƿ�Ϊ�� ����ǿ�ֱ�ӷ���
		if("".equals(userName)){
			message.setCode(-1);
			message.setMessage("�û�������Ϊ��");
			WriteMessage.write(resp, message);
			return;
		}
		//����û����Ƿ���� 
		if(userService.checkUserName(userName)){
			message.setCode(-1);
			message.setMessage("�û����ظ�,����������");
		}else{
			message.setCode(1);
			message.setMessage("�û�������");
		}
		WriteMessage.write(resp, message);
	}
	
	
	
	
}
