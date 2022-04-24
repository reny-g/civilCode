package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 吴仁杨
 */
@RestController
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String test() {
        return "Welcome!";
    }
}
