package com.barengific.server;

import com.barengific.Messages.Booking;
import com.barengific.Messages.Message;
import com.barengific.Messages.User;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.Date;

/**
 *
 * @author barengific
 */
public class ServerMain {

    public static int clientNo = 1;
    public static ServerSocket srvrSckt;
    public static Socket clntSckt;
    public static final int prt = 1234;

    public static boolean usrVld;
    public static boolean userPriv;

    static ObjectInputStream ois;
    static ObjectOutputStream oos;

    static DatabaseManager dbManager;
    static Message msg;
    static User user;
    static Booking bkng;

    public static void main(String[] args) throws SQLException, IOException {
        srvrSckt = new ServerSocket(1234);
        System.out.println("Srvr Strtd @ " + new Date() + '\n');
        while (true) {
            Socket clntSckt = srvrSckt.accept();
            Thread thrd = new Thread() {
                @Override
                public void run() {
                    try {//start main jobs of thread - create new instances of class
                        System.out.println("Clnt Cnnctd \nStrt Thrd " + clientNo++ + '\n');
                        dbManager = new DatabaseManager("admin12345", "pass", "jdbc:derby://localhost:1527/ppRoom");
                        ois = new ObjectInputStream(clntSckt.getInputStream());
                        oos = new ObjectOutputStream(clntSckt.getOutputStream());
                        clntConn(clntSckt);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                private void clntConn(Socket clntSckt) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };
            thrd.start();
        }
    }
    //run for every new client
    public static void clntConn(Socket clntSckt) throws IOException, ClassNotFoundException, SQLException {
        msg = new Message("   _   _   _   _   _  \n"
                + "  / \\ / \\ / \\ / \\ / \\ \n"
                + " ( L | o | g | i | n )\n"
                + "  \\_/ \\_/ \\_/ \\_/ \\_/ \n"
                + "Type Username: "+ "Type Password: ");
        oos.writeObject(msg);//send abve to client as soon as client connects
        oos.flush();
        
        user = (User) ois.readObject();
        
        System.out.println("reading");
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        Vuser(user.getUsername(), user.getPassword());
        if (usrVld == true) {//verifying user
            isAdmin(user.getUsername());
            if (userPriv == true) {//checking for user priveledges
                msg = new Message("confirmed_Super\n");
                oos.writeObject(msg);
                oos.flush();//send menu to clients
                msg = new Message("1) Book Room\n"
                        + "2) View Bookings\n"
                        + "3) View Available Rooms\n"
                        + "4) Update Booking\n"
                        + "5) Delete Booking\n"
                        + "6) User Settings\n"
                        + "7) Quit", new User(user.getUsername(), user.getStaffID(), user.getPassword(), true));
                oos.writeObject(msg);
                oos.flush();
                userOptions();
                //System.out.println("" + msg.getOps());
            } else {
                msg = new Message("confirmed_User\n");
                oos.writeObject(msg);
                oos.flush();
                msg = new Message("1) Book Room\n"
                        + "2) View Bookings\n"
                        + "3) View Available Rooms\n"
                        + "4) Update Booking\n"
                        + "5) Delete Booking\n"
                        + "7) Quit", new User(user.getUsername(), user.getStaffID(), user.getPassword(), false));
                oos.writeObject(msg);
                oos.flush();
                userOptions();
                //System.out.println("" + msg.getOps());
            }
        } else if (usrVld == false) {
            msg = new Message("NOT_Confirmed");
            oos.writeObject(msg);
            oos.flush();

            clntConn(clntSckt);
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
                        dbManager.resetUserPass(rstName, rstPass);
                        break;
                    case "Booking":
                        //msg = (Message) ois.readObject();
                        //System.out.println("" + msg.getEventName());
                        //int bookingid = dbManager.addBooking(msg.getRoomNo(), msg.getStaffID(), msg.getRecurringID(), msg.getStartTime(), msg.getFinTime(), msg.getEstAttend(), msg.getEventName());
                        //read booking info and ad to database
                        System.out.println("Room-booking one");
                        //bkng = new Booking(bookingid);
                        //msg = new Message("Below is your BookingID / number ---- Please retain this for future reference: \n", bkng);
                        oos.writeObject(msg);
                        oos.writeObject(bkng);
                        oos.flush();
                        break;
                    case "view_Booking":
                        System.out.println("viewing Booking");
                        dbManager.viewBookings();
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

    static public void Vuser(String uname, String upass) throws SQLException {//using boolean if user verified or not
        DatabaseManager databaseManager = new DatabaseManager("admin12345", "pass", "jdbc:derby://localhost:1527/ppRoom");
        databaseManager.verifyUser(uname, upass);
        if (databaseManager.verifyUser(uname, upass) == true) {
            usrVld = true;
        } else {
            usrVld = false;
        }
    }

    static public void isAdmin(String uname) throws SQLException {
        DatabaseManager databaseManager = new DatabaseManager("admin12345", "pass", "jdbc:derby://localhost:1527/ppRoom");
        databaseManager.validateUser(uname);
        if (databaseManager.validateUser(uname) == true) {
            userPriv = true;
            System.out.println("user admin");
        } else {
            userPriv = false;
            System.out.println("user not admin");
        }
    }

}
