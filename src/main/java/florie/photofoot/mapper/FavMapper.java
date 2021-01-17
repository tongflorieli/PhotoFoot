package florie.photofoot.mapper;

import florie.photofoot.model.Fav;
import florie.photofoot.model.Photo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FavMapper {
    @Insert("insert into Fav(UserName, PhotoId) values(#{UserName},#{PhotoId})")
    void insert(String UserName, int PhotoId);

    @Delete("delete from Fav where UserName = #{UserName} AND PhotoId = #{PhotoId}")
    void delete(String UserName, int PhotoId);

    @Select("SELECT COUNT(*) from Fav WHERE UserName = #{UserName} AND PhotoId = #{PhotoId}")
    int countByUsernameAndPhotoId(String UserName, int PhotoId);
}
