package com.etc.blog.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.etc.blog.dao.ArticleDao;
import com.etc.blog.dao.TypeDao;
import com.etc.blog.dao.UserDao;
import com.etc.blog.entity.Article;
import com.etc.blog.entity.Type;
import com.etc.blog.entity.User;
import com.etc.blog.util.JDBCUtil;

public class ArticleDaoImpl implements ArticleDao{

	JDBCUtil util = new JDBCUtil();
	/**
	 * 根据作者id查询每页显示的新闻
	 * @param page	当前的页数
	 * @param size	每页显示文章的数量
	 * @param userid	文章作者的id
	 * @return list 返回查询到的每页的文章
	 * @since 2018年9月25日 20:44:33
	 */
	@Override
	public List<Article> queryArticleForPage(Integer page, Integer size,Integer userid) {
		ResultSet rs = util.doQuery("select * from t_article where article_user=? order by article_time desc limit ?,?", userid,(page-1)*size,size);
		List<Article> list = new ArrayList<Article>();
		try {
			while(rs.next()){
				Article article = new Article();
				article.setArticle_content(rs.getString("article_content"));
				article.setArticle_id(rs.getInt("article_id"));
				article.setArticle_image(rs.getString("article_image"));
				article.setArticle_time(rs.getString("article_time"));
				article.setArticle_title(rs.getString("article_title"));
				
				int typeid = rs.getInt("article_type");
				//根据文章类型id，查询对应的文章对象
				TypeDao tdao = new TypeDaoImpl();
				Type type = tdao.queryById(typeid);
				//将查询到的对应typeid的类型对象放入到文章对象中
				article.setArticle_type(type);
				
				int uid = rs.getInt("article_user");
				//根据用户（作者）id查询返回用户对象
				UserDao dao = new UserDaoImpl();
				User user = dao.queryById(uid);
				article.setArticle_user(user);
				
				list.add(article);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.doClose(rs);
		}
		return list;
	}
	
	/**
	 * 根据某个作者的id查找这个作者的所有文章的数量
	 * @param userid 	某个作者的id
	 * @return count	返回文章的总条数
	 */
	@Override
	public long queryArticleCount(Integer userid) {
		long count = 0;
		ResultSet rs = util.doQuery("select count(*) as mynum from t_article where article_user =?", userid);
		try {
			if(rs.next()){
				count = rs.getLong("mynum");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.doClose(rs);
		}
		return count;
	}

	/**
	 * 根据传入文章的id删除对应的文章
	 * @param id	要删除的文章的id
	 * @return boolean	删除成功返回true
	 * @since 2018年9月26日 12:24:38
	 */
	@Override
	public boolean delete(Integer id) {
		
		return util.doUpdate("delete from t_article where article_id=?", id);
	}

	/**
	 * 编辑文章时根据id查找对应的文章
	 * @param articleid	要编辑的文章的id
	 * @return Article	查找到的文章的对象
	 * @since	2018年9月26日 12:56:02
	 */
	@Override
	public Article queryArticleById(Integer articleid) {
		int id = articleid;
		ResultSet rs = util.doQuery("select * from t_article where article_id=?", articleid);
		Article article = null;
		try {
			while(rs.next()){
				article = new Article();
				article.setArticle_content(rs.getString("article_content"));
				article.setArticle_id(rs.getInt("article_id"));
				article.setArticle_image(rs.getString("article_image"));
				article.setArticle_time(rs.getString("article_time"));
				article.setArticle_title(rs.getString("article_title"));
				
				int typeid = rs.getInt("article_type");
				TypeDao tdao = new TypeDaoImpl();
				//根据文章类型的id，查找到对应的类型对象
				Type type = tdao.queryById(typeid);
				//将查询到的对应的类型对象放入到文章对象中
				article.setArticle_type(type);	
				
				int uid = rs.getInt("article_user");
				UserDao dao = new UserDaoImpl();
				//根据作者的id查询对应的作者的对象
				User user = dao.queryById(uid);
				//将作者（用户）对象放入文章对象中
				article.setArticle_user(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.doClose(rs);
		}
		return article;
	}

	/**
	 * 添加文章
	 * @param article	要添加的新闻的对象
	 * @return boolean	添加成功返回true
	 * @since	2018年9月26日 20:38:02
	 */
	@Override
	public boolean addArticle(Article article) {
		
		return util.doUpdate("insert into t_article(article_title,article_content,article_user,article_type,article_image,article_time) values(?,?,?,?,?,?)", article.getArticle_title(),article.getArticle_content(),article.getArticle_user().getUser_id(),article.getArticle_type().getType_id(),article.getArticle_image(),article.getArticle_time());
	}

	
	/**
	 * 保存修改的文章
	 * @param article	要修改的文章的对象
	 * @return boolean	修改文章成功则返回true
	 * @since	2018年9月27日 23:09:09
	 */
	@Override
	public boolean updateArticle(Article article) {
		
		return util.doUpdate("update t_article set article_title=?,article_content=?,article_type=?,article_time=? where article_id=?", article.getArticle_title(),article.getArticle_content(),article.getArticle_type().getType_id(),article.getArticle_time(),article.getArticle_id());
	}

}
