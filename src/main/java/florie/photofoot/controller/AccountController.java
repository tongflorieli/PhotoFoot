package florie.photofoot.controller;
import florie.photofoot.mapper.ActivityMapper;
import florie.photofoot.mapper.UserInfoMapper;
import florie.photofoot.model.Activity;
import florie.photofoot.model.NameValuePair;
import florie.photofoot.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/Account")
public class AccountController {
    public AccountController(UserInfoMapper uiMapper, ActivityMapper aMapper) {
        this.aMapper = aMapper;
        this.uiMapper = uiMapper;
    }

    private UserInfoMapper uiMapper;
    private ActivityMapper aMapper;

    @RequestMapping("/Login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
        ModelAndView mv=new ModelAndView("login_register");
        if (error != null) {
            mv.addObject("error", "Invalid username and password!");
        }
        return mv;
    }

    @PostMapping(path = "/CheckUsernameExist")
    @ResponseBody
    public NameValuePair CheckUsernameExist(@RequestParam()String username){
        NameValuePair ret = new NameValuePair();
        try{
            Integer existinguser = uiMapper.checkUsernameExist(username);
            ret.setName("success");
            if(existinguser > 0){
                ret.setValue("already exist");
            }else{
                ret.setValue("does not exist");
            }
        }catch (Exception ex){
            ret.setName("fail");
            ret.setValue(ex.getMessage() + "<br />" + ex.getStackTrace());
        }
        return ret;
    }

    @PostMapping(path = "/AddUser")
    @ResponseBody
    public NameValuePair AddUser(@RequestParam()String username, String password, String firstname,
                                 String lastname, String location, String description, String occupation){
        NameValuePair ret = new NameValuePair();
        try{
            UserInfo ui =new UserInfo();
            ui.setUsername(username);
            ui.setPassword(password);
            ui.setFirstName(firstname);
            ui.setLastName(lastname);
            ui.setLocation(location);
            ui.setDescription(description);
            ui.setOccupation(occupation);
            uiMapper.insert(ui);

            //Activity
            Activity activity = new Activity();
            activity.setUsername(username);
            activity.setModified(new Timestamp(System.currentTimeMillis()));
            activity.setType("User Registered");
            aMapper.insert(activity);

            ret.setName("success");
        }catch (Exception ex){
            ret.setName("fail");
            ret.setValue(ex.getMessage() + "<br />" + ex.getStackTrace());
        }
        return ret;
    }
}
