package florie.photofoot.controller;

import florie.photofoot.mapper.*;
import florie.photofoot.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/PhotoFoot")
public class HomeController {
    public HomeController(PhotoMapper photoMapper, CommentMapper commentmapper, UserInfoMapper uiMapper, ActivityMapper aMapper, FavMapper favMapper) {
        this.commentMapper = commentmapper;
        this.photoMapper = photoMapper;
        this.uiMapper = uiMapper;
        this.aMapper = aMapper;
        this.favMapper = favMapper;
    }
    private PhotoMapper photoMapper;
    private CommentMapper commentMapper;
    private UserInfoMapper uiMapper;
    private ActivityMapper aMapper;
    private FavMapper favMapper;

    @RequestMapping("/Home")
    public ModelAndView Home() {
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
            photo.setCreated(new java.sql.Timestamp(System.currentTimeMillis()));
            photoMapper.insert(photo);

            //track activity
            Activity activity = new Activity();
            activity.setUsername(principal.getName());
            activity.setModified(new Timestamp(System.currentTimeMillis()));
            activity.setType("Photo Upload");
            activity.setRelated_Id(photo.getId());
            aMapper.insert(activity);

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
        lphotos.forEach((photo)->{
            int favcnt = favMapper.countByUsernameAndPhotoId(principal.getName(), photo.getId());
            if(favcnt >= 1){
                photo.setIsFav(true);
            }
        });
        ModelAndView mv=new ModelAndView("photos");
        mv.addObject("lphotos", lphotos);
        return mv;
    }

    @RequestMapping("/Activities")
    public ModelAndView Activities() {
        List<Activity> lactivities = aMapper.select20();
        lactivities.forEach((activity) -> {
            switch (activity.getType()){
                case "Comment":
                    //activity comment
                    activity.setComment(commentMapper.getCommentsById(activity.getRelated_Id()));
                    activity.getComment().setUserInfo(uiMapper.findByUsername(activity.getUsername()));
                    //activity photo
                    activity.setPhoto(photoMapper.getPhotoById(activity.getComment().getPhotoId()));
                    activity.getPhoto().setUserInfo(uiMapper.findByUsername(activity.getPhoto().getUploaded_By_Username()));
                    break;
                case "Photo Upload":
                    activity.setPhoto(photoMapper.getPhotoById(activity.getRelated_Id()));
                    activity.getPhoto().setUserInfo(uiMapper.findByUsername(activity.getUsername()));
                    break;
                default://login logout register
                    activity.setUi(uiMapper.findByUsername(activity.getUsername()));
                    break;
            }
        });
        ModelAndView mv=new ModelAndView("activities");
        mv.addObject("lactivities", lactivities);
        return mv;
    }

    @RequestMapping("/Favorites")
    public ModelAndView Favorites(Principal principal){
        List<Fav> lfav = favMapper.getMyFavs(principal.getName());
        ModelAndView mv=new ModelAndView("fav");
        mv.addObject("lfav", lfav);
        return mv;
    }

    @RequestMapping("/FavPopup")
    public ModelAndView FavPopup(int photoid){
        Photo photo = photoMapper.getPhotoById(photoid);
        ModelAndView mv=new ModelAndView("favpopup");
        mv.addObject("photo", photo);
        return mv;
    }

    @RequestMapping("/UAList")
    public ModelAndView UAList(Principal principal){
        List<UserInfo> lua = uiMapper.getAll();
        UserInfo curua = uiMapper.findByUsername(principal.getName());
        lua.remove(curua);
        Activity curactivity = aMapper.getRecentActivityByUsername(curua.getUsername());
        if(curactivity!= null){
            curactivity.setUi(uiMapper.findByUsername(curactivity.getUsername()));
            curua.setActivity(curactivity);
        }
        lua.forEach((ui)->{
            Activity activity = aMapper.getRecentActivityByUsername(ui.getUsername());
            if(activity!= null){
                activity.setUi(uiMapper.findByUsername(activity.getUsername()));
                ui.setActivity(activity);
            }
        });
        ModelAndView mv=new ModelAndView("ualist");
        mv.addObject("lua", lua);
        mv.addObject("curua",curua);
        return mv;
    }

    @GetMapping("/UpdateUserList")
    @ResponseBody
    public List<UserListActivityViewModal> UpdateUserList(){
        List<UserListActivityViewModal> ret = new ArrayList<UserListActivityViewModal>();
        try{
            List<UserInfo> lua = uiMapper.getAll();
            lua.forEach((ui)->{
                UserListActivityViewModal thisulavm = new UserListActivityViewModal();
                thisulavm.Name= ui.getId().toString();
                Activity activity = aMapper.getRecentActivityByUsername(ui.getUsername());
                if(activity == null){
                    thisulavm.Value = "Default";
                }
                if(activity != null){
                    thisulavm.Value = activity.getType();
                    if(activity.getType().equals("Photo Upload")){
                        thisulavm.Related_Id = activity.getRelated_Id();
                    }
                }
                ret.add(thisulavm);
            });
        }catch (Exception ex){
            UserListActivityViewModal fail = new UserListActivityViewModal();
            fail.Name = "fail";
            fail.Value = ex.getMessage() + "<br />" + ex.getStackTrace();
            ret.add(fail);
        }
        return ret;
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

    @PostMapping(path = "/AddComment")
    @ResponseBody
    public NameValuePair AddComment(String comment, Integer photoid, Principal principal){
        NameValuePair ret = new NameValuePair();
        try{
            Comment objcomment = new Comment();
            objcomment.setComment(comment);
            objcomment.setPhotoId(photoid);
            objcomment.setCreated(new java.sql.Timestamp(System.currentTimeMillis()));
            objcomment.setBy_Username(principal.getName());
            commentMapper.insert(objcomment);

            //track activity
            Activity activity = new Activity();
            activity.setUsername(principal.getName());
            activity.setModified(new java.sql.Timestamp(System.currentTimeMillis()));
            activity.setType("Comment");
            activity.setRelated_Id(objcomment.getId());
            aMapper.insert(activity);
            ret.setName("success");
        }catch (Exception ex){
            ret.setName("fail");
            ret.setValue(ex.getMessage() + "<br />" + ex.getStackTrace());
        }
        return ret;
    }

    @PostMapping(path = "/UpdateFav")
    @ResponseBody
    public NameValuePair UpdateFav(int fav, int photoid, Principal principal){
        NameValuePair ret = new NameValuePair();
        try{
            String username = principal.getName();
            if(fav == 1){
                favMapper.insert(username, photoid);
            }
            if(fav == 0){
                favMapper.delete(username, photoid);
            }
            ret.setName("success");
        }catch (Exception ex){
            ret.setName("fail");
            ret.setValue(ex.getMessage() + "<br />" + ex.getStackTrace());
        }
        return ret;
    }

    @RequestMapping("/Comments")
    public ModelAndView Comments(Integer photoid) {
        List<Comment> lcomments = commentMapper.getCommentsByPhotoId(photoid);
        lcomments.forEach((comment)->{
            comment.setUserInfo(uiMapper.findByUsername(comment.getBy_Username()));
        });
        ModelAndView mv=new ModelAndView("comments");
        mv.addObject("lcomments", lcomments);
        return mv;
    }
}
