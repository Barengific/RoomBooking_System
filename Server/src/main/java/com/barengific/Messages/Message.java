package com.barengific.Messages;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author barengific
 */

public class Message implements Serializable {
    private static final long serialVersionUID = 192856L;
    
    private String ops;
    User user;
    Room room;
    Staff staff;
    Booking booking;
    
    public Message(String ops, User user, Room room, Staff staff, Booking booking) {
        this.ops = ops;
        this.user = user;
        this.room = room;
        this.staff = staff;
        this.booking = booking;
    }
    
    public Message(User user) {
        this.user = user;
    }
    
    public Message(Room room) {
        this.room = room;
    }
    
    public Message(Staff staff) {
        this.staff = staff;
    }
    
    public Message(Booking booking) {
        this.booking = booking;
    }
        
    public Message(String ops) {
        this.ops = ops;
    }


    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    
  

    public Message(String ops, User user){
        this.ops = ops;
        this.user = user;
    }


    public String getOps() {
        return ops;
    }

    public void setOps(String ops) {
        this.ops = ops;
    }


    public String getUname(){
        return user.getUsername();
    }
    public String getPwd(){
        return user.getPassword();
    }
    public boolean getIsAdmin(){
        return user.isIsadmin();
    }
}
