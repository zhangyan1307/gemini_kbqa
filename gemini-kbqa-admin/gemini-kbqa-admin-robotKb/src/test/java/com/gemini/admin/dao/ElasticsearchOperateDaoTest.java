package com.gemini.admin.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

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



    /**
     * @Author Gu YuLong
     * @Description ES查询超时时间
     */
    public static int SEARCH_TIME_OUT = 60;
    // 配置文件
    public static String ES_HOST = "192.168.1.131";
    public static int ES_PORT = 9200;
    // 通配符符号
    public static String WILDCARD = "*";

    /**
     * @author Gu YuLong
     * @description 初始化ES客户端
     * @date 2021/3/9 16:14
     * @return
     */
    public static RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                    new HttpHost(ES_HOST, ES_PORT)
            )
    );


    //测试查询自定义查询
    public void queryByQuestion(String val, Integer page, Integer pageSize) throws IOException {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        builder.must(QueryBuilders.matchPhraseQuery("question", WILDCARD + val + WILDCARD));
//        builder.must(QueryBuilders.wildcardQuery("question.keyword", WILDCARD + val + WILDCARD));
        sourceBuilder.query(builder);
        sourceBuilder.from((page-1)*pageSize);
        sourceBuilder.size(pageSize);
        sourceBuilder.timeout(new TimeValue(SEARCH_TIME_OUT, TimeUnit.SECONDS));
        HighlightBuilder highlightBuilder = new HighlightBuilder().field("question").requireFieldMatch(false);
        highlightBuilder.preTags("<em style='color:red'>")
                .postTags("</em>");

        sourceBuilder.highlighter(highlightBuilder);

        SearchRequest searchRequest = new SearchRequest("question");
        searchRequest.types("qdocument");
        searchRequest.source(sourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        List list = new ArrayList();
        for(SearchHit hit: hits){
            Map<String, Object> map = hit.getSourceAsMap();
            disposeHighlighter(hit, map);
            list.add(map);
        }
        list.forEach( m ->{
            System.out.println(m.toString());
        });
    }

    @Test
    public void query() throws IOException {
        String val = "测试用例";
        Integer page = 1;
        Integer pageSize = 10;
        queryByQuestion(val, page, pageSize);
    }


    public void disposeHighlighter(SearchHit hit, Map<String, Object> source) {
        Map<String, HighlightField> highlightFields = hit.getHighlightFields();
        Set<String> ketSet = highlightFields.keySet();
        for (String element : ketSet) {
            HighlightField highlightField = highlightFields.get(element);
            if (highlightField != null) {
                Text[] fragments = highlightField.fragments();
                String nameTmp = "";
                for (Text text : fragments) {
                    nameTmp += text;
                }
                //将高亮片段组装到结果中去
                source.put(element, nameTmp);
            }

        }
    }
}
