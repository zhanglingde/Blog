package com.etc.blog.biz;

import java.util.List;

import com.etc.blog.entity.LeaveMessage;
import com.etc.blog.entity.User;

public interface LeaveMessageBiz {
	
	public boolean addLeaveMessage(LeaveMessage leaveMessage);

	public List<LeaveMessage> getContent(User user,int pageIndex);
	
	public int getAllCount(User user);

	public boolean deleteLeaveMessage(int id);
}
