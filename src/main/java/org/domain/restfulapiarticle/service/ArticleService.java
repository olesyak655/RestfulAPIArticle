package org.domain.restfulapiarticle.service;

import java.util.List;
import org.domain.restfulapiarticle.entity.Article;

public interface ArticleService {
	public List<Article> findAll();
	public Article findById(long id);
	public Article save(Article article);
	public void delete(long id);
}
