package com.etc.blog.biz;

import java.util.List;

import com.etc.blog.entity.Diary;
import com.etc.blog.entity.User;

public interface DiaryBiz {
	public boolean addDiary(Diary diary);

	public int getAllCount(User user);

	public List<Diary> getContent(User user, int pageIndex);

	public boolean delete(int id);
}
