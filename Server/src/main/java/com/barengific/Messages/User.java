package com.barengific.Messages;

import java.io.Serializable;

/**
 *
 * @author barengific
 */
public class User implements Serializable {

    private static final long serialVersionUID = 192856L;
    private String username;
    private int staffID;
    private String password;
    private boolean isAdmin;

    /**
     *
     * @param username
     * @param password
     * @param isAdmin
     */
    public User(String username, int staffID, String password, boolean isAdmin) {
        this.username = username;
        this.staffID = staffID;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username) {
        this.username = username;
    }

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

}
