package com.jt.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jt.user.bean.Friend;
import com.jt.user.bean.User;
import com.jt.util.MyPool;

public class FriendsDao {
	
	public static final FriendsDao DAO = new FriendsDao();
	
	private FriendsDao(){};
	
	
	//根据用户id查询好友列表
	
	public List<User> getFriends(long id){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<>();  
		try {
			cn = MyPool.getConnection();
			ps = cn.prepareStatement("SELECT u.id,u.nick_name FROM friends f LEFT JOIN users u ON f.friend_user_id = u.id WHERE main_user_id = ?");
			
			ps.setLong(1, id);
			
			rs = ps.executeQuery();
			
			
			
			while(rs.next()){
				User user = new User();
				user.setId(rs.getLong(1));
				user.setNickName(rs.getString(2));
				list.add(user);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyPool.release(cn, ps, rs);
		}
		return list;
	}
	
	//检测好友是否存在
	public boolean isFriend(long mainUserId,long friendUserId){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cn = MyPool.getConnection();
			ps = cn.prepareStatement("SELECT id FROM friends WHERE main_user_id=? AND friend_user_id = ?");
			
			ps.setLong(1, mainUserId);
			ps.setLong(2, friendUserId);
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyPool.release(cn, ps, rs);
		}
		return false;
	}
	
	//添加好友
	public int saveFriend(Friend friend){
		Connection cn = null;
		PreparedStatement ps = null;
		int num = 0;
		try {
			cn = MyPool.getConnection();
			ps = cn.prepareStatement("INSERT INTO friends(main_user_id,friend_user_id,create_time,update_time)VALUES(?,?,?,?)");
			
			ps.setLong(1, friend.getMainUserId());
			ps.setLong(2, friend.getFriendUserId());
			ps.setLong(3, friend.getCreateTime());
			ps.setLong(4, friend.getUpdateTime());
			
			num = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyPool.release(cn, ps, null);
		}
		return num;
	}
	
}
