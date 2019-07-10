package com.etc.blog.biz;

import java.util.List;

import com.etc.blog.entity.Article;

public interface ArticleBiz {
	
	//获取某个作者的文章的总数
	public long getArticleCount(Integer userid);
	
	//根据作者id查询某一页的文章
	public List<Article> queryArticleForPage(Integer page,Integer size,Integer userid);
	
	//删除文章--根据文章id删除对应的文章
	public boolean delete(Integer id);
	
	//编辑文章--根据id查询对应文章
	public Article queryArticleById(Integer articleid);
	
	//添加文章
	public boolean addArticle(Article article);
	
	//编辑文章
	public boolean updateArticle(Article article);
}
