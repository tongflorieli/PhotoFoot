package florie.photofoot.controller;

import florie.photofoot.mapper.PhotoMapper;
import florie.photofoot.mapper.UserInfoMapper;
import florie.photofoot.model.NameValuePair;
import florie.photofoot.model.Photo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/PhotoFoot")
public class HomeController {
    public HomeController(PhotoMapper photoMapper) {
        this.photoMapper = photoMapper;
    }
    private PhotoMapper photoMapper;

    @RequestMapping("/Home")
    public ModelAndView Activities() {
        ModelAndView mv=new ModelAndView("home");
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

    @RequestMapping("/Photos")
    public ModelAndView Photos(Boolean ismyphotos, String username, Principal principal) {
        if(ismyphotos){
            username = principal.getName();
        }
        List<Photo> lphotos = photoMapper.selectByUsernameWithoutData(username);
        ModelAndView mv=new ModelAndView("photos");
        mv.addObject("lphotos", lphotos);
        return mv;
    }

    @GetMapping("/GetImage")
    public void GetImage(Integer Id, HttpServletResponse response) throws IOException {
        try {
            Photo p = photoMapper.getPhotoById(Id);
            byte[] bytearr = p.getData();
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(bytearr);
            response.getOutputStream().close();
        }catch (Exception ex){
        }
    }
}
