package com.jt.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jt.user.bean.User;
import com.jt.util.MyPool;

public class UserDao {
	
	public static final UserDao DAO = new UserDao();
	
	private UserDao(){};
	
	//保存用户
	public int svaeUser(User user){
		
		Connection cn = null;
		PreparedStatement ps = null;
		int num = 0;
		
		try {
			cn = MyPool.getConnection();
			ps = cn.prepareStatement("INSERT INTO users(user_name,password,nick_name,create_time,update_time)VALUES(?,?,?,?,?)");
			
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickName());
			ps.setLong(4, user.getCreateTime());
			ps.setLong(5, user.getUpdateTime());
			
			num = ps.executeUpdate();
			
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyPool.release(cn, ps, null);
		}
		
		return num;
	}
	
	/**
	 * 检查用户名是否存在 
	 * 	true : 用户名存在
	 * 	false: 用户名不存
	 */
	public boolean checkUserName(String userName){

		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			cn = MyPool.getConnection();
			ps = cn.prepareStatement("SELECT id FROM users WHERE user_name=?");
			ps.setString(1, userName);
			
			rs = ps.executeQuery();
			return rs.next();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyPool.release(cn, ps, null);
		}
		
		return false;
	}
	//根据用户名密码查询用户信息
	public User getUserByUserNameAndPassword(User user){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			cn = MyPool.getConnection();
			ps = cn.prepareStatement("SELECT * FROM users WHERE user_name=? AND password =?");
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();
			
			if(rs.next()){
				user.setId(rs.getLong(1));
				user.setNickName(rs.getString(4));
				user.setCreateTime(rs.getLong(5));
				user.setUpdateTime(rs.getLong(6));
				return user;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyPool.release(cn, ps, null);
		}
		
		return null;
	}
	
	public List<User> getUserByNickName(String nickName){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<>();
		try {
			cn = MyPool.getConnection();
			ps = cn.prepareStatement("SELECT id,nick_name FROM users WHERE nick_name=?");
			ps.setString(1, nickName);
			rs = ps.executeQuery();
			
			if(rs.next()){
				User user = new User();
				user.setId(rs.getLong(1));
				user.setNickName(rs.getString(2));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyPool.release(cn, ps, null);
		}
		return list;
	}
}
