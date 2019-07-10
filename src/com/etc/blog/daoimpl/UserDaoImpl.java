package com.etc.blog.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.etc.blog.dao.UserDao;
import com.etc.blog.entity.User;
import com.etc.blog.util.JDBCUtil;

public class UserDaoImpl implements UserDao{
	
	JDBCUtil util=new JDBCUtil();
	
	/**
	 * 验证登录的用户名和密码是否和数据库里的匹配
	 * @param name	用户名
	 * @param pwd	密码
	 * @return user 返回查询到的user对象，数据库有，登录成功
	 * @since 2018年9月25日 21:46:28
	 */
	@Override
	public User checkLogin(String name, String pwd) {
		ResultSet rs = util.doQuery("select * from t_user where user_lname=? and user_pwd=?",name,pwd);
		User user = null;
		try {
			while(rs.next()){
				user = new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_info(rs.getString("user_info"));
				user.setUser_job(rs.getString("user_job"));
				user.setUser_lname(rs.getString("user_lname"));
				user.setUser_pwd(rs.getString("user_pwd"));
				user.setUser_rname(rs.getString("user_rname"));
				user.setUser_sex(rs.getString("user_sex"));
				user.setUser_image(rs.getString("user_image"));
				user.setUser_motto(rs.getString("user_motto"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.doClose(rs);
		}
		return user;
	}

	/**
	 * 根据作者的id从用户（作者）表查询文章的作者
	 * @param userid	作者的id
	 * @return user		返回根据userid查询到文章对应的作者对象
	 * @since	2018年9月25日 21:50:10
	 */
	@Override
	public User queryById(Integer userid) {
		ResultSet rs = util.doQuery("select * from t_user where user_id=?", userid);
		User user = null;
		try {
			while(rs.next()){
				user = new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_info(rs.getString("user_info"));
				user.setUser_job(rs.getString("user_job"));
				user.setUser_lname(rs.getString("user_lname"));
				user.setUser_pwd(rs.getString("user_pwd"));
				user.setUser_rname(rs.getString("user_rname"));
				user.setUser_sex(rs.getString("user_sex"));
				user.setUser_image(rs.getString("user_image"));
				user.setUser_motto(rs.getString("user_motto"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.doClose(rs);
		}
		return user;
	}

	/**
	 * 修改用户的个人信息
	 * @param user	被修改的用户的对象
	 * @return boolean 	修改成功返回true
	 * @since 2018年9月26日 23:41:10
	 */
	@Override
	public boolean updataUser(User user) {
		return util.doUpdate("update t_user set user_rname=?,user_sex=?,user_job=?,user_image=?,user_motto=? where user_id=?", user.getUser_rname(),user.getUser_sex(),user.getUser_job(),user.getUser_image(),user.getUser_motto(),user.getUser_id());
	}

	@Override
	public User register(String name, String password) {
		// 重名判断
		ResultSet rs = util.doQuery("select * from t_user where user_lname=?", name);
		try {
			if(rs.next())  return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int id = util.doInsertGetPK("insert into t_user(user_lname,user_pwd,user_sex) values(?,?,?)",name,password,"男");
		if(id < 0) return null;
		return queryById(id);
	}
	
	

}
