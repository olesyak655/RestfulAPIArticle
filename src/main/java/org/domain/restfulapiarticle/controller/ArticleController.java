package org.domain.restfulapiarticle.controller;

import java.util.List;

import org.domain.restfulapiarticle.entity.Article;
import org.domain.restfulapiarticle.entity.Articles;
import org.domain.restfulapiarticle.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value="/articles")
@Controller
public class ArticleController {
	final Logger logger = LoggerFactory.getLogger(ArticleController.class); 
	
	@Autowired 
	private ArticleService articleService;
	
	public ArticleController() {
		
	}
	
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Articles articleList(Model uiModel) {
		logger.info("Listing Article");
		return new Articles(articleService.findAll());
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody Article article(@PathVariable Long id) {
		logger.info("Article");
		Article article = articleService.findById(id);
		return article;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST) 
	@ResponseBody 
	public Article create(@RequestBody Article article) { 
		articleService.save(article); 
		logger.info("Article created successfully with info: " + article); 
		return article;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT) 
	@ResponseBody 
	public void update(@RequestBody Article article, @PathVariable Long id) { 
		articleService.save(article); 
		logger.info("Contact updated successfully with info: + article"); 
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE) 
	@ResponseBody 
	public void delete(@PathVariable Long id) { 
		articleService.delete(id); 
		logger.info("Contact deleted successfully");
		
	} 



	
}
