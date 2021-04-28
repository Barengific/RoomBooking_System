package com.barengific.Messages;
import java.io.Serializable;
/**
 *
 * @author barengific
 */
public class Staff implements Serializable {
    private static final long serialVersionUID = 192856L;
    private int staffID;
    private String firstName;
    private String lastName;
    private int office;
    private String email;
    private long phoneNo;

    private String fullName;

    public Staff(int staffID, String firstName, String lastName, int office, String email, long phoneNo) {
        this.staffID = staffID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.office = office;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public Staff(int staffID, String fullName, String firstName, String lastName, int office, String email, long phoneNo) {
        this.staffID = staffID;
        this.fullName = fullName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.office = office;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public Staff(String firstName, String lastName, int office, String email, long phoneNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.office = office;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

}
