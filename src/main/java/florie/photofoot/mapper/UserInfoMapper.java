package florie.photofoot.mapper;

import florie.photofoot.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserInfoMapper {
    @Select("SELECT TOP 1 * from UserInfo WHERE Username = #{username}")
    UserInfo findByUsername(String username);

    @Select("SELECT TOP 1 * from UserInfo WHERE Id = #{id}")
    UserInfo findById(int id);

    @Select("SELECT * from UserInfo")
    List<UserInfo> getAll();

    @Select("SELECT count(*) from UserInfo WHERE Username = #{username}")
    Integer checkUsernameExist(String username);

    @Insert("insert into UserInfo(Username, FirstName, LastName, Password, Location, Description, Occupation) values(#{Username},#{FirstName},#{LastName},#{Password},#{Location},#{Description},#{Occupation})")
    void insert(UserInfo users);
}
