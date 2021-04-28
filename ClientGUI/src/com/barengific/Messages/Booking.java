package com.barengific.Messages;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author barengific
 */
public class Booking implements Serializable {

    private static final long serialVersionUID = 192856L;
    private int bookingID;
    private int roomNo;
    private int staffID;
    private int recurringID;
    private String sTime;
    private String eTime;
    private int estAttend;
    private String eventName;

    private ArrayList<Booking> bkng;

    public Booking(int bookingID) {
        this.bookingID = bookingID;
    }

    public Booking(int bookingID, int roomNo, int staffID, int recurringID, String sTime, String eTime, int estAttend, String eventName) {
        this.bookingID = bookingID;
        this.roomNo = roomNo;
        this.staffID = staffID;
        this.recurringID = recurringID;
        this.sTime = sTime;
        this.eTime = eTime;
        this.estAttend = estAttend;
        this.eventName = eventName;
    }

    public Booking(int roomNo, int staffID, int recurringID, String sTime, String eTime, int estAttend, String eventName) {
        this.roomNo = roomNo;
        this.staffID = staffID;
        this.recurringID = recurringID;
        this.sTime = sTime;
        this.eTime = eTime;
        this.estAttend = estAttend;
        this.eventName = eventName;
    }
    public Booking(ArrayList<Booking> bkng){
        this.bkng = bkng;
    }
    public ArrayList<Booking> getBookingsArray(){
        return bkng;
    }

    public int getBookingID() {
        return bookingID;
    }

    public String getSTime() {
        return sTime;
    }

    public String getETime() {
        return eTime;
    }

    public int getEstAttend() {
        return estAttend;
    }

    public String getEventName() {
        return eventName;
    }

    public int getRecurringID() {
        return recurringID;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public void setStime(String sTime) {
        this.sTime = sTime;
    }

    public void setETime(String fTime) {
        this.eTime = fTime;
    }

    public void setEstAttend(int estAttend) {
        this.estAttend = estAttend;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setRecurringID(int recurringID) {
        this.recurringID = recurringID;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

}
