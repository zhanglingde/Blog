package com.etc.blog.entity;

public class LeaveMessage {
	Integer leaveMessage_id;
	String leaveMessage_content;
	String leaveMessage_time;
	User leaveMessage_user;
	User leaveMessage_auther;
	LeaveMessage leaveMessage_response;
	public Integer getLeaveMessage_id() {
		return leaveMessage_id;
	}
	public void setLeaveMessage_id(Integer leaveMessage_id) {
		this.leaveMessage_id = leaveMessage_id;
	}
	public String getLeaveMessage_content() {
		return leaveMessage_content;
	}
	public void setLeaveMessage_content(String leaveMessage_content) {
		this.leaveMessage_content = leaveMessage_content;
	}
	public String getLeaveMessage_time() {
		return leaveMessage_time;
	}
	public void setLeaveMessage_time(String leaveMessage_time) {
		this.leaveMessage_time = leaveMessage_time;
	}
	public User getLeaveMessage_user() {
		return leaveMessage_user;
	}
	public void setLeaveMessage_user(User leaveMessage_user) {
		this.leaveMessage_user = leaveMessage_user;
	}
	public User getLeaveMessage_auther() {
		return leaveMessage_auther;
	}
	public void setLeaveMessage_auther(User leaveMessage_auther) {
		this.leaveMessage_auther = leaveMessage_auther;
	}
	public LeaveMessage getLeaveMessage_response() {
		return leaveMessage_response;
	}
	public void setLeaveMessage_response(LeaveMessage leaveMessage_response) {
		this.leaveMessage_response = leaveMessage_response;
	}
	
}
