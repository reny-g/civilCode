package controller.test;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

/**
 * @author 吴仁杨
 */ //实现该接口的类就如同使用了注解，该路径映射在xml文件中通过id或name进行配置
public class LawControllerByInterface implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", "Hello");
        modelAndView.setViewName("test");
        return modelAndView;

    }
}
