package security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView errorPage(ModelAndView model) {
        model.setViewName("error");
        return model;
    }
}
