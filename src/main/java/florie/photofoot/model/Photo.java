package florie.photofoot.model;

import java.sql.Date;

public class Photo {
    private Integer Id;
    private Date Created;
    private byte[] Data;
    private String Uploaded_By_Username;

    public Integer getId() {
        return Id;
    }

    public Date getCreated() {
        return Created;
    }

    public byte[] getData() {
        return Data;
    }

    public void setCreated(Date created) {
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
