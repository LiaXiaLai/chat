package com.jt.talk.bean;

public class SingleTalking {
	
	private long id;
	private long fromUserId;
	private long toUserId;
	private String content;
	private long createTime;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(long fromUserId) {
		this.fromUserId = fromUserId;
	}
	public long getToUserId() {
		return toUserId;
	}
	public void setToUserId(long toUserId) {
		this.toUserId = toUserId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "SingleTalking [id=" + id + ", fromUserId=" + fromUserId + ", toUserId=" + toUserId + ", content="
				+ content + ", createTime=" + createTime + "]";
	}
}
