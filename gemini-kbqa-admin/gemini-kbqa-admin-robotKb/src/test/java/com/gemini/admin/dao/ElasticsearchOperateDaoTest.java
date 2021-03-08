package com.gemini.admin.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @Author: XXY
 * @Date: 2021/2/27 21:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchOperateDaoTest {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Test
    public void testEs(){

    }

    //检查相应的索引是否存在，如果spring.data.elasticsearch.repositories.enabled=True,则会自动创建索引
    public boolean checkIndexExists(Class<?> cls){
        boolean isExist = elasticsearchRestTemplate.indexOps(cls).exists();
        //获取索引名
        String indexName = cls.getAnnotation(Document.class).indexName();
        System.out.printf("index %s is %s\n", indexName, isExist ? "exist" : "not exist");
        return isExist;
    }
    @Test
    public void checkIndex() {
        checkIndexExists(Article.class);
    }

    @Test
    public void save(){
        Article article = new Article("Spring Data Elasticsearch");
        article.setAuthors(Arrays.asList(new Author("LaoAlex"),new Author("John")));
        articleRepository.save(article);

        article = new Article("Spring Data Elasticsearch2");
        article.setAuthors(Arrays.asList(new Author("LaoAlex"),new Author("King")));

        articleRepository.save(article);
        article = new Article("Spring Data Elasticsearch3");
        article.setAuthors(Arrays.asList(new Author("LaoAlex"),new Author("Bill")));
        articleRepository.save(article);
    }

    @Test
    public void queryAuthorName() throws JsonProcessingException {
        Page<Article> articles = articleRepository.findByAuthorsName("LaoAlex", PageRequest.of(0,10));
        //将对象转为Json字符串
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(articles);
        System.out.println(json);
        articles = articleRepository.findByTitleIsContaining("Spring", PageRequest.of(0,10));
        objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        json = objectWriter.writeValueAsString(articles);
        System.out.println(json);
    }

    //使用自定义查询
    @Test
    public void queryAuthorNameByCustom() throws JsonProcessingException {
        Page<Article> articles = articleRepository.findByAuthorsNameUsingCustomQuery("J",PageRequest.of(0,10));
        //将对象转为Json字符串
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(articles);
        System.out.println(json);
    }
}
