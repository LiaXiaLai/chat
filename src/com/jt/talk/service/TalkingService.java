package com.jt.talk.service;

import java.util.List;

import com.jt.talk.bean.SingleTalking;
import com.jt.talk.dao.TalkingDao;

public class TalkingService {
	
	public static final TalkingService SERVICE = new TalkingService();
	
	private TalkingService(){}
	
	private TalkingDao talkingDao = TalkingDao.DAO;
	
	/**
	 * 获取发送方的数据
	 * @param user
	 * @return
	 */
	public List<SingleTalking> getFromTalking(SingleTalking talking){
		
		List<SingleTalking> list = talkingDao.getsingleTalkings(talking);
		
		list.sort((a,b)->{
			
			return (int) (a.getCreateTime()-b.getCreateTime());
		});
		
		return list;
	}
	//获取最近50条聊天记录
	public List<SingleTalking> getFromTalkingAll(SingleTalking talking){
		
		List<SingleTalking> list = talkingDao.getsingleTalkingsAll(talking);
		
		list.sort((a,b)->{
			
			return (int) (a.getCreateTime()-b.getCreateTime());
		});
		
		return list;
	}
	
	public boolean saveSingleMessage(SingleTalking talking){
		
		int num  = talkingDao.saveSingleMessage(talking);
		
		return num>0;
	}
}
