package dao;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class RecomPassageDAOTest {

    @Test
    public void queryRecomPassage() throws SQLException {
        System.out.println(JSON.toJSON(new RecomPassageDAO().queryRecomPassage()));
    }
}