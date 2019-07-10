package com.etc.blog.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.etc.blog.dao.DiaryDao;
import com.etc.blog.dao.UserDao;
import com.etc.blog.entity.Diary;
import com.etc.blog.entity.LeaveMessage;
import com.etc.blog.entity.User;
import com.etc.blog.util.JDBCUtil;

public class DiaryDaoImpl implements DiaryDao{
	
	JDBCUtil util = new JDBCUtil();
	@Override
	public boolean addDiary(Diary diary) {
		return util.doUpdate("insert into t_diary(diary_content,diary_auther,diary_time) values(?,?,?)", diary.getDiary_content(),diary.getDiary_auther().getUser_id(),diary.getDiary_time());
	}
	@Override
	public int getAllCount(User user) {
		ResultSet rs = util.doQuery("select count(*) as count from t_diary where diary_auther=?", user.getUser_id());
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
	public List<Diary> getContent(User user, int pageIndex) {
		ResultSet rs = util.doQuery("select * from t_diary where diary_auther=? ORDER BY diary_time DESC LIMIT ?,10 ", user.getUser_id(),(pageIndex-1)*10);
		List<Diary> list = new ArrayList<Diary>();
		try {
			while(rs.next()) {
				Diary tmp = new Diary();
				tmp.setDiary_id(rs.getInt("diary_id"));
				tmp.setDiary_content(rs.getString("diary_content"));
				tmp.setDiary_time(rs.getString("diary_time"));
				// 取得对应id得用户对象
				UserDao udao = new UserDaoImpl();
				tmp.setDiary_auther(udao.queryById(rs.getInt("diary_auther")));
				list.add(tmp);
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
	public boolean delete(int id) {
		return util.doUpdate("delete from t_diary where diary_id=?", id);
	}

}
