package service.ES.indexDocApi;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import domain.SearchInfo;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import service.ES.indexApi.*;
import utils.SearchSetting;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocCRUD {

    //存储搜索信息
    public static SearchInfo searchInfo =null;
    //存储搜索结果
    public static List<Map<String,Object>> searchResult = null;
    //存储搜索信息
    static String keyword=null;
    static int pageSize = 0;

    RestHighLevelClient client = new IndexCRUD().init();

    public void search() throws IOException {
        SearchRequest request = new SearchRequest("laws");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //以下为全文匹配查询
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("contentName","财产权利");
        matchQueryBuilder.fuzziness(Fuzziness.AUTO);

        searchSourceBuilder.query(matchQueryBuilder);
        searchSourceBuilder.from(0);
        //设置最大返回结果数
        searchSourceBuilder.size(60);
        //按id进行ASC排序
        searchSourceBuilder.sort(new FieldSortBuilder("id").order(SortOrder.ASC));
        //高亮显示
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field highlightTitle =
                new HighlightBuilder.Field("subPartName");
        highlightTitle.highlighterType("unified");
        highlightBuilder.field(highlightTitle);
        HighlightBuilder.Field highlightUser = new HighlightBuilder.Field("id");
        highlightBuilder.field(highlightUser);
        searchSourceBuilder.highlighter(highlightBuilder);

        request.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        //System.out.println(JSON.toJSON(searchResponse.getHits()));

        SearchHits hits = searchResponse.getHits();
        TotalHits totalHits = hits.getTotalHits();
        // the total number of hits, must be interpreted in the context of totalHits.relation
        long numHits = totalHits.value;
        System.out.println(numHits+"=="+searchResponse.getHits().getHits().length);

        int i=0;
        for (SearchHit documentFields : searchResponse.getHits()) {
            System.out.println(++i+"-"+documentFields.getSourceAsMap());
        }
    }

    public List<Map<String,Object>> search(String keyword,int pageNo,int pageSize) throws IOException {
        if (pageNo<=1) {
            pageNo=1;
        }
        //获得索引
        SearchRequest searchRequest = new SearchRequest("laws");

        //构造查询容器
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //分页
        sourceBuilder.from(pageNo);
        sourceBuilder.size(pageSize);

        //构造查询条件
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("contentName",keyword);
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        sourceBuilder.query(matchQueryBuilder);
        searchRequest.source(sourceBuilder);

        //执行查询
        SearchResponse response = client.search(searchRequest,RequestOptions.DEFAULT);
        //封装返回数据
        List<Map<String,Object>> list = new ArrayList<>();
        for (SearchHit documentFields: response.getHits().getHits()) {
            list.add(documentFields.getSourceAsMap());
            System.out.println("--"+JSON.toJSON(documentFields.getSourceAsMap()));
        }
        client.close();
    return list;
    }

    public List<Map<String,Object>> searchLighter(String keyword,int pageNo,int pageSize) throws IOException {
        if (pageNo<=1) {
            pageNo=1;
        }
        DocCRUD.keyword = keyword;

        //获得索引
        SearchRequest searchRequest = new SearchRequest("laws");

        //构造查询容器
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //分页$6$VvlQo/jco3T5KhxM$2XsGIvjeM4mCIrrEnE42DSN7C8utNaRDbfJhpZkD1.w1nf8n9WZ8l213q8QGcrSzUTmuElWPjqU9oxQWeVBYQ0
        sourceBuilder.from(pageNo);
        sourceBuilder.size(pageSize);

        //构造查询条件
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("contentName",keyword);
        //匹配度 and和or中选择相适应的相似度
        matchQueryBuilder.minimumShouldMatch(SearchSetting.minimumShouldMatch);
        matchQueryBuilder.analyzer("ik_max_word");
        sourceBuilder.query(matchQueryBuilder);

        //高亮容器
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field highlightTitle =
                new HighlightBuilder.Field("contentName");
        highlightTitle.highlighterType("unified");
        highlightBuilder.requireFieldMatch(false);
        highlightTitle.preTags("<span style='color:red;'>");
        highlightTitle.postTags("</span>");
        highlightBuilder.field(highlightTitle);
        sourceBuilder.highlighter(highlightBuilder);

        //执行查询
        searchRequest.source(sourceBuilder);
        SearchResponse response = client.search(searchRequest,RequestOptions.DEFAULT);

        //查询总数
        SearchHits hits = response.getHits();
        TotalHits totalHits = hits.getTotalHits();
        long numHits = totalHits.value;
        searchInfo = new SearchInfo(response.getTook().secondsFrac(),(int)numHits);
        System.out.println("searchinfo:"+searchInfo.getTotalNum()+"--"+searchInfo.getTime());
        //封装返回数据
        List<Map<String,Object>> list = new ArrayList<>();
        for (SearchHit documentFields: response.getHits().getHits()) {
            Map<String,HighlightField> highlightBuilderMap = documentFields.getHighlightFields();
            HighlightField title = highlightBuilderMap.get("contentName");
            Map<String,Object> sourceAsMap = documentFields.getSourceAsMap();
            //解析高亮字段，做个替换
            if (title!=null) {
                Text[] fragments = title.fragments();
                String nTitle = "";
                for (Text text: fragments) {
                    nTitle += text;
                }
                //高亮字段替换原来的内容,每一个map中存放的都是一个文档（即一条记录）
                sourceAsMap.put("contentName",nTitle);
                //System.out.println(JSON.toJSON(sourceAsMap.get("id"))+nTitle);
             }
            list.add(sourceAsMap);
        }
        client.close();
        searchResult = list;
        return list;
    }

    /**
     * 为图表构建数据，设计具体法律条文的id范围以及搜索关键字
     * @return
     * @throws IOException
     */
    public static Map<String,Object> getJsonForChartPie() throws IOException {
        if (searchResult==null) {
            System.out.println("searchResult is null!");
            return null;
        } else {
            //获得索引
            SearchRequest searchRequest = new SearchRequest("laws");

            //构造查询容器
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

            //构造查询条件
            MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("contentName", DocCRUD.keyword);
            sourceBuilder.size(1261);
            //匹配度 and和or中选择相适应的相似度
            matchQueryBuilder.minimumShouldMatch(SearchSetting.minimumShouldMatch);
            matchQueryBuilder.analyzer("ik_max_word");
            sourceBuilder.query(matchQueryBuilder);

            //执行查询
            searchRequest.source(sourceBuilder);
            SearchResponse response = new IndexCRUD().init().search(searchRequest,RequestOptions.DEFAULT);

            Map<String,Object> jsonMap = new HashMap<>(7);
            int first=0,second=0,third=0,four=0,five=0,six=0,seven=0;

            System.out.println("长度："+response.getHits().getHits().length);
            for (SearchHit documentFields: response.getHits().getHits()) {
                if (Integer.parseInt((String) (documentFields.getSourceAsMap().get("id").toString()))<=420) {
                    first++;
                } else if (Integer.parseInt((String) (documentFields.getSourceAsMap().get("id").toString()))<=911) {
                    second++;
                } else if (Integer.parseInt((String) (documentFields.getSourceAsMap().get("id").toString()))<=1822) {
                    third++;
                } else if (Integer.parseInt((String) (documentFields.getSourceAsMap().get("id").toString()))<=1939) {
                    four++;
                } else if (Integer.parseInt((String) (documentFields.getSourceAsMap().get("id").toString()))<=2121) {
                    five++;
                } else if (Integer.parseInt((String) (documentFields.getSourceAsMap().get("id").toString()))<=2224) {
                    six++;
                } else {
                    seven++;
                }
            }
            jsonMap.put("fone",first);
            jsonMap.put("two",second);
            jsonMap.put("three",third);
            jsonMap.put("four",four);
            jsonMap.put("five",five);
            jsonMap.put("six",six);
            jsonMap.put("seven",seven);

            return jsonMap;
        }
    }

    public <E> Boolean insert(E e) throws IOException {
//        获得索引
        IndexRequest indexRequest = new IndexRequest("laws");
//        添加到请求数据源
        System.out.println(new Gson().toJson(e));
        System.out.println(new Gson().toJson(e));
        indexRequest.source(new Gson().toJson(e), XContentType.JSON);

        //执行请求
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse.toString());
        return true;
    }

    public static SearchInfo getSearchInfo() {
        System.out.println(JSON.toJSON(searchInfo));
        if (searchInfo!=null) {
            System.out.println("get searchInfo");
            return searchInfo;
        } else {
            System.out.println("searchInfo is null");
            return null;
        }
    }
}

