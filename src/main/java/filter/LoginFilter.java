package filter;

import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录验证的过滤器
 * @author 杨文俊
 */
@WebFilter("/search/reassult.html")//改成要过滤的搜索页面，未登录的用户无法使用搜索功能
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String user =(String) request.getSession().getAttribute("user");
        //System.out.println(JSON.toJSON(user));
        HttpSession session = request.getSession();
        if(user!= null&&user!="error") {
            chain.doFilter(req, resp);
        }
        else{
            session.setAttribute("msg","unloginError");
            ((HttpServletResponse)resp).sendRedirect(request.getContextPath()+"/login/login_in.html");
            //System.out.println(req.getServletContext().toString());
        }
        // chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void destroy() {
    }

}