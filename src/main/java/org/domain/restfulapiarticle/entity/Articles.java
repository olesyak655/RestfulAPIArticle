package org.domain.restfulapiarticle.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Articles {

	private List<Article> articles;
	
	public Articles(List<Article> articles) {
		
		Set<Article> setArticle = new TreeSet<Article>(new ArticleComparator<Article>());
		for (Article article : articles) {
			setArticle.add(article);
		}

		this.articles = new ArrayList<Article>(setArticle);
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		Set<Article> setArticle = new TreeSet<Article>(new ArticleComparator<Article>());
		for (Article article : articles) {
			setArticle.add(article);
		}

		this.articles = new ArrayList<Article>(setArticle);
	}
	
	
}
