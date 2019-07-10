package com.etc.blog.dao;

import java.util.List;

import com.etc.blog.entity.LeaveMessage;
import com.etc.blog.entity.User;

public interface LeaveMessageDao {
	
	public boolean addLeaveMessage(LeaveMessage leaveMessage);

	public List<LeaveMessage> getContent(User user,int pageIndex); 
	
	public int getAllCount(User user);

	public boolean deleteLeaveMessage(int id);
}
