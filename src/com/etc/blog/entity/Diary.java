package com.etc.blog.entity;

public class Diary {
	Integer diary_id;
	String diary_content;
	User diary_auther;
	String diary_time;
	public Integer getDiary_id() {
		return diary_id;
	}
	public void setDiary_id(Integer diary_id) {
		this.diary_id = diary_id;
	}
	public String getDiary_content() {
		return diary_content;
	}
	public void setDiary_content(String diary_content) {
		this.diary_content = diary_content;
	}
	public User getDiary_auther() {
		return diary_auther;
	}
	public void setDiary_auther(User diary_auther) {
		this.diary_auther = diary_auther;
	}
	public String getDiary_time() {
		return diary_time;
	}
	public void setDiary_time(String diary_time) {
		this.diary_time = diary_time;
	}
	
}
