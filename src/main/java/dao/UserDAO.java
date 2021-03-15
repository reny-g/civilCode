package dao;

import domain.User;
import utils.JdbcUtils;

import java.sql.*;

/**
 * @author 吴仁杨
 */
public class UserDAO {
    /**
     * 功能：根据传入的user对象解析出数据插入数据表中
     * @param user（插入的用户对象）
     * @return int 返回影响行数
     * @throws SQLException
     */
    public int insertUser(User user) throws SQLException {
        Connection connection = JdbcUtils.getConn();
        String sql = "insert into db_user value(null,?,?,?,null,null,null)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //System.out.println(sql);
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.setString(3,user.getName());
        int b
                 = preparedStatement.executeUpdate();
        System.out.println(b);
        return b;
    }

    /**
     * 功能：根据传入的用户去数据表中查询用户名与密码是否匹配
     * @param user 用户输入数据构造的对象
     * @return 若查到返回正确用户对象；否则返回null
     * @throws SQLException
     */
    public User queryUser(User user) throws SQLException {
        Connection connection = JdbcUtils.getConn();
        String sql = "select * from db_user where "+"user_phone = ? and"+" user_password = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //System.out.println(sql);
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            //System.out.println(resultSet.getString("user_phone")+resultSet.getString("user_password"));
            if (user.getName().equals(resultSet.getString("user_phone"))&&user.getPassword().equals(resultSet.getString("user_password"))) {
                return user;
            } else {
                return null;
            }
        }
        return null;
    }
}
