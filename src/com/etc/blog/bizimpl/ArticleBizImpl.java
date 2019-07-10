package com.etc.blog.bizimpl;

import java.util.List;

import com.etc.blog.biz.ArticleBiz;
import com.etc.blog.dao.ArticleDao;
import com.etc.blog.daoimpl.ArticleDaoImpl;
import com.etc.blog.entity.Article;

public class ArticleBizImpl implements ArticleBiz{

	ArticleDao dao = new ArticleDaoImpl();
	
	//获取某个作者的文章的总数
	@Override
	public long getArticleCount(Integer userid) {
		
		return dao.queryArticleCount(userid);
	}
	
	//根据作者id查询某一页的文章
	@Override
	public List<Article> queryArticleForPage(Integer page, Integer size,
			Integer userid) {
		return dao.queryArticleForPage(page, size, userid);
	}

	//根据文章id删除对应的文章
	@Override
	public boolean delete(Integer id) {
		return dao.delete(id);
	}

	//编辑文章--根据id查询对应文章
	@Override
	public Article queryArticleById(Integer articleid) {
		return dao.queryArticleById(articleid);
	}

	//添加新闻
	@Override
	public boolean addArticle(Article article) {
		return dao.addArticle(article);
	}

	//编辑文章
	@Override
	public boolean updateArticle(Article article) {
		return dao.updateArticle(article);
	}

}
