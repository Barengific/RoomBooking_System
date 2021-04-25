package com.barengific.Messages;
import java.io.Serializable;
/**
 *
 * @author barengific
 */
public class User implements Serializable {
    private static final long serialVersionUID = 192856L;
    private String username;
    private String password;
    private boolean isadmin;
    /**
     *
     * @param username
     * @param password
     * @param isadmin
     */
    public User(String username, String password, boolean isadmin) {
        this.username = username;
        this.password = password;
        this.isadmin = isadmin;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, boolean isadmin, String header) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isIsadmin() {
        return isadmin;
    }

    public void setIsadmin(boolean isadmin) {
        this.isadmin = isadmin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
