package florie.photofoot.mapper;

import florie.photofoot.model.Activity;
import florie.photofoot.model.Photo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ActivityMapper {
    @Insert("insert into Activity(Username, Modified, Type, Related_Id) values(#{Username},#{Modified},#{Type},#{Related_Id})")
    void insert(Activity activity);

    @Select("SELECT TOP 20 * from Activity Order By Id DESC")
    List<Activity> select20();

    //get recent activity of last 4 hours
    @Select("SELECT TOP 1 * from Activity where Username = #{username} and Modified >= DATEADD(hh, -4, GETDATE()) Order By Id DESC")
    Activity getRecentActivityByUsername(String username);
}
