package com.barengific.server;

import com.barengific.Messages.Booking;
import com.barengific.Messages.Message;
import com.barengific.Messages.User;
import static com.barengific.server.ServerMain.user;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.Date;

/**
 *
 * @author barengific
 */
public class Testers {

    public static int clientNo = 1;
    public static ServerSocket serverSocket;
    //public static Socket clientSocket;
    public static final int port = 1234;

    public static boolean userValid;
    public static boolean userPriv;

    static ObjectInputStream ois;
    static ObjectOutputStream oos;

    static DatabaseManager dbManager;
    static Message msg;
    static User user;
    static Booking bkng;

    public static void main(String[] args) throws SQLException, IOException {
        serverSocket = new ServerSocket(1234);
        System.out.println("Server Started at: " + new Date() + '\n');
        
        while (true) {
            Socket clientSocket = serverSocket.accept();
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        System.out.println("Client Connected \nStart Thread " + clientNo++ + '\n');
                        dbManager = new DatabaseManager("app", "pass", "jdbc:derby://localhost:1527/roombooks");
                        ois = new ObjectInputStream(clientSocket.getInputStream());
                        oos = new ObjectOutputStream(clientSocket.getOutputStream());
                        clientConn(clientSocket);
                        
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }
    //run for every new client
    public static void clientConn(Socket clntSckt) throws IOException, ClassNotFoundException, SQLException {
        msg = new Message("   _   _   _   _   _  \n"
                + "  / \\ / \\ / \\ / \\ / \\ \n"
                + " ( L | o | g | i | n )\n"
                + "  \\_/ \\_/ \\_/ \\_/ \\_/ \n"
                + "Type Username: " + "Type Password: ");
        oos.writeObject(msg);
        oos.flush();
      
        user = (User) ois.readObject();        
        verifyUsers(user.getUsername(), user.getPassword());
        
        if (userValid == true) {
            isAdmin(user.getUsername());
            if (userPriv == true) {//checking for user priveledges            
                msg = new Message("confirmed_Super");
                oos.writeObject(msg);
                oos.flush();
                userOptions();
            } else {
                msg = new Message("confirmed_User");
                oos.writeObject(msg);
                oos.flush();
                userOptions();
            }
        } else if (userValid == false) {
            msg = new Message("NOT_Confirmed");
            oos.writeObject(msg);
            oos.flush();
            clientConn(clntSckt);
            System.out.println("sent---notcomfum");
        }
    }

    static void userOptions() {
        try {//using switch case to handle client requests
            System.out.println("receiving options ");
            while (true) {//server continues to server client with their options selected e.g. creating user, removing user,
                System.out.println("heree");
                msg = (Message) ois.readObject();
                System.out.println(msg.getOps() + "-salv-");
                switch (msg.getOps()) {
                    case "crtUser":
                        String uname = msg.getUname();
                        String upass = msg.getPwd();
                        boolean isAdmin = msg.getIsAdmin();
                        System.out.println("creating user");
                        dbManager.addUser(uname, upass, isAdmin);
                        break;
                    case "rmvUser":
                        String rname = msg.getUname();
                        dbManager.removeUser(rname);
                        System.out.println(rname + " removed");
                        break;
                    case "rstUser"://reset user password
                        String rstName = msg.getUname();
                        String rstPass = msg.getPwd();
                        System.out.println("rsting pass");
                        dbManager.rstPass(rstName, rstPass);
                        break;
                    case "Booking":
                        //msg = (Message) ois.readObject();
                        //System.out.println("" + msg.getEventName());
                        //int bookingid = dbManager.bookRoom(msg.getRoomNo(), msg.getStaffID(), msg.getRecurringID(), msg.getStartTime(), msg.getFinTime(), msg.getEstAttend(), msg.getEventName());
                        //read booking info and ad to database
                        System.out.println("Room-booking one");
                        //bkng = new Booking(bookingid);
//                        msg = new Message("Below is your BookingID / number ---- Please retain this for future reference: \n", bkng);
                        oos.writeObject(msg);
                        oos.writeObject(bkng);
                        oos.flush();
                        break;
                    case "view_Booking":
                        System.out.println("viewing Booking");
                        dbManager.viewBooking();
                        bkng = new Booking(dbManager.bookings);
                        //msg = new Message("View_Booking", dbManager.bookings);
                        try {
                            oos.writeObject(bkng);
                            oos.flush();
                        } catch (IOException ex) {
                            System.out.println("" + ex);
                        }
                        System.out.println("View booking simple");
                        break;
                    case "vAvail":
                        System.out.println("view available rooms");
                        break;
                    case "view_Booking_Details":
                        System.out.println("view available booking");
                        break;
                    case "updateBook":
                        System.out.println("");
                        break;
                    case "delBook":
                        System.out.println("Deleting Booking");
                        break;
                    case "viewRooms":
                        System.out.println("viewing Rooms");
                        dbManager.viewRooms();
                        try {
                            oos.writeObject(dbManager.rooms);
                            oos.flush();
                        } catch (IOException ex) {
                            System.out.println("" + ex);
                        }
                        break;
                    default:
                        System.out.println("no option found");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("error - at start thread" + e);
        }
    }

    static public void verifyUsers(String uname, String upass) throws SQLException {//using boolean if user verified or not
        if (dbManager.verifyUser(uname, upass) == true) {
            userValid = true;
        } else {
            userValid = false;
        }
    }

    static public void isAdmin(String uname) throws SQLException {
        if (dbManager.isAdmin(uname) == true) {
            userPriv = true;
        } else {
            userPriv = false;
        }
    }

}
