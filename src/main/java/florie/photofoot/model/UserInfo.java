package florie.photofoot.model;

public class UserInfo {
    public UserInfo() {
    }

    public UserInfo(UserInfo ui) {
        this.Username = ui.getUsername();
        this.FirstName = ui.getFirstName();
        this.LastName = ui.getLastName();
        this.Password = ui.getPassword();
    }

    private String Username;
    private String FirstName;
    private String LastName;
    private String Password;

    public void setPassword(String password) {
        Password = password;
    }
    public String getPassword() {
        return Password;
    }
    public void setUsername(String Username) {
        this.Username = Username;
    }
    public String getUsername() {
        return Username;
    }
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }
    public String getFirstName() {
        return FirstName;
    }
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }
    public String getLastName() {
        return LastName;
    }
}
