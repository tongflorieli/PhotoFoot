package florie.photofoot.model;

import java.sql.Timestamp;

public class Photo {
    public Photo() {
        IsFav = false;
    }

    private Integer Id;
    private Timestamp Created;
    private byte[] Data;
    private String Uploaded_By_Username;
    private Boolean IsFav;
    private UserInfo UserInfo;

    public Boolean getFav() {
        return IsFav;
    }

    public void setUserInfo(florie.photofoot.model.UserInfo userInfo) {
        UserInfo = userInfo;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public florie.photofoot.model.UserInfo getUserInfo() {
        return UserInfo;
    }

    public Boolean getIsFav() {
        return IsFav;
    }

    public void setIsFav(Boolean isFav) {
        IsFav = isFav;
    }

    public Integer getId() {
        return Id;
    }

    public Timestamp getCreated() {
        return Created;
    }

    public byte[] getData() {
        return Data;
    }

    public void setCreated(Timestamp created) {
        Created = created;
    }

    public void setData(byte[] data) {
        Data = data;
    }

    public String getUploaded_By_Username() {
        return Uploaded_By_Username;
    }

    public void setUploaded_By_Username(String uploaded_By_Username) {
        Uploaded_By_Username = uploaded_By_Username;
    }
}
