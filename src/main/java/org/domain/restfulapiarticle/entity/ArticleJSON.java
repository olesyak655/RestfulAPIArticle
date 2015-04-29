package org.domain.restfulapiarticle.entity;

public class ArticleJSON {
	
	private long id;
	private String titleArticle;
	private String bodyArticle;
	private String author;
	
	public ArticleJSON() {
		
	}
	
	public ArticleJSON(Article article) {
		super();
		this.id = article.getId();
		this.titleArticle = article.getTitleArticle();
		this.bodyArticle = article.getBodyArticle();
		this.author = article.getAuthor();
	}

	public long getId() {
		return id;
	}
	public String getTitleArticle() {
		return titleArticle;
	}
	public String getBodyArticle() {
		return bodyArticle;
	}
	public String getAuthor() {
		return author;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setTitleArticle(String titleArticle) {
		this.titleArticle = titleArticle;
	}
	public void setBodyArticle(String bodyArticle) {
		this.bodyArticle = bodyArticle;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}
