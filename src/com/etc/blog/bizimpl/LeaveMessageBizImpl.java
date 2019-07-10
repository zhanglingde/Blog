package com.etc.blog.bizimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.etc.blog.biz.LeaveMessageBiz;
import com.etc.blog.dao.LeaveMessageDao;
import com.etc.blog.daoimpl.LeaveMessageDaoImpl;
import com.etc.blog.entity.LeaveMessage;
import com.etc.blog.entity.User;

public class LeaveMessageBizImpl implements LeaveMessageBiz{
	
	LeaveMessageDao dao = new LeaveMessageDaoImpl();
	
	@Override
	public boolean addLeaveMessage(LeaveMessage leaveMessage) {
		// 判空处理
		String content = leaveMessage.getLeaveMessage_content();
		if(content == null || "".equals(content)) return false;
		if(leaveMessage.getLeaveMessage_auther() == null) return false;
		if(leaveMessage.getLeaveMessage_user() == null) return false;
		return dao.addLeaveMessage(leaveMessage);
	}

	@Override
	public List<LeaveMessage> getContent(User user,int pageIndex) {
		if(user == null || user.getUser_id() == null) return null;
		int allCount = dao.getAllCount(user);  
		if(pageIndex <= 0) pageIndex = 1;
		if((pageIndex-1) * 10 >= allCount) return null;
		return dao.getContent(user,pageIndex);
	}

	@Override
	public int getAllCount(User user) {
		if(user == null || user.getUser_id() == null) return 0;
		return dao.getAllCount(user);
	}

	@Override
	public boolean deleteLeaveMessage(int id) {
		if(id <= 0) return false;
		return dao.deleteLeaveMessage(id);
	}
}
