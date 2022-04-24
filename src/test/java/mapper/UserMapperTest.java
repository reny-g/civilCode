package mapper;

import com.alibaba.fastjson.JSON;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.user.UserService;

import java.io.IOException;
import java.io.InputStream;

public class UserMapperTest {

    private SqlSessionFactory sessionFactory = null;
    @Before
    public void init() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void insertUser() {
        SqlSession openSession = sessionFactory.openSession();
        UserMapper mapper = openSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUserEmail("1517979@139.com");
        user.setUserPassword("12345");
        int res = mapper.insertUser(user);
        openSession.commit();
        System.out.println(res);
    }

    @Test
    public void queryUser() {
        SqlSession openSession = sessionFactory.openSession();
        UserMapper mapper = openSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUserEmail("2507218357@qq.com");
        user.setUserPassword("123456");
        System.out.println(JSON.toJSON(user));
        User res = mapper.queryUser(user);
        System.out.println("UserMapperTest:"+JSON.toJSON(res));
    }

    @Test
    public void updateUser() {
        SqlSession openSession = sessionFactory.openSession();
        UserMapper mapper = openSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUserEmail("1517979@139.com");
        user.setUserPassword("19999999");
        int res = mapper.updateUser(user);
        openSession.commit();
        openSession.close();
        System.out.println(res);
    }
}