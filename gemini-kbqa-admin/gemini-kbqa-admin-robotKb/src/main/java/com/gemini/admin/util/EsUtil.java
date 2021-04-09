package com.gemini.admin.util; /**
 * Copyright (c) 2021,CHENGJIINFORMATION TECHNOLOGY(SHANGHAI) O.,LTD  All Rights Reserved.
 */

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName EsUtil
 * @Description es操作工具类
 * @Author Gu YuLong
 * @Date 2021/3/10 9:48
 * @Version 1.0
 */
public class EsUtil {

    /**
     * @Author Gu YuLong
     * @Description ES查询超时时间
     */
    public static int SEARCH_TIME_OUT = 60;
    // 配置文件
    public static String ES_HOST = "129.211.159.145";
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


    /**
     * @author Gu YuLong
     * @description 查询es内问题
     * @Param: val
     * @Param: page
     * @Param: pageSize
     * @date 2021/3/10 9:49
     * @return void
     */
    public static List<Map<String, Object>> queryByQuestion(String val, Integer page, Integer pageSize) throws IOException {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        builder.must(QueryBuilders.matchPhraseQuery("question", WILDCARD + val + WILDCARD));
        sourceBuilder.query(builder);
        sourceBuilder.from((page-1)*pageSize);
        sourceBuilder.size(pageSize);
        sourceBuilder.timeout(new TimeValue(SEARCH_TIME_OUT, TimeUnit.SECONDS));
       /* HighlightBuilder highlightBuilder = new HighlightBuilder().field("question").requireFieldMatch(false);
        highlightBuilder.preTags("<em style='color:red'>")
                .postTags("</em>");

        sourceBuilder.highlighter(highlightBuilder);*/

        SearchRequest searchRequest = new SearchRequest("question");
        searchRequest.types("qdocument");
        searchRequest.source(sourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        List<Map<String, Object>> list = new ArrayList();
        for(SearchHit hit: hits){
            Map<String, Object> map = hit.getSourceAsMap();
            //disposeHighlighter(hit, map);
            list.add(map);
        }
        return list;
    }

    /**
     * @author Gu YuLong
     * @description 拼接高亮字段
     * @Param: hit
     * @Param: source
     * @date 2021/3/10 9:48
     * @return void
     */
    public static void disposeHighlighter(SearchHit hit, Map<String, Object> source) {
        Map<String, HighlightField> highlightFields = hit.getHighlightFields();
        Set<String> ketSet = highlightFields.keySet();
        for (String element : ketSet) {
            HighlightField highlightField = highlightFields.get(element);
            if (highlightField != null) {
                Text[] fragments = highlightField.fragments();
                StringBuilder nameTmp = new StringBuilder();
                for (Text text : fragments) {
                    nameTmp.append(text);
                }
                //将高亮片段组装到结果中去
                source.put(element, nameTmp.toString());
            }

        }
    }

}
