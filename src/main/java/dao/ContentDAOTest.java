package dao;

import com.alibaba.fastjson.JSON;
import domain.OneLaw;

import java.sql.SQLException;

/**
 *为了返回裁判文书的内容
 */
public class ContentDAOTest {

    @org.junit.Test
    public void queryContent() throws SQLException {
        System.out.println(JSON.toJSON(new ContentDAO().
                queryContent(new OneLaw(97, null, null))));;
    }
}
