package com.jt.user.service;

import java.util.List;

import com.jt.user.bean.Friend;
import com.jt.user.bean.User;
import com.jt.user.dao.FriendsDao;

public class FriendsService {
	
	public static final FriendsService SERVICE = new FriendsService();
	
	private FriendsService(){};
	
	
	private FriendsDao friendsDao = FriendsDao.DAO;
	
	
	public List<User> getFriends(long id){
		return friendsDao.getFriends(id);
	}
	
	public boolean isFriend(long mainUserId,long friendUserId ){
		
		return friendsDao.isFriend(mainUserId, friendUserId);
	}
	
	
	public boolean saveFriend(long mainUserId,long friendUserId ){
		Friend friend = new Friend();
		friend.setMainUserId(mainUserId);
		friend.setFriendUserId(friendUserId);
		friend.setCreateTime(System.currentTimeMillis());
		friend.setUpdateTime(System.currentTimeMillis());
		
		int num = friendsDao.saveFriend(friend);
		if(num>0){
			friend.setMainUserId(friendUserId);
			friend.setFriendUserId(mainUserId);
			num = friendsDao.saveFriend(friend);
		}
		
		
		return num>0;
	}
	
	
	
}
