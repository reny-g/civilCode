package controller.test;

import domain.Content;
import domain.OneLaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.law.LawService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class LawController implements org.springframework.web.servlet.mvc.Controller {
    @Autowired
    private LawService lawService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        //模型和视图
        ModelAndView modelAndView = new ModelAndView();
        //封装对象，放在modelview中
        modelAndView.addObject("msg", "Hello!");
        System.out.println("LawController");
        //封装要跳转的视图，放在modelView中
        //表示url的名字，代表prefix/hello/suffix
        modelAndView.setViewName("test");
        return modelAndView;
    }
}
