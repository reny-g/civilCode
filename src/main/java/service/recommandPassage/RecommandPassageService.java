package service.recommandPassage;

import com.sun.org.apache.bcel.internal.generic.NEW;
import dao.RecomPassageDAO;
import domain.Passage;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 张宏沛
 */
public class RecommandPassageService {
    /**
     * 参数 无
     * @return 推荐文章对象数组列表
     * @throws SQLException
     */
    public List<Passage> queryRecommandPassage() throws SQLException {
        return new RecomPassageDAO().queryRecomPassage();
    }
}
