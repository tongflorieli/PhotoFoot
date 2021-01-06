package florie.photofoot.mapper;

import florie.photofoot.model.Activity;
import org.apache.ibatis.annotations.Insert;

public interface ActivityMapper {
    @Insert("insert into Activity(Username, Modified, Type, Related_Id) values(#{Username},#{Modified},#{Type},#{Related_Id})")
    void insert(Activity activity);
}
