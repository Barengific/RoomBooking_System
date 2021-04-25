package com.barengific.Messages;

import java.io.Serializable;

/**
 *
 * @author barengific
 */
public class Room implements Serializable {

    private static final long serialVersionUID = 192856L;
    private int roomNo;
    private int capacity;
    private String type;
    private long phoneNo;

    public Room(int roomNo, int capacity, String type) {
        this.roomNo = roomNo;
        this.capacity = capacity;
        this.type = type;
    }

    public Room(int roomNo, int capacity, String type, long phoneNo) {
        this.roomNo = roomNo;
        this.capacity = capacity;
        this.type = type;
        this.phoneNo = phoneNo;
    }



    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public Room() {
        this.type = null;
    }

}
