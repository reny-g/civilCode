package servlet;

import com.alibaba.fastjson.JSON;
import service.ES.indexDocApi.DocCRUD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 吴仁杨
 */
@WebServlet("/isInSession")
public class LoginInSession extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<String,Object> map = new HashMap<>(2);
        map.put("user",session.getAttribute("user")==null?"null":session.getAttribute("user"));
        System.out.println("userSession"+session.getAttribute("user"));
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter outer = resp.getWriter();
        outer.println(JSON.toJSON(map));
    }
}
