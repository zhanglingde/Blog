package com.etc.blog.dao;

import java.util.List;

import com.etc.blog.entity.Article;

public interface ArticleDao {

	//根据作者id查询某一页的文章
	public List<Article> queryArticleForPage(Integer page,Integer size,Integer userid);
	
	//查询某个作者的文章的总数
	public long queryArticleCount(Integer userid);
	
	//删除文章
	public boolean delete(Integer id);
	
	//编辑文章时根据文章的id查找某篇文章的详情
	public Article queryArticleById(Integer articleid);
	
	//添加文章
	public boolean addArticle(Article article);
	
	//编辑文章
	public boolean updateArticle(Article article);
}
