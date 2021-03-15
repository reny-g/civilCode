package servlet;

import com.alibaba.fastjson.JSON;
import service.ES.indexDocApi.DocCRUD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/echart.json")
public class ChartJson extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json;charset=utf-8");
        PrintWriter outer = resp.getWriter();
        outer.println(JSON.toJSON(DocCRUD.getJsonForChartPie()));
    }
}
