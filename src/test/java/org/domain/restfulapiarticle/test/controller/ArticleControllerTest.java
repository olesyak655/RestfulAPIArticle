package org.domain.restfulapiarticle.test.controller;

import java.util.Arrays;

import org.domain.restfulapiarticle.entity.Article;
import org.domain.restfulapiarticle.exception.ArticleNotFoundException;
import org.domain.restfulapiarticle.service.ArticleService;
import org.domain.restfulapiarticle.test.config.TestContext;
import org.domain.restfulapiarticle.test.config.TestUtil;
import org.domain.restfulapiarticle.test.config.WebAppContext;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static junit.framework.Assert.assertNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class ArticleControllerTest {

	private MockMvc mockMvc;
	 
    @Autowired
    private ArticleService articleServiceMock;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
 
    @Before
    public void setUp() {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(articleServiceMock);
 
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void findAll_ArticlesFound_ShouldReturnFoundArticleEntries() throws Exception {
        Article first = new Article();
        first.setId(1);
        first.setTitleArticle("Title_1");
        first.setBodyArticle("Body_Article_1");
        first.setAuthor("Author_1");
       
        Article second = new Article();
        first.setId(2);
        first.setTitleArticle("Title_2");
        first.setBodyArticle("Body_Article_2");
        first.setAuthor("Author_2");
 
        when(articleServiceMock.findAll()).thenReturn(Arrays.asList(first, second));
 
        mockMvc.perform(get("/restful/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].titleArticle", is("Title_1")))
                .andExpect(jsonPath("$[0].bodyArticle", is("Body_Article_1")))
                .andExpect(jsonPath("$[0].author", is("Author_1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].titleArticle", is("Title_2")))
                .andExpect(jsonPath("$[1].bodyArticle", is("Body_Article_2")))
                .andExpect(jsonPath("$[1].author", is("Author_2")));
 
        verify(articleServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(articleServiceMock);
    }
    
    @Test
    public void findById_ArticleEntryNotFound_ShouldReturnHttpStatusCode404() throws Exception {
        when(articleServiceMock.findById(1)).thenThrow(new ArticleNotFoundException(""));
 
        mockMvc.perform(get("/restful/articles/{id}", 1))
                .andExpect(status().isNotFound());
 
        verify(articleServiceMock, times(1)).findById(1L);
        verifyNoMoreInteractions(articleServiceMock);
    }
    
    @Test
    public void findById_ArticleEntryFound_ShouldReturnFoundArticleEntry() throws Exception {
    	Article found = new Article();
        found.setId(1);
        found.setTitleArticle("Title_1");
        found.setBodyArticle("Body_Article_1");
        found.setAuthor("Author_1");
 
        when(articleServiceMock.findById(1)).thenReturn(found);
 
        mockMvc.perform(get("/restful/articles/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.titleArticle", is("Title_1")))
                .andExpect(jsonPath("$.bodyArticle", is("Body_Article_1")))
                .andExpect(jsonPath("$.author", is("Author_1")));
 
        verify(articleServiceMock, times(1)).findById(1L);
        verifyNoMoreInteractions(articleServiceMock);
    }
    
    @Test
    public void add_NewArticleEntry_ShouldAddArticleEntryAndReturnAddedEntry() throws Exception {
        DateTime now = new DateTime();
    	
    	Article article1 = new Article();
        article1.setTitleArticle("Title_1");
        article1.setBodyArticle("Body_Article_1");
        article1.setAuthor("Author_1"); 
        article1.setDateCreate(now);
        article1.setDateUpdate(now);
        
        Article article2 = new Article();
        article2.setId(1);
        article2.setTitleArticle("Title_1");
        article2.setBodyArticle("Body_Article_1");
        article2.setAuthor("Author_1"); 
        article2.setDateCreate(now);
        article2.setDateUpdate(now);
 
        when(articleServiceMock.save(article1)).thenReturn(article2);
 
        mockMvc.perform(post("/restful/articles")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(article1))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.titleArticle", is("Title_1")))
                .andExpect(jsonPath("$.bodyArticle", is("Body_Article_1")))
                .andExpect(jsonPath("$.author", is("Author_1")));
 
        ArgumentCaptor<Article> articleCaptor = ArgumentCaptor.forClass(Article.class);
        verify(articleServiceMock, times(1)).save(articleCaptor.capture());
        verifyNoMoreInteractions(articleServiceMock);
 
        Article articleArgument = articleCaptor.getValue();
        assertNull(articleArgument.getId());
        assertThat(articleArgument.getTitleArticle(), is("Title_1"));
        assertThat(articleArgument.getBodyArticle(), is("Body_Article_1"));
        assertThat(articleArgument.getAuthor(), is("Author_1"));
    }
}
