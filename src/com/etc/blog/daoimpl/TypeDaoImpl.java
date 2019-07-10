package com.etc.blog.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.etc.blog.dao.TypeDao;
import com.etc.blog.entity.Type;
import com.etc.blog.util.JDBCUtil;

public class TypeDaoImpl implements TypeDao{

	JDBCUtil util = new JDBCUtil();
	/**
	 * 根据文章类型id，查询对应类型对象
	 * @param typeid 文章表中的代表文章类型的id
	 * @return type	返回查找到的文章的类型
	 * @since 2018年9月25日 21:33:01
	 */
	@Override
	public Type queryById(Integer typeid) {
		ResultSet rs = util.doQuery("select * from t_type where type_id=?", typeid);
		
		Type type=null;
		try {
			while(rs.next()){
				type = new Type();
				type.setType_id(rs.getInt("type_id"));
				type.setType_name(rs.getString("type_name"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.doClose(rs);
		}
			
		return type;
	}
	
	/**
	 * 添加文章的类型
	 * @return boolean	添加成功返回true
	 * @since	2018年9月26日 14:40:28
	 */
	@Override
	public boolean addType(Type type) {
		
		return util.doUpdate("insert into t_type(type_name) values(?)", type.getType_name());
	}

	/**
	 * 查询所有的文章的类型
	 * @return list	返回查询到的类型的集合
	 * @since	2018年9月26日 17:09:11
	 */
	@Override
	public List<Type> queryAllType() {
		ResultSet rs = util.doQuery("select * from t_type");
		List<Type> list = new ArrayList<Type>();
		try {
			while(rs.next()){
				Type type = new Type();
				type.setType_id(rs.getInt("type_id"));
				type.setType_name(rs.getString("type_name"));
				list.add(type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.doClose(rs);
		}
		return list;
	}

	/**
	 * 删除文章的类型
	 * @param typeid 	要被删除的文章类型的id
	 * @return boolean 	删除成功返回true
	 * @since	2018年9月28日 21:30:16
	 */
	@Override
	public boolean deleteType(Integer typeid) {
		return util.doUpdate("delete from t_type where type_id=?", typeid);
	}

	/**
	 * 根据文章类型的id修改文章的类型
	 * @param typeid	要被修改的文章类型的id
	 * @return boolean 	修改文章类型成功返回true
	 * @since	2018年9月28日 21:50:52
	 */
	@Override
	public boolean updateType(Type type) {
		return util.doUpdate("update t_type set type_name=? where type_id=?",type.getType_name(),type.getType_id() );
	}

}
