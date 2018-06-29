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
	 * 保存用户信息
	 * @param user
	 * @return	true: 保存成功
	 * 			false: 保存失败
	 */
	public boolean saveUser(User user){
		
		user.setCreateTime(System.currentTimeMillis());
		user.setUpdateTime(System.currentTimeMillis());
		
		user.setPassword(Encoder.encode(user.getPassword()));
		
		int num = userDao.svaeUser(user);
		
		return num > 0;
	}
	
	/**
	 * 检查用户名是否存在
	 * @param userName
	 * @return
	 */
	public boolean checkUserName(String userName){
		
		return userDao.checkUserName(userName);
		
	}
	
	/**
	 * 根据用户名密码获取用户信息
	 * @param user
	 * @return 如果没有获取到用户信息,返回null
	 */
	public User getUserByUserNameAndPassword(User user){
		user.setPassword(Encoder.encode(user.getPassword()));
		return userDao.getUserByUserNameAndPassword(user);
	}
	/**
	 * 根据用户昵称查询用户
	 * @param nickName
	 * @return 如果没有查询结构返回空集合
	 */
	public List<User> getUserByNickName(String nickName){
		
		return userDao.getUserByNickName(nickName);
	}
}
