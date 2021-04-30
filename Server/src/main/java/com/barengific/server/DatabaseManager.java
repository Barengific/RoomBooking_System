package com.barengific.server;

import com.barengific.Messages.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author barengific
 */
public class DatabaseManager {

    private final String userName;
    private final String password;
    private final String databaseURL;

    ArrayList<Booking> bookings = new ArrayList<Booking>();
    ArrayList<Room> rooms = new ArrayList<Room>();
    ArrayList<Staff> staffs = new ArrayList<Staff>();
    ArrayList<User> users = new ArrayList<User>();

    Message msg;

    public DatabaseManager(String userName, String password, String databaseURL) {
        this.userName = userName;
        this.password = password;
        this.databaseURL = databaseURL;
    }

    public Connection getConnector() throws SQLException {
        Connection con = DriverManager.getConnection(databaseURL, userName, password);
        return con;
    }

    public int addBooking(int roomNo, int staffID, int recurringID, String sTime, String fTime, int estAttend, String eName) throws SQLException {
        Connection conn = getConnector();
        int newBookingNo = viewBookings().size() + 1;
        //
//        try {
//            String findOverlap = "SELECT * FROM BOOKING where roomNo not in ("
//                    + "SELECT roomNo FROM booking where startdate >= '"
//                    + eeetime + "' AND findate <= '" + ssstime + "')"
//                    + "and roomno = " + roomNo;
//            PreparedStatement ps = conn.prepareStatement(findOverlap);
//            ResultSet rec = ps.executeQuery();
//            while (rec.next()) {
//                if (rec.getInt("RoomNo") == roomNo) {
//                    if (rec.getTimestamp("DATETIME").equals(ssstime)) {
//                        newBookingNo = 0;
//                        System.out.println("Booking already exists for roomno and date");
//                        break;
//                    } else {
//                        System.out.println("contiue");
//                        continue;
//                    }
//                } else {
        try {

            java.sql.Timestamp stim = java.sql.Timestamp.valueOf(sTime);
            java.sql.Timestamp ftim = java.sql.Timestamp.valueOf(fTime);

            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "INSERT INTO BOOKING (BOOKINGID, ROOMNO,STAFFID,RECURRINGID,STARTDATE,ENDDATE,ESTATTENDEES,EVENTNAME) " + "VALUEs (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, newBookingNo);
            preparedStatement.setInt(2, roomNo);
            preparedStatement.setInt(3, staffID);
            preparedStatement.setInt(4, recurringID);
            preparedStatement.setTimestamp(5, stim);
            preparedStatement.setTimestamp(6, ftim);
            preparedStatement.setInt(7, estAttend);
            preparedStatement.setString(8, eName);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            stmt.close();
            conn.close();

        } catch (Exception ex) {
            System.out.println("error at adding bookss:: " + ex);
        }
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("error at room booking bd mngr" + e);
//        }

        return newBookingNo;
    }

    public void addRoom(int roomNo, int capacity, String type, long phoneNo) throws SQLException {
        Connection conn = getConnector();
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "INSERT INTO ROOM (ROOMNO,CAPACITY,TYPES,PHONENO) " + "VALUEs (?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, roomNo);
            preparedStatement.setInt(2, capacity);
            preparedStatement.setString(3, type);
            preparedStatement.setLong(4, phoneNo);
            preparedStatement.executeUpdate();
            System.out.println("room! " + type + " !Added");
            preparedStatement.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("add rooom error here: " + e);
        }
    }

    public int addStaff(String firstName, String lastName, int officeNo, String email, long phoneNo) throws SQLException {
        Connection conn = getConnector();
        int newStaffID = viewStaffs().size() + 1;

        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "INSERT INTO STAFF (STAFFID,FIRSTNAME,LASTNAME,OFFICENO,EMAIL,PHONENO) " + "VALUEs (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, newStaffID);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setInt(4, officeNo);
            preparedStatement.setString(5, email);
            preparedStatement.setLong(6, phoneNo);
            preparedStatement.executeUpdate();
            System.out.println("staff! " + firstName + " !Added");
            preparedStatement.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("add staff error here: " + e);
        }
        return newStaffID;
    }

    public void addUser(String uname, int staffID, String uPass, boolean isAdmin) throws SQLException {
        Connection conn = getConnector();
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "INSERT INTO USERS (username,STAFFID,password,isadmin) " + "VALUEs (?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, uname);
            preparedStatement.setInt(2, staffID);
            preparedStatement.setString(3, uPass);
            preparedStatement.setBoolean(4, isAdmin);
            preparedStatement.executeUpdate();
            System.out.println("User! " + uname + " !Added");
            preparedStatement.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("add user error here: " + e);
        }
    }

    public void removeBooking(int bookingID) throws SQLException {
        Connection conn = getConnector();
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "DELETE FROM BOOKING WHERE BOOKINGID = " + bookingID;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.println("booking! " + bookingID + " !Deleted");
            preparedStatement.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("failed at remove booking dbmngr class::: " + e);
        }
    }

    public void removeRoom(int roomNo) throws SQLException {
        Connection conn = getConnector();
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "DELETE FROM ROOM WHERE ROOMNO = " + roomNo;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.println("room! " + roomNo + " !Deleted");
            preparedStatement.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("failed at remove room dbmngr class" + e);
        }
    }

    public void removeStaff(int staffID) throws SQLException {
        Connection conn = getConnector();
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "DELETE FROM STAFF WHERE STAFFID = " + staffID;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.println("staff! " + staffID + " !Deleted");
            preparedStatement.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("failed at remove staff dbmngr class:: " + e);
        }
    }

    public void removeUser(String uname) throws SQLException {
        Connection conn = getConnector();
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "DELETE FROM USERS WHERE USERNAME = " + "'" + uname + "'";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.println("User! " + uname + " !Deleted");
            preparedStatement.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("failed at remove user dbmngr class:: " + e);
        }
    }

    ArrayList<Booking> viewBookings() throws SQLException {
        Connection conn = getConnector();
        try {
            String query = "SELECT * FROM BOOKING ORDER BY BOOKINGID ASC";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rec = preparedStatement.executeQuery();
            while (rec.next()) {
                bookings.add(new Booking(rec.getInt(1), rec.getInt(2), rec.getInt(3),
                        rec.getInt(4), rec.getString(5), rec.getString(6), rec.getInt(7), rec.getString(8)));
            }
        } catch (Exception e) {
            System.out.println("err ar view bookings db mngr _ " + e);
        }
        return bookings;
    }

    ArrayList<Room> viewRooms() throws SQLException {
        Connection conn = getConnector();
        try {
            String query = "SELECT * FROM ROOM ORDER BY ROOMNO ASC";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rec = preparedStatement.executeQuery();
            while (rec.next()) {
                rooms.add(new Room(rec.getInt(1), rec.getInt(2), rec.getString(3), rec.getInt(4)));
            }
        } catch (Exception e) {
            System.out.println("err ar view rooms db mngr _ " + e);
        }
        return rooms;
    }

    ArrayList<Staff> viewStaffs() throws SQLException {
        Connection conn = getConnector();
        try {
            String query = "SELECT * FROM STAFF ORDER BY STAFFID ASC";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rec = preparedStatement.executeQuery();
            while (rec.next()) {
                staffs.add(new Staff(rec.getInt(1), rec.getString(2), rec.getString(3), 
                        rec.getInt(4), rec.getString(5), rec.getInt(6)));
            }
        } catch (Exception e) {
            System.out.println("err ar view staffs db mngr _ " + e);
        }
        return staffs;
    }

    ArrayList<User> viewUsers() throws SQLException {
        Connection conn = getConnector();
        try {
            String query = "SELECT * FROM USERS ORDER BY USERNAME ASC";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rec = preparedStatement.executeQuery();
            while (rec.next()) {
                users.add(new User(rec.getString(1), rec.getInt(2), rec.getString(3), rec.getBoolean(4)));
            }
        } catch (Exception e) {
            System.out.println("!!! NNOOOO ...,<>");
            System.out.println("err ar view users db mngr _ " + e);
        }
        return users;
    }

    public boolean verifyUser(String uname, String upass) throws SQLException {
        Connection conn = getConnector();
        boolean validity = false;
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT USERNAME, PASSWORD FROM APP.USERS";
            ResultSet rec = stmt.executeQuery(query);

            while (rec.next()) {
                if (uname.equals(rec.getString("USERNAME"))) {
                    if (upass.equals(rec.getString("PASSWORD"))) {
                        System.out.println("logg in - " + uname + '\n');
                        validity = true;
                        return true;
                    } else {
                        System.out.println("pass incorredt");
                        validity = false;
                    }
                } else {
                    System.out.println("didnt work/usernotFOUND");
                    validity = false;
                }
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("failed at validate user dbmngr class");
            System.out.println(e);
        }
        return validity;
    }

    public boolean validateUser(String uname) throws SQLException {
        Connection conn = getConnector();
        boolean userPriv = false;
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT USERNAME, isAdmin FROM APP.USERS";
            ResultSet rec = stmt.executeQuery(query);

            while (rec.next()) {
                if (uname.equals(rec.getString("USERNAME"))) {
                    System.out.println(rec.getString("USERNAME"));
                    if (rec.getBoolean("isAdmin")) {
                        System.out.println("user admin");
                        userPriv = true;
                        return true;
                    } else {
                        System.out.println("not it");
                        userPriv = false;
                        return false;
                    }
                } else {
                    userPriv = false;
                    System.out.println("usr not found at isadmin db manger");
                    return false;
                }
            }
        } catch (Exception ex) {
            System.out.println("error at isAdmin at db manager" + ex);
        }
        return userPriv;
    }

    public int getUserStaffID(String uname) throws SQLException {
        Connection conn = getConnector();
        int staffi = 0;
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT STAFFID FROM USERS WHERE USERNAME = " + "'" + uname + "'";
            ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                staffi = rec.getInt("STAFFID");
            }
        } catch (Exception ex) {
            System.out.println("error at get staff id staff at db manager" + ex);
        }
        return staffi;
    }

    void resetUserPass(String rstUname, String resetPass) throws SQLException {
        Connection conn = getConnector();
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "UPDATE USERS SET PASSWORD = '" + resetPass + "' WHERE USERNAME = '" + rstUname + "'";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.println("User! " + rstUname + " !pass reset updated");
            preparedStatement.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("er at dbManager reset user");
        }
    }
}
