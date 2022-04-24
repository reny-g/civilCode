package service.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import domain.User;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import service.IUserService;


@Service("userService")
@Scope("SCOPE_PROTOTYPE")
public class IUserviceImpl implements IUserService {
    @Autowired
    @Qualifier("sqlSessionFactory")
    private SqlSessionFactory sessionFactory;
    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        SqlSession openSession = sessionFactory.openSession();
        UserMapper mapper = openSession.getMapper(UserMapper.class);
        int res = mapper.insertUser(user);
        openSession.commit();
        openSession.close();
        return res;
    }

    @Override
    public User queryUser(User user) {
        SqlSession openSession = sessionFactory.openSession();
        UserMapper mapper = openSession.getMapper(UserMapper.class);
        User user1 = mapper.queryUser(user);
        openSession.close();
        System.out.println("IUserviceImpl has been executed!");
        return user1;
    }

    @Override
    public int updateUser(User user) {
        SqlSession openSession = sessionFactory.openSession();
        UserMapper mapper = openSession.getMapper(UserMapper.class);
        int res = mapper.updateUser(user);
        openSession.commit();
        openSession.close();
        return res;
    }

    @Override
    public Boolean queryUserAccount(User user) {
        SqlSession sqlSession = sessionFactory.openSession();
        //UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return  userMapper.queryUserAccount(user);
    }
}
