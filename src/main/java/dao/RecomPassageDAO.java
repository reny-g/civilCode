package dao;

import com.alibaba.fastjson.JSON;
import domain.Passage;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author 张宏沛
 */
public class RecomPassageDAO {
    /**
     * 功能：随机在数据库里取3篇文章，并封装在数组里返回
     * @return 推荐文章对象数组列表
     * @throws SQLException
     */
    public List<Passage> queryRecomPassage() throws SQLException {

        //获取数据库连接
        Connection connection = JdbcUtils.getConn();
        //构造sql查询语句
        String sql = "select    *    from    db_recommand_passage  order     by rand()  limit   3 ";
        //获取sql容器
        System.out.println(sql);
        Statement statement = connection.createStatement();
        //获取查询结果集
        ResultSet result = statement.executeQuery(sql);
        List<Passage> list = new ArrayList<>(3);
        //遍历结果集，生成对象
        while (result.next()) {
            //构造对象
            Passage passage = new Passage(result.getString("title"), (result.getDate("publish_date").toString()).substring(0,10),
                    result.getString("general_content"),result.getString("complete_content"),result.getString("source_name"),
                    result.getString("source_url"));
            //加入list
            list.add(passage);
        }
        //释放数据连接资源
        JdbcUtils.freeAllResources(connection,statement,result);
        return list;
    }
}
