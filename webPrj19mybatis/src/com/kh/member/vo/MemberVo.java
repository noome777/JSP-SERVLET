package com.kh.member.vo;

public class MemberVo {
	
	private String userId;
	private String userPwd;
	
	public MemberVo() {}

	public MemberVo(String userId, String userPwd) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	@Override
	public String toString() {
		return "MemberVo [userId=" + userId + ", userPwd=" + userPwd + "]";
	}

	
	

}
