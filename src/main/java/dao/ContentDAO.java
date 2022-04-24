package dao;

import domain.Content;
import domain.Law;
import domain.OneLaw;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author 吴仁杨
 *
 */
public class ContentDAO {
    /**
     * 功能：根据法律条文的id去数据库查询法律条文，返回时进行解析后封装成content
     * @param law（法律对象，包含id，part，partName）
     * @return content 返回查到的法律条文
     * @throws SQLException
     */
    public Content queryContent(OneLaw law) throws SQLException {

        Connection connection = JdbcUtils.getConn();
        String sql = "select * from db_laws where id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //System.out.println(sql);
        preparedStatement.setInt(1,law.getId());
        ResultSet set = preparedStatement.executeQuery();
        Content content = null;
        while (set.next()) {
            content = new Content(set.getInt("id"),set.getString("part"),
                    set.getString("partName"),
                    set.getString("subPart"),set.getString("subPartName"),
                    set.getString("section"),set.getString("sectionName"));
        }
        return content;
     }
}
