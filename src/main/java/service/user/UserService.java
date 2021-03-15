package service.user;

import dao.UserDAO;
import domain.User;
import org.w3c.dom.UserDataHandler;

import java.sql.SQLException;

/**
 * @author 杨文俊
 */
public class UserService {
    public User signIn(User user) throws SQLException {
        return new UserDAO().queryUser(user);
    }
    public int register(User user) throws SQLException {
        return new UserDAO().insertUser(user);
    }
}
