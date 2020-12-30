package florie.photofoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Home")
public class HomeController {
    @RequestMapping("/Users")
    public ModelAndView Users() {
        ModelAndView mv=new ModelAndView("users");
        return mv;
    }
}
