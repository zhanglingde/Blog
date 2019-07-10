package com.etc.blog.biz;

import com.etc.blog.entity.User;

public interface UserBiz {
	
	//登录验证
	public User checkLogin(String name,String pwd);
	
	//修改用户的信息
	public boolean updataUser(User user);
	
	//根据用户（作者）id查询文章的作者
	public User queryById(Integer userid);
	
	public User register(String name, String password);
}
