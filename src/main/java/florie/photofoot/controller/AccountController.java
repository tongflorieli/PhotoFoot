package florie.photofoot.controller;
import florie.photofoot.mapper.UserInfoMapper;
import florie.photofoot.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/Account")
public class AccountController {
    public AccountController(UserInfoMapper uiMapper) {
        this.uiMapper = uiMapper;
    }

    private UserInfoMapper uiMapper;

    @RequestMapping("/Login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
        ModelAndView mv=new ModelAndView("login_register");
        if (error != null) {
            mv.addObject("error", "Invalid username and password!");
        }
        return mv;
    }
}
