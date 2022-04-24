package service.ES.indexApi;

import com.google.gson.Gson;
import domain.OneLaw;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.AnalyzeRequest;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;

import utils.JdbcUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author 吴仁杨
 */
public class IndexCRUD {

    RestHighLevelClient client;

    public RestHighLevelClient init() {
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
        return client;
    }

    /**
     * 从数据库表获得相关数据创建成索引,包含初始文档
     * @param tableName 表名
     * @param fields 字段名
     * @return
     */
    public Boolean createIndexFromTable(String indexName,String tableName,String... fields) throws SQLException, IOException {
        //1，获取数据表中数据
        Connection connection = JdbcUtils.getConn();
        String sql = "select ?";
        for (int i = 0; i < fields.length-1; i++) {
            sql+=",?";
        }
        sql+=" from "+tableName;
        sql = "select * from "+tableName;
        System.out.println(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i=0; i<fields.length; i++) {
            //preparedStatement.setObject(i+1,fields[i]);
            //System.out.print("the retire column is:"+fields[i]);
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        //2，创建请求索引
        client  = init();
        IndexRequest indexRequest = new IndexRequest(indexName);
        CreateIndexRequest request = new CreateIndexRequest(indexName);

        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.startObject("properties");
            {
//                //ID文档字段
//                builder.startObject("id");
//                {
//                    builder.field("type", "long");
//                }
//                builder.endObject();
//                //创建content文档字段
//                builder.startObject("content");
//                {
//                    builder.field("type", "text")
//                            //插入时分词
//                            .field("analyzer", "ik_smart")
//                            //搜索时分词
//                            .field("search_analyzer", "ik_max_word");
//                }
//                builder.endObject();
//                //创建contentName文档字段
//                builder.startObject("contentName");
//                {
//                    builder.field("type", "text")
//                            //插入时分词
//                            .field("analyzer", "ik_max_word")
//                            //搜索时分词
//                            .field("search_analyzer", "ik_smart");
//                }
//                builder.endObject();
                for (String field : fields) {
                builder.startObject(field);
                {
                    builder.field("type", "text")
                            //插入时分词
                            .field("analyzer", "ik_max_word")
                            //搜索时分词
                            .field("search_analyzer", "ik_smart");
                }
                builder.endObject();
                }
            }
            builder.endObject();
        }
        builder.endObject();
        request.mapping(builder);
        //2客户端执行请求，请求后获得响应
        CreateIndexResponse response2 = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response2);
        IndexResponse response1 = null;
        //注：以下内容还未实现自动化，比如从resultset中获得的行数是固定的，oneLw对象也是固定的
        int j=0;
        while (resultSet.next()) {

            OneLaw oneLaw = new OneLaw(resultSet.getInt(fields[0]),resultSet.getString(fields[1]),resultSet.getString(fields[2]));
            
            indexRequest.source(new Gson().toJson(oneLaw), XContentType.JSON)
                        .id(String.valueOf(resultSet.getInt(fields[0])));
            System.out.println(new Gson().toJson(oneLaw));
            response1 = client.index(indexRequest,RequestOptions.DEFAULT);
        }
        JdbcUtils.freeAllResources(connection,preparedStatement,resultSet);
        System.out.println("isAcknowledged: "+response2.isAcknowledged());

        //3，初始化文档
        return true;
    }

    public static XContentBuilder getMapping(){
        XContentBuilder mapping = null;
        try {
            mapping = XContentFactory.jsonBuilder()
                    .startObject()
                    //关闭TTL
                    .startObject("_ttl")
                    .field("enabled",false)
                    .endObject()
                    .startObject("properties")
                    .startObject("contentName")
                    .field("type","text")
                    .field("store", "yes")
                    //指定index analyzer 为 ik
                    .field("analyzer", "ik_max_word")
                    //指定search_analyzer 为ik_syno
                    .field("search_analyzer", "ik_smart")
                    .endObject()
                    .endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapping;
    }

    /**
     * 测试索引是否存在
     * @param index 索引名称
     * @return
     * @throws IOException
     */
    public Boolean IsExistIndex(String index) throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest(index);
        client = init();
        return client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
    }

    public Boolean deleteIndex(String index) throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest(index);
        client = init();
        if (client.indices().exists(getIndexRequest,RequestOptions.DEFAULT)) {
            DeleteIndexRequest deleteRequest = new DeleteIndexRequest(index);
            AcknowledgedResponse deleteResponse = client.indices().delete(deleteRequest,RequestOptions.DEFAULT);
            System.out.println("delete "+deleteResponse.isAcknowledged());
            return true;
        } else {
            System.out.println("index isn't exist!");
            return false;
        }
    }

}
