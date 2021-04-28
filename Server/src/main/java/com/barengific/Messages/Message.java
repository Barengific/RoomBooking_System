package com.barengific.Messages;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author barengific
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 192856L;


    private int rmID;
    private String ops;
    User user;
    Room room;
    Staff staff;
    Booking booking;

    ArrayList<Booking> arrBooking;
    ArrayList<Room> arrRoom;
    ArrayList<Staff> arrStaff;
    ArrayList<User> arrUser;

    /**
     *
     * @param arrBooking
     */
    public Message(ArrayList<Booking> arrBooking) {
        this.arrBooking = arrBooking;
    }

    public Message(ArrayList<Booking> arrBooking, ArrayList<Room> arrRoom, ArrayList<Staff> arrStaff, ArrayList<User> arrUser) {
        this.arrBooking = arrBooking;
        this.arrRoom = arrRoom;
        this.arrStaff = arrStaff;
        this.arrUser = arrUser;
    }

    public ArrayList<Room> getArrRoom() {
        return arrRoom;
    }

    public void setArrRoom(ArrayList<Room> arrRoom) {
        this.arrRoom = arrRoom;
    }

    public ArrayList<Staff> getArrStaff() {
        return arrStaff;
    }

    public void setArrStaff(ArrayList<Staff> arrStaff) {
        this.arrStaff = arrStaff;
    }

    public ArrayList<Booking> getArrBooking() {
        return arrBooking;
    }

    public void setArrBooking(ArrayList<Booking> arrBooking) {
        this.arrBooking = arrBooking;
    }

    public ArrayList<User> getArrUser() {
        return arrUser;
    }

    public void setArrUser(ArrayList<User> arrUser) {
        this.arrUser = arrUser;
    }

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

    public Message(String ops, Room room) {
        this.ops = ops;
        this.room = room;
    }

    public Message(String ops, Staff staff) {
        this.ops = ops;
        this.staff = staff;
    }

    public int getRmID() {
        return rmID;
    }

    public void setRmID(int rmID) {
        this.rmID = rmID;
    }

    public Message(String ops, int rmID){
        this.ops = ops;
        this.rmID = rmID;
    }

    public Message(String ops,Booking booking) {
        this.ops = ops;
        this.booking = booking;
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

    public Message(String ops, User user) {
        this.ops = ops;
        this.user = user;
    }

    public String getOps() {
        return ops;
    }

    public void setOps(String ops) {
        this.ops = ops;
    }

    public String getUname() {
        return user.getUsername();
    }

    public String getPwd() {
        return user.getPassword();
    }

    public boolean getIsAdmin() {
        return user.getIsAdmin();
    }
}
