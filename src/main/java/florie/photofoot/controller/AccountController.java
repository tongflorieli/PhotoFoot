package florie.photofoot.controller;
import florie.photofoot.mapper.UserInfoMapper;
import florie.photofoot.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/w")
public class AccountController {
    public AccountController(UserInfoMapper uiMapper) {
        this.uiMapper = uiMapper;
    }

    private UserInfoMapper uiMapper;

    @RequestMapping("/asd")
    public ModelAndView asd() {
        ModelAndView mv=new ModelAndView("login_register");
        List<UserInfo> lui = uiMapper.findall();
//        mv.addObject("user", uiMapper.findall());
        return mv;
    }
}
