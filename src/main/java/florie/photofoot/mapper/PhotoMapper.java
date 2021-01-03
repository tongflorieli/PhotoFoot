package florie.photofoot.mapper;

import florie.photofoot.model.Photo;
import florie.photofoot.model.UserInfo;
import org.apache.ibatis.annotations.Insert;

public interface PhotoMapper {
    @Insert("insert into Photo(Created, Data, Uploaded_By_Username) values(#{Created},#{Data},#{Uploaded_By_Username})")
    void insert(Photo photo);
}
