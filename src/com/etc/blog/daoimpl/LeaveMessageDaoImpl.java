package com.etc.blog.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.etc.blog.dao.LeaveMessageDao;
import com.etc.blog.dao.UserDao;
import com.etc.blog.entity.LeaveMessage;
import com.etc.blog.entity.User;
import com.etc.blog.util.JDBCUtil;

public class LeaveMessageDaoImpl implements LeaveMessageDao{
	
	JDBCUtil util = new JDBCUtil();
	@Override
	public boolean addLeaveMessage(LeaveMessage leaveMessage) {
		return util.doUpdate("insert into t_leaveMessage(leaveMessage_content,leaveMessage_time,leaveMessage_user,leaveMessage_auther) values(?,?,?,?)",
				leaveMessage.getLeaveMessage_content(),
				leaveMessage.getLeaveMessage_time(),
				leaveMessage.getLeaveMessage_user().getUser_id(),
				leaveMessage.getLeaveMessage_auther().getUser_id()
				);
	}
	@Override
	public List<LeaveMessage> getContent(User user,int pageIndex) {
		ResultSet rs = util.doQuery("select * from t_leaveMessage where leaveMessage_user=? and leaveMessage_response is null ORDER BY leaveMessage_time DESC LIMIT ?,10 ", user.getUser_id(),(pageIndex-1)*10);
		List<LeaveMessage> list = new ArrayList<LeaveMessage>();
		try {
			while(rs.next()) {
				LeaveMessage lm = new LeaveMessage();
				lm.setLeaveMessage_id(rs.getInt("leaveMessage_id"));
				lm.setLeaveMessage_content(rs.getString("leaveMessage_content"));
				lm.setLeaveMessage_time(rs.getString("leaveMessage_time"));
				// 取得对应id得用户对象
				UserDao udao = new UserDaoImpl();
				lm.setLeaveMessage_user(udao.queryById(rs.getInt("leaveMessage_user")));
				lm.setLeaveMessage_auther(udao.queryById(rs.getInt("leaveMessage_auther")));
				list.add(lm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			util.doClose(rs);
		}
		return list;
	}
	@Override
	public int getAllCount(User user) {
		ResultSet rs = util.doQuery("select count(*) as count from t_leaveMessage where leaveMessage_user=? and leaveMessage_response is null", user.getUser_id());
		int count = 0;
		try {
			while(rs.next()) {
				// 取得对应id得用户对象
				count = rs.getInt("count"); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			util.doClose(rs);
		}
		return count;
	}
	@Override
	public boolean deleteLeaveMessage(int id) {
		return util.doUpdate("delete from t_leaveMessage where leaveMessage_id=?", id);
	}
}
