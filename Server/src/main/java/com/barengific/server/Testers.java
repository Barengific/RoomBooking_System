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
            //System.out.println("receiving options ");

            msg = new Message(dbManager.viewBookings(), dbManager.viewRooms(),
                    dbManager.viewStaffs(), dbManager.viewUsers());
            oos.writeObject(msg);
            oos.flush();

            while (true) {//server continues to server client with their options selected e.g. creating user, removing user,
                System.out.println("heree at options");
                msg = (Message) ois.readObject();
                System.out.println(msg.getOps() + "-salv-");
                switch (msg.getOps()) {
                    case "refresh":
                        msg = new Message(dbManager.viewBookings(), dbManager.viewRooms(),
                                dbManager.viewStaffs(), dbManager.viewUsers());
                        oos.writeObject(msg);
                        oos.flush();
                        System.out.println("viewing allls");
                        break;
                    case "resetUser"://reset user password
                        System.out.println("rsting pass");
                        dbManager.resetUserPass(msg.getUname(), msg.getPwd());
                        msg = new Message("user: " + msg.getUname() + " was resetss");
                        oos.writeObject(msg);
                        oos.flush();
                        break;
                    case "updateBook":
                        System.out.println("");
                        break;

                    // 
                    //
                    //
                    case "removeBooking":
                        System.out.println("FDe delling booking");
                        dbManager.removeBooking(msg.getRmID());
                        System.out.println("Deleting Booking");
                        msg = new Message("Booking IDss: " + msg.getRmID() + " removed");
                        oos.writeObject(msg);
                        oos.flush();
                        break;
                    case "removeRoom":
                        dbManager.removeRoom(msg.getRmID());
                        System.out.println("Deleting Roomms");
                        msg = new Message("roomssnOOO: " + msg.getRmID() + " removed");
                        oos.writeObject(msg);
                        oos.flush();
                        System.out.println("Deleting Room");
                        break;
                    case "removeStaff":
                        dbManager.removeStaff(msg.getRmID());
                        System.out.println("Deleting stafffss");
                        msg = new Message("staffIDDss: " + msg.getRmID() + " removed");
                        oos.writeObject(msg);
                        oos.flush();
                        System.out.println("Deleting Staff");
                        break;
                    case "removeUser":
                        dbManager.removeUser(msg.getUname());
                        msg = new Message("user: " + msg.getUname() + " was removeds");
                        oos.writeObject(msg);
                        oos.flush();
                        break;
                    //                      
                    //
                    //
                    case "addBooking":
                        System.out.println("ADDing booking");
                        int bookingID = dbManager.addBooking(msg.getBooking().getRoomNo(),
                                msg.getBooking().getStaffID(), msg.getBooking().getRecurringID(),
                                msg.getBooking().getSTime(), msg.getBooking().getETime(),
                                msg.getBooking().getEstAttend(), msg.getBooking().getEventName());

                        oos.writeObject("Here Booking number ---- retain for reference: " + bookingID);
                        oos.flush();
                        break;
                    case "addRoom":
                        dbManager.addRoom(msg.getRoom().getRoomNo(), msg.getRoom().getCapacity(),
                                msg.getRoom().getType(), msg.getRoom().getPhoneNo());
                        oos.writeObject(new Message("new roomss wadds"));
                        oos.flush();
                        System.out.println("room addsss");
                        break;
                    case "addStaff":
                        oos.writeObject(new Message("new stafff adds \\n new staff ID: "
                                + dbManager.addStaff(msg.getStaff().getFirstName(), msg.getStaff().getLastName(),
                                        msg.getStaff().getOffice(), msg.getStaff().getEmail(), msg.getStaff().getPhoneNo())));
                        oos.flush();
                        System.out.println("stafff allls adds");
                        break;
                    case "addUser":
                        System.out.println("creating user");
                        dbManager.addUser(msg.getUname(), msg.getPwd(), msg.getIsAdmin());
                        oos.writeObject(new Message("newss user: " + msg.getUname() + " was addeds"));
                        oos.flush();
                        break;
                    //    
                    //
                    //
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
        if (dbManager.validateUser(uname) == true) {
            userPriv = true;
        } else {
            userPriv = false;
        }
    }

}
