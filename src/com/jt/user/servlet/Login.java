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
			message.setMessage("�Բ���,�û����������");
		}else{
			message.setCode(1);
			message.setMessage("��¼�ɹ�");
			
			req.getSession().setAttribute("userInfo", user);
		}
		
		WriteMessage.write(resp, message);
		//http����ÿһ�ζ��Ƕ�����
		//�Ự����    	JSESSIONID = 654F6ASFASKFJ
		//��һ��ʹ��session��ʱ��Żᴴ��session����
		
		//����request�е�cookie��û��JSESSIONID
		//���û��,����һ��, ����һ��Session����, 
		//ʹ��JSESSIONID��ֵ��Ϊ����, Session������Ϊֵ���뵽ApplicationContext;
		//ͨ��response��JSESSIONID��ֵд�ص�������д洢
		
		
		
		
	}


}
