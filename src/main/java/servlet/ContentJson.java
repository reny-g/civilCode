package servlet;

import com.alibaba.fastjson.JSON;
import domain.Content;
import domain.OneLaw;
import service.ES.indexDocApi.DocCRUD;
import service.law.LawService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/content.json")
public class ContentJson extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Map<String,Object>> list = DocCRUD.searchResult;
        List<Content> result = new ArrayList<>();
        resp.setContentType("application/json;charset=utf-8");
        for (Map<String,Object> map:list) {
            try {
                Content content = new LawService().queryContent(new OneLaw(Integer.parseInt(map.get("id").toString()),map.get("content").toString(),map.get("contentName").toString()));
                result.add(content);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        PrintWriter outer = resp.getWriter();
        System.out.println(JSON.toJSON(result));
        outer.println(JSON.toJSON(result));
    }
}