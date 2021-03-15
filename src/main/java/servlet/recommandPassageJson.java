package servlet;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.HttpGet;
import service.recommandPassage.RecommandPassageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**返回推荐文章json对象
 * @author 张宏沛
 */
@WebServlet("/passage.json")
public class recommandPassageJson extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter outer = resp.getWriter();
        try {
            //把推荐文章转换成json对象
            outer.println(JSON.toJSON(new RecommandPassageService().queryRecommandPassage()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
