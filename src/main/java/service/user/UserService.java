package service.user;

import com.alibaba.fastjson.JSON;
import domain.User;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * @author
 */
public class UserService {
    @Autowired
    @Qualifier("sqlSessionFactory")
    private SqlSessionFactory sessionFactory;
    @Autowired
    private SqlSession session;
    @Autowired
    private UserMapper userMapper;

    public int insertUser(User user) {
        SqlSession openSession = sessionFactory.openSession();
        UserMapper mapper = openSession.getMapper(UserMapper.class);
        return mapper.insertUser(user);

    }

    public User queryUser(User user) {
        SqlSession openSession = sessionFactory.openSession();
        UserMapper mapper = openSession.getMapper(UserMapper.class);
        System.out.println("class UserService hsd been executed!");
        return mapper.queryUser(user);
    }

    public int updateUser(User user) {
        SqlSession openSession = sessionFactory.openSession();
        UserMapper mapper = openSession.getMapper(UserMapper.class);
        int res = mapper.updateUser(user);
        openSession.commit();
        openSession.close();
        return res;
    }
}
