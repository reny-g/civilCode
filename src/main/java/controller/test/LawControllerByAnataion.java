package controller.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author 吴仁杨
 */
@Controller
//第一层访问地址
//@RequestMapping("/say")
public class LawControllerByAnataion {

    //第二层访问地址
    @RequestMapping(value = "/Hello/{a}", method = RequestMethod.GET)
//    @GetMapping/@PostMapping/@DeleteMapping
    public String sayHello(@PathVariable int a,@RequestParam("model") Model model) {
        System.out.println("by annotaion!");
        model.addAttribute("msg", "Hello by annotaion from test.html <-"+a);
//        /WEB-INF/page/test.html
//        return "test";
//        forward:
        return "test";
    }

    @RequestMapping(value = "/Hello", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
    @ResponseBody
//    @GetMapping/@PostMapping/@DeleteMapping
    public String sgetJson(Model model, String name) throws JsonProcessingException {
        System.out.println("by annotaion!");
        model.addAttribute("msg", "Hello by annotaion from test.html <- "+name);

        return new ObjectMapper().writeValueAsString(model);
    }
}
