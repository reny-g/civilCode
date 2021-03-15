package service.law;

import dao.ContentDAO;
import domain.Content;
import domain.OneLaw;

import java.sql.SQLException;

/**
 * @author 吴仁杨
 */
public class LawService {
    public Content queryContent(OneLaw law) throws SQLException {
        return new ContentDAO().queryContent(law);
    }
}
