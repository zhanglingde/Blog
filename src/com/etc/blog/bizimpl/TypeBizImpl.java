package com.etc.blog.bizimpl;

import java.util.List;

import com.etc.blog.biz.TypeBiz;
import com.etc.blog.dao.TypeDao;
import com.etc.blog.daoimpl.TypeDaoImpl;
import com.etc.blog.entity.Type;

public class TypeBizImpl implements TypeBiz{

	TypeDao dao = new TypeDaoImpl();
	/**
	 * 添加文章类型
	 * @param type	要添加的类型的对象
	 * @return boolean	添加成功返回true
	 * @since	2018年9月26日 14:52:19
	 */
	@Override
	public boolean addType(Type type) {
		return dao.addType(type);
	}
	
	//查询所有文章的类型		
	@Override
	public List<Type> queryAllType() {
		return dao.queryAllType();
	}

	//根据文章类型id，查询对应类型对象
	@Override
	public Type queryById(Integer typeid) {
		return dao.queryById(typeid);
	}

	//根据id删除文章的类型
	@Override
	public boolean deleteType(Integer typeid) {
		return dao.deleteType(typeid);
	}

	//根据类型id修改文章的类型
	@Override
	public boolean updateType(Type type) {
		return dao.updateType(type);
	}

}
