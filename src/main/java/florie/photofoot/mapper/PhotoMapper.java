package florie.photofoot.mapper;

import florie.photofoot.model.Photo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PhotoMapper {
    @Insert("insert into Photo(Created, Data, Uploaded_By_Username) values(#{Created},#{Data},#{Uploaded_By_Username})")
    @Options(useGeneratedKeys = true, keyProperty = "Id", keyColumn = "Id")
    void insert(Photo photo);

    @Select("SELECT Id, Created, Uploaded_By_Username from Photo WHERE Uploaded_By_Username = #{username}")
    List<Photo> selectByUsernameWithoutData(String username);

    @Select("SELECT TOP 1 * from Photo WHERE Id = #{Id}")
    Photo getPhotoById(Integer Id);
}
