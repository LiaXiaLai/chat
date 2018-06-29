package com.jt.talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jt.talk.bean.SingleTalking;
import com.jt.util.MyPool;

public class TalkingDao {
	
	public static final TalkingDao DAO = new TalkingDao();
	
	private TalkingDao(){}
	
	/**
	 * 获取对方发送过来的数据
	 * @param id
	 * @return
	 */
	public List<SingleTalking> getsingleTalkings(SingleTalking talk){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<SingleTalking> list = new ArrayList<>();
		try {
			cn = MyPool.getConnection();
			ps = cn.prepareStatement("SELECT * FROM sigle_talking  WHERE from_user_id=? AND to_user_id=? AND create_time >? ORDER BY create_time DESC");
			
			ps.setLong(1, talk.getFromUserId());
			ps.setLong(2, talk.getToUserId());
			ps.setLong(3, talk.getCreateTime());
			
			rs = ps.executeQuery();
			while(rs.next()){
				SingleTalking talking = new SingleTalking();
				
				talking.setId(rs.getLong(1));
				talking.setFromUserId(rs.getLong(2));
				talking.setToUserId(rs.getLong(3));
				talking.setContent(rs.getString(4));
				talking.setCreateTime(rs.getLong(5));
				list.add(talking);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyPool.release(cn, ps, rs);
		}
		
		return list;
	}
	
	public List<SingleTalking> getsingleTalkingsAll(SingleTalking talk){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<SingleTalking> list = new ArrayList<>();
		try {
			cn = MyPool.getConnection();
			ps = cn.prepareStatement("SELECT * FROM sigle_talking  WHERE (from_user_id=? AND to_user_id = ? )OR (from_user_id=? AND to_user_id = ?) ORDER BY create_time DESC LIMIT 0,50");
			
			ps.setLong(1, talk.getFromUserId());
			ps.setLong(2, talk.getToUserId());
			ps.setLong(3, talk.getToUserId());
			ps.setLong(4, talk.getFromUserId());
			
			rs = ps.executeQuery();
			while(rs.next()){
				SingleTalking talking = new SingleTalking();
				
				talking.setFromUserId(rs.getLong(2));
				talking.setToUserId(rs.getLong(3));
				talking.setContent(rs.getString(4));
				talking.setCreateTime(rs.getLong(5));
				list.add(talking);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyPool.release(cn, ps, rs);
		}
		
		return list;
	}
	
	//保存聊天数据
	public int saveSingleMessage(SingleTalking talk){
		Connection cn = null;
		PreparedStatement ps = null;
		
		int num = 0;
		
		try {
			cn = MyPool.getConnection();
			ps = cn.prepareStatement("INSERT INTO sigle_talking (from_user_id,to_user_id,content,create_time)VALUES(?,?,?,?)");
			
			ps.setLong(1, talk.getFromUserId());
			ps.setLong(2, talk.getToUserId());
			ps.setString(3, talk.getContent());
			ps.setLong(4, talk.getCreateTime());
			
			num = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyPool.release(cn, ps, null);
		}
		
		return num;
	}
}
