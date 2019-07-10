package com.etc.blog.dao;

import com.etc.blog.entity.User;

public interface UserDao {

	//查询方法	传入用户 名和密码 返回登录对象
	public  User checkLogin(String name,String pwd);
	
	//根据用户（作者）id查询文章的作者
	public User queryById(Integer userid);
	
	//修改用户的信
	public boolean updataUser(User user);
	
	public User register(String name, String password);
}
