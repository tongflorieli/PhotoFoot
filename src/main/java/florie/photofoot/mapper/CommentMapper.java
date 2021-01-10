package florie.photofoot.mapper;

import florie.photofoot.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper {
    @Insert("insert into Comment(Comment, PhotoId, Created, By_Username) values(#{Comment},#{PhotoId},#{Created},#{By_Username})")
    @Options(useGeneratedKeys = true, keyProperty = "Id", keyColumn = "Id")
    void insert(Comment comment);

    @Select("select * from Comment where PhotoId = #{photoid} Order by Created")
    List<Comment> getCommentsByPhotoId(Integer photoid);

    @Select("select TOP 1 * from Comment where Id = #{id}")
    Comment getCommentsById(Integer id);
}
