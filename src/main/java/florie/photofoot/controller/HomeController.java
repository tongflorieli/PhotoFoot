package florie.photofoot.controller;

import florie.photofoot.mapper.PhotoMapper;
import florie.photofoot.mapper.UserInfoMapper;
import florie.photofoot.model.NameValuePair;
import florie.photofoot.model.Photo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/Home")
public class HomeController {
    public HomeController(PhotoMapper photoMapper) {
        this.photoMapper = photoMapper;
    }
    private PhotoMapper photoMapper;

    @RequestMapping("/Activities")
    public ModelAndView Activities() {
        ModelAndView mv=new ModelAndView("activities");
        return mv;
    }

    @PostMapping(path = "/UploadImage")
    @ResponseBody
    public NameValuePair CheckUsernameExist(MultipartFile imgfile, Principal principal){
        NameValuePair ret = new NameValuePair();
        try{
            Photo photo = new Photo();
            photo.setData(imgfile.getBytes());
            photo.setUploaded_By_Username(principal.getName());
            photo.setCreated(new java.sql.Date(System.currentTimeMillis()));
            photoMapper.insert(photo);
            ret.setName("success");
        }catch (Exception ex){
            ret.setName("fail");
            ret.setValue(ex.getMessage() + "<br />" + ex.getStackTrace());
        }
        return ret;
    }
}
