package com.jt.user.service;

import java.util.List;

import com.jt.user.bean.User;
import com.jt.user.dao.UserDao;
import com.jt.util.Encoder;

public class UserService {
	
	public static final UserService SERVICE = new UserService();
	
	private UserService(){};
	
	private UserDao userDao = UserDao.DAO;
	
	/**
	 * �����û���Ϣ
	 * @param user
	 * @return	true: ����ɹ�
	 * 			false: ����ʧ��
	 */
	public boolean saveUser(User user){
		
		user.setCreateTime(System.currentTimeMillis());
		user.setUpdateTime(System.currentTimeMillis());
		
		user.setPassword(Encoder.encode(user.getPassword()));
		
		int num = userDao.svaeUser(user);
		
		return num > 0;
	}
	
	/**
	 * ����û����Ƿ����
	 * @param userName
	 * @return
	 */
	public boolean checkUserName(String userName){
		
		return userDao.checkUserName(userName);
		
	}
	
	/**
	 * �����û��������ȡ�û���Ϣ
	 * @param user
	 * @return ���û�л�ȡ���û���Ϣ,����null
	 */
	public User getUserByUserNameAndPassword(User user){
		user.setPassword(Encoder.encode(user.getPassword()));
		return userDao.getUserByUserNameAndPassword(user);
	}
	/**
	 * �����û��ǳƲ�ѯ�û�
	 * @param nickName
	 * @return ���û�в�ѯ�ṹ���ؿռ���
	 */
	public List<User> getUserByNickName(String nickName){
		
		return userDao.getUserByNickName(nickName);
	}
}
