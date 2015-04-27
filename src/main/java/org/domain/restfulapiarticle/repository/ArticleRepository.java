package org.domain.restfulapiarticle.repository;

import java.util.List;

import org.domain.restfulapiarticle.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
        
    public List<Article> findAll();
    public Article findOne(String id);
    public Article save(Article article);
    public void delete(Long id);
	
}
