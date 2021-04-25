package com.barengific.Messages;
import java.io.Serializable;
/**
 *
 * @author barengific
 */
public class Staff implements Serializable {
    private static final long serialVersionUID = 192856L;
    private int staffID;
    private String name;
    private int office;
    private String email;
    private long phoneNo;

    public Staff(int staffID, String name, int office, String email, long phoneNo) {
        this.staffID = staffID;
        this.name = name;
        this.office = office;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getOffice() {
        return office;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOffice(int office) {
        this.office = office;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
    
    
}
