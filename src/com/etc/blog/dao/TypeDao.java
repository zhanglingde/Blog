package com.etc.blog.dao;

import java.util.List;

import com.etc.blog.entity.Type;

public interface TypeDao {
	
	//根据文章类型id，查询对应类型对象
	public Type queryById(Integer typeid);
	
	//添加文章的类型
	public boolean addType(Type type);
	
	//查询所有文章的类型
	public List<Type> queryAllType();
	
	//根据id删除文章的类型
	public boolean deleteType(Integer typeid);
	
	//根据类型id修改文章的类型
	public boolean updateType(Type type);

}
