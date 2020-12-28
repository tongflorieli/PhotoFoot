package florie.photofoot.mapper;

import florie.photofoot.model.UserInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserInfoMapper {
    @Select("select * from Users")
    List<UserInfo> findall();
}
