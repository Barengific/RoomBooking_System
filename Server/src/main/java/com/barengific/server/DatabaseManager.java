package com.barengific.server;

import com.barengific.Messages.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
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

    ArrayList<Room> rooms = new ArrayList<Room>();
    ArrayList<Booking> bookings = new ArrayList<Booking>();
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

    public void addUser(String uname, String uPass, boolean isAdmin) throws SQLException {
        Connection conn = getConnector();
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "INSERT INTO USERS (username,password,isadmin) " + "VALUEs (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, uname);
            preparedStatement.setString(2, uPass);
            preparedStatement.setBoolean(3, isAdmin);
            preparedStatement.executeUpdate();
            System.out.println("User! " + uname + " !Added");
            preparedStatement.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("adduser here: " + e);
        }
    }

    public int bookRoom(int roomNo, int staffID, int recurringID, String sTime, String fTime, int estAttend, String eName) throws SQLException {
        Connection conn = getConnector();

        Random rndmNo = new Random();
        int rndmBooking = 1 + rndmNo.nextInt((1000000 - 1) + 1);

        System.out.println("" + rndmBooking);
        LocalDateTime ldt;
        try {

//            ldt = LocalDateTime.parse(sTime);
//            Timestamp sts = Timestamp.valueOf(ldt);
//
//            ldt = LocalDateTime.parse(fTime);
//            Timestamp fts = Timestamp.valueOf(ldt);
            try {
//                System.out.println("creating string");
//                String findOverlap = "SELECT * FROM BOOKING where roomNo not in ("
//                        + "SELECT roomNo FROM booking where startdate >= '"+ fts +"' AND findate <= '"+sts+"')"
//                        + "and roomno = " + roomNo ;
//                System.out.println(findOverlap);
//                PreparedStatement ps = conn.prepareStatement(findOverlap);
//                System.out.println("prepare state done");
//                ResultSet rec = ps.executeQuery();
//                System.out.println("rs before while");
//                while (rec.next()) {
//                    System.out.println("whilee" + rec);
//                    //rec.getInt("RoomNo") == roomNo -- rec.getTimestamp("DATETIME").equals(datime)
//                    if (rec.getInt("RoomNo") == roomNo) {
//                        System.out.println("qif rec next");
//                        if (rec.getTimestamp("DATETIME").equals(sts)) {
//                            rndmBooking = 0;
//                            System.out.println("Booking already exists for roomno and date");
//                            break;
//                        } else {
//                            System.out.println("contiue");
//                            continue;
//                        }
//                    } else {
                try {
                    System.out.println("qwert");
                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    System.out.println("qqqqq");
                    String query = "INSERT INTO BOOKING (BOOKINGID,ROOMNO,STAFFID,RECURRINGID,STARTDATE,FINDATE,ESTATTENDES,EVENTNAME) " + "VALUEs (?,?,?,?,?,?,?,?)";
                    System.out.println("wwwwww");
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    System.out.println("eeeeee");
                    preparedStatement.setInt(1, rndmBooking);
                    System.out.println("rrrrrr");
                    preparedStatement.setInt(2, roomNo);
                    System.out.println("ttttt");
                    preparedStatement.setInt(3, staffID);
                    System.out.println("yyyyyy");
                    preparedStatement.setInt(4, recurringID);
                    System.out.println("uuuuuu");
                    preparedStatement.setString(5, sTime);
                    System.out.println("iiiii");
                    preparedStatement.setString(6, fTime);
                    System.out.println("oooooo");
                    preparedStatement.setInt(7, estAttend);
                    System.out.println("pppppp");
                    preparedStatement.setString(8, eName);
                    System.out.println("aaaaaa");
                    preparedStatement.executeUpdate();

                    System.out.println("Booking! " + eName + " !Added");
                    preparedStatement.close();
                    stmt.close();
                    conn.close();

                } catch (Exception ex) {
                    System.out.println("" + ex);
                }
//                        System.out.println("continueed");
//                        continue;
//                    }
//                }
            } catch (Exception e) {
                System.out.println("error at room booking bd mngr" + e);
            }
        } catch (Exception ex) {
            ex.getCause();
            rndmBooking = 1;
            System.out.println("Date time ISSUE at booking db mngnr" + ex);
        }
        return rndmBooking;
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
            System.out.println("failed at remove user dbmngr class" + e);
        }
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

    public boolean isAdmin(String uname) throws SQLException {
        Connection conn = getConnector();
        String isTrue = "true";
        boolean userPriv = false;
        try {
            
            Statement stmt = conn.createStatement();
            String query = "SELECT USERNAME, isAdmin FROM APP.USERS";
            ResultSet rec = stmt.executeQuery(query);
            
            System.out.println("about to exe");
            
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

    void viewRooms() throws SQLException {
        Connection conn = getConnector();
        try {
            String query = "SELECT * FROM ROOM ORDER BY ROOMNO ASC";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rec = preparedStatement.executeQuery();
            while (rec.next()) {
                rooms.add(new Room(rec.getInt(1), rec.getInt(2), rec.getString(3)));
                //System.out.println("rooms]***---" + rooms.toString() + roomer.getType());
                for (Room clntRoom : rooms) {
                    System.out.println(clntRoom.getRoomNo() + "-" + clntRoom.getCapacity() + "-" + clntRoom.getType());
                }
            }
        } catch (Exception e) {
            System.out.println("err ar view rooms db mngr _ " + e);
        }
    }

    void viewBooking() throws SQLException {
        Connection conn = getConnector();
        try {
            String query = "SELECT * FROM BOOKING ORDER BY BOOKINGID ASC";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rec = preparedStatement.executeQuery();
            while (rec.next()) {
                bookings.add(new Booking(rec.getInt(1), rec.getInt(2), rec.getInt(3), rec.getInt(4), rec.getString(5), rec.getString(6), rec.getInt(7), rec.getString(8)));
                for (Booking clntBooking : bookings) {
                    System.out.println(clntBooking.getBookingID() + "-");
                }
            }
        } catch (Exception e) {
            System.out.println("err ar view rooms db mngr _ " + e);
        }
    }

    void rstPass(String rstUname, String rstPass) throws SQLException {
        Connection conn = getConnector();
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "UPDATE USERS SET PASSWORD = '" + rstPass + "' WHERE USERNAME = '" + rstUname + "'";
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
