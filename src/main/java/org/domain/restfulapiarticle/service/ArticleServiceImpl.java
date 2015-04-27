package org.domain.restfulapiarticle.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.domain.restfulapiarticle.entity.Article;
import org.domain.restfulapiarticle.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("articleService") 
@Repository 
@Transactional 
public class ArticleServiceImpl implements ArticleService {
	private Log log = LogFactory.getLog(ArticleServiceImpl.class); 
	
//	@PersistenceContext 
//	private EntityManager em; 

	//@Autowired 
	private ArticleRepository articleRepository;	
	
	public ArticleServiceImpl() {
		
	}
	
	public ArticleServiceImpl(ArticleRepository repository) {
        this.articleRepository = repository;
    }
	
	@Override
	@Transactional (readOnly=true)
	public List<Article> findAll() {
		List<Article> articles = (List<Article>) articleRepository.findAll();
		return articles;
	}

	@Override
	@Transactional (readOnly=true) 
	public Article findById(long id) {
		Article article = articleRepository.findOne(id);
		return article; 
	}

	@Override
	public Article save(Article article) {
		Article newArticle = articleRepository.save(article);
		return newArticle;
	}
	
	@Override
	public void delete(long id) {
		articleRepository.delete(id);
	}

}
