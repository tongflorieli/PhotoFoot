package florie.photofoot.model;
import java.sql.Timestamp;

public class Comment {
    private Integer Id;
    private String Comment;
    private Integer PhotoId;
    private Timestamp Created;
    private String By_Username;
    private UserInfo UserInfo;

    public void setUserInfo(UserInfo ui) {
        this.UserInfo = ui;
    }

    public UserInfo getUserInfo() {
        return UserInfo;
    }

    public Integer getId() {
        return Id;
    }

    public String getComment() {
        return Comment;
    }

    public Integer getPhotoId() {
        return PhotoId;
    }

    public Timestamp getCreated() {
        return Created;
    }

    public String getBy_Username() {
        return By_Username;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public void setPhotoId(Integer photoId) {
        PhotoId = photoId;
    }

    public void setCreated(Timestamp created) {
        Created = created;
    }

    public void setBy_Username(String by_Username) {
        By_Username = by_Username;
    }
}
