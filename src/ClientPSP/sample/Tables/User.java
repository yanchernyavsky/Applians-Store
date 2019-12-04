package ClientPSP.sample.Tables;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String userName;
    private String login;
    private String password;
    private Integer admin;

    public User(){

    }

    public User(String userName, String login, String password) {
        this.userName = userName;
        this.login = login;
        this.password = password;
    }

    public User(User user){
        this.userName=user.userName;
        this.login=user.login;
        this.password=user.password;
    }

    public User(String login, String password){
        this.login = login;
        this.password= password;
    }

    public Integer getUserId() {
        return id;
    }

    public void setUserId(Integer id) {
        this.id = id;
    }

    public Integer getUserAdmin() {
        return admin;
    }

    public void setUserAdmin(Integer admin) {
        this.admin = admin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogin() {
        return login;
    }

    public void setUserLogin(String login) {
        this.login = login;
    }

    public String getUserPassword() {
        return password;
    }

    public void setUserPassword(String password) {
        this.password = password;
    }
}
