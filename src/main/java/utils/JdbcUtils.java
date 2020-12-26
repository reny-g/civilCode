package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * @author 吴仁杨
 * 数据库库连接工具类，通过更改TABLE_NAME可实现连接不同的数据库
 */
public class JdbcUtils {

    private static String DB_USER;
    private static String DB_PASSWORD;
    private static String DB_URL;
    private static String DB_DRIVER;
    private static Connection DB;
    private static String TABLE_NAME = "laws";
    private static ComboPooledDataSource CPDS;

    static {
        ResourceBundle resourceBundle =ResourceBundle.getBundle("jdbc");
        CPDS = new ComboPooledDataSource();
        try {
            //加载驱动
            DB_DRIVER = resourceBundle.getString("DB_DRIVER");
            Class.forName(DB_DRIVER);
            //获取配置文件的设置
            DB_URL= resourceBundle.getString("DB_URL")+TABLE_NAME;
            DB_USER = resourceBundle.getString("DB_USER");
            DB_PASSWORD = resourceBundle.getString("DB_PASSWORD");

            CPDS.setDriverClass(DB_DRIVER);
            CPDS.setJdbcUrl(DB_URL);
            CPDS.setUser(DB_USER);
            CPDS.setPassword(DB_PASSWORD);

            DB = CPDS.getConnection();
        } catch (ClassNotFoundException | SQLException | PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() throws SQLException {
        if (DB.isClosed()) {
            DB = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
        }
        return DB;
    }

    public static void closeConn(Connection conn) throws SQLException {
        if (!conn.isClosed()) {
            conn.close();
        }
    }

    public static void closeStatement(Statement st) throws SQLException {
        if (!st.isClosed()) {
            st.close();
        }
    }

    public static void closeResultSet(ResultSet rs) throws SQLException {
        if (!rs.isClosed()) {
            rs.close();
        }
    }

    public static void freeAllResources(Connection conn, Statement st, ResultSet rs) throws SQLException{
        rs.close();
        st.close();
        conn.close();
    }
}
