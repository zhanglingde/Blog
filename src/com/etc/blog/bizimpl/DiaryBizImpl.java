package com.etc.blog.bizimpl;

import java.util.List;

import com.etc.blog.biz.DiaryBiz;
import com.etc.blog.dao.DiaryDao;
import com.etc.blog.daoimpl.DiaryDaoImpl;
import com.etc.blog.entity.Diary;
import com.etc.blog.entity.User;

public class DiaryBizImpl implements DiaryBiz{
	
	DiaryDao dao = new DiaryDaoImpl();
	@Override
	public boolean addDiary(Diary diary) {
		if(diary.getDiary_auther() == null) return false;
		if(diary.getDiary_content() == null) return false;
		if(diary.getDiary_auther().getUser_id() == null) return false;
		return dao.addDiary(diary);
	}
	@Override
	public int getAllCount(User user) {
		if(user == null || user.getUser_id() == null) return 0;
		return dao.getAllCount(user);
	}
	@Override
	public List<Diary> getContent(User user, int pageIndex) {
		if(user == null || user.getUser_id() == null) return null;
		int allCount = dao.getAllCount(user);  
		if(pageIndex <= 0) pageIndex = 1;
		if((pageIndex-1) * 10 >= allCount) return null;
		return dao.getContent(user,pageIndex);
	}
	@Override
	public boolean delete(int id) {
		if(id <= 0) return false;
		return dao.delete(id);
	}
	
}
