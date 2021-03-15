package dao;

import com.alibaba.fastjson.JSON;
import domain.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;


public class UserDAOTest {

    @Test
    public void queryUser() throws SQLException {
        System.out.println(JSON.toJSON(new UserDAO().queryUser(new User("15179793897","1254oo"))));;
    }
}