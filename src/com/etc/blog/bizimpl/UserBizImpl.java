package com.etc.blog.bizimpl;

import com.etc.blog.biz.UserBiz;
import com.etc.blog.dao.UserDao;
import com.etc.blog.daoimpl.UserDaoImpl;
import com.etc.blog.entity.User;

public class UserBizImpl implements UserBiz{

	UserDao dao = new UserDaoImpl();
	
	//用户登录验证，查询数据库用户名和密码 
	@Override
	public User checkLogin(String name, String pwd) {
		return dao.checkLogin(name, pwd);
	}

	//修改用户的信息
	@Override
	public boolean updataUser(User user) {
		return dao.updataUser(user);
	}

	//根据用户（作者）id查询文章的作者
	@Override
	public User queryById(Integer userid) {
		return dao.queryById(userid);
	}
	
	@Override
	public User register(String name, String password) {
		if(name == null || password == null) return null;
		if("".equals(name)||"".equals(password))return null;
		return dao.register(name, password);
	}

}
