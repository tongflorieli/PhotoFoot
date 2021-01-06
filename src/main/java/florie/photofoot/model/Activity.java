package florie.photofoot.model;

import java.sql.Timestamp;

public class Activity {
    private Integer Id;
    private String Username;
    private Timestamp Modified;
    private String Type;
    private Integer Related_Id;

    public Integer getId() {
        return Id;
    }

    public String getUsername() {
        return Username;
    }

    public Timestamp getModified() {
        return Modified;
    }

    public String getType() {
        return Type;
    }

    public Integer getRelated_Id() {
        return Related_Id;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setModified(Timestamp modified) {
        Modified = modified;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setRelated_Id(Integer related_Id) {
        Related_Id = related_Id;
    }
}
