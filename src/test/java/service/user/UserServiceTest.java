package service.user;

import domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Test
    public void insertUser() {
    }

    @Test
    public void queryUser() {
        User user = new User();
        user.setUserPassword("123456");
        user.setUserEmail("15179793794@139.com");
        new UserService().queryUser(user);
    }

    @Test
    public void updateUser() {
    }
}