package servlet;

import com.alibaba.fastjson.JSON;
import domain.User;
import service.user.UserService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


    @WebServlet("/register")
    public class Register extends HttpServlet {
        @Override
        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String name = req.getParameter("userName");
            String password = req.getParameter("userPassword");
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter outer = resp.getWriter();

            Map<String,Object> map = new HashMap<>(2);

            try {
                if (new UserService().register(new User())==1) {
                    map.put("msg","registerSuccess");
                } else {
                    map.put("msg","registerFailed");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            outer.println(JSON.toJSON(map));
        }
    }

