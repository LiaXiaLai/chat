package com.jt.user.bean;

public class Friend {
	
	private long id;
	private long mainUserId;
	private long friendUserId;
	private long createTime;
	private long updateTime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getMainUserId() {
		return mainUserId;
	}
	public void setMainUserId(long mainUserId) {
		this.mainUserId = mainUserId;
	}
	public long getFriendUserId() {
		return friendUserId;
	}
	public void setFriendUserId(long friendUserId) {
		this.friendUserId = friendUserId;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "Friend [id=" + id + ", mainUserId=" + mainUserId + ", friendUserId=" + friendUserId + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
}
