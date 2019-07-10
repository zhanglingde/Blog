package com.etc.blog.entity;

public class Article {

	private Integer article_id;
	private String article_title;
	private String article_content;
	private User article_user;//作者外键  ， 对象类型
	private Type article_type;//文章类型     外键对象类型
	private String article_time;
	private String article_image;
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	public String getArticle_title() {
		return article_title;
	}
	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}
	public String getArticle_content() {
		return article_content;
	}
	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}
	public User getArticle_user() {
		return article_user;
	}
	public void setArticle_user(User article_user) {
		this.article_user = article_user;
	}
	public Type getArticle_type() {
		return article_type;
	}
	public void setArticle_type(Type article_type) {
		this.article_type = article_type;
	}
	public String getArticle_time() {
		return article_time;
	}
	public void setArticle_time(String article_time) {
		this.article_time = article_time;
	}
	public String getArticle_image() {
		return article_image;
	}
	public void setArticle_image(String article_image) {
		this.article_image = article_image;
	}
	
	
	
}
