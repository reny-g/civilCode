package servlet;

import com.alibaba.fastjson.JSON;
import domain.User;
import service.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 杨文俊
 */
@WebServlet("/signIn")
public class SignIn extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String name = req.getParameter("userName");
        String password = req.getParameter("userPassword");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter outer = resp.getWriter();

        Map<String,Object> map = new HashMap<>(2);

        //用户传过来的验证码
        String verifyCode = req.getParameter("verifyCode");
        //验证码正确才查询
        if (session.getAttribute("sessionKeyValue").equals(verifyCode)) {
            UserService userService = new UserService();
            try {
                User user = userService.signIn(new User(name,password));
                if (user!=null) {
                    //登陆成功
                    map.put("msg","userSuccess");
                    map.put("user",user.getName());
                    session.setAttribute("user",user.getName());
//                    Cookie cookie=new Cookie("userName", user.getName());
//                    cookie.setMaxAge(24*60*60);
//                    cookie.setDomain("localhost");
//                    resp.addCookie(cookie);
                } else {
                    map.put("msg","userError");
                    map.put("user","error");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            map.put("msg","verError");
            map.put("user","error");
        }
        outer.println(JSON.toJSON(map));
    }
}
