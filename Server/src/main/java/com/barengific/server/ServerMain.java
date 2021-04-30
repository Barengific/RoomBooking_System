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
public class ServerMain {

    public static int clientNo = 1;
    public static ServerSocket serverSocket;
    public static final int port = 1234;

    public static boolean userValid;
    public static boolean userPriv;

    static ObjectInputStream ois;
    static ObjectOutputStream oos;

    static DatabaseManager dbManager;
    static Message msg;
    static User user;
    static Booking bkng;

    public static String dbInfo = "Server Initialising";

    public static void main(String[] args) throws SQLException, IOException {
        serverSocket = new ServerSocket(1234);
        System.out.println("Server Started at: " + new Date() + '\n');

        while (true) {
            Socket clientSocket = serverSocket.accept();
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        System.out.println("\nClient Connected \nStart Thread " + clientNo++ + '\n');
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
        userValid = dbManager.verifyUser(user.getUsername(), user.getPassword());
        userPriv = dbManager.validateUser(user.getUsername());
        if (userValid == true) {
            if (userPriv == true) {//checking for user priveledges            
                msg = new Message("confirmed_Super", dbManager.getUserStaffID(user.getUsername()));
                oos.writeObject(msg);
                oos.flush();
                userOptions();
            } else {
                msg = new Message("confirmed_User", dbManager.getUserStaffID(user.getUsername()));
                oos.writeObject(msg);
                oos.flush();
                userOptions();
            }
        } else if (userValid == false) {
            msg = new Message("NOT_Confirmed", dbManager.getUserStaffID(user.getUsername()));
            oos.writeObject(msg);
            oos.flush();
            clientConn(clntSckt);
        }
    }

    static void userOptions() {
        try {//using switch case to handle client requests
            msg = new Message(dbManager.viewBookings(), dbManager.viewRooms(),
                    dbManager.viewStaffs(), dbManager.viewUsers());
            oos.writeObject(msg);
            oos.flush();

            while (true) {//server continues to server client with options selected
                msg = (Message) ois.readObject();
                System.out.println("\n" + msg.getOps() + "-salv-\n");
                switch (msg.getOps()) {
                    case "refresh":
                        msg = new Message(dbManager.viewBookings(), dbManager.viewRooms(),
                                dbManager.viewStaffs(), dbManager.viewUsers());
                        msg.getArrBooking().clear();
                        msg.getArrRoom().clear();
                        msg.getArrStaff().clear();
                        msg.getArrUser().clear();
                        clientInvoker(new Message(dbManager.viewBookings(), dbManager.viewRooms(),
                                dbManager.viewStaffs(), dbManager.viewUsers()));
                        break;
                    case "resetUser"://reset user password
                        dbManager.resetUserPass(msg.getUser().getUsername(), msg.getUser().getPassword());
                        break;
                    case "updateBook":
                        System.out.println("updating not implexmenteed yet");
                        break;
                    // 
                    //
                    //
                    case "removeBooking":
                        dbManager.removeBooking(msg.getRmID());
                        break;
                    case "removeRoom":
                        dbManager.removeRoom(msg.getRmID());
                        break;
                    case "removeStaff":
                        dbManager.removeStaff(msg.getRmID());
                        break;
                    case "removeUser":
                        dbManager.removeUser(msg.getRmUser());
                        break;
                    //                      
                    //
                    //
                    case "addBooking":
                        dbManager.addBooking(msg.getBooking().getRoomNo(),
                                msg.getBooking().getStaffID(), msg.getBooking().getRecurringID(),
                                msg.getBooking().getSTime(), msg.getBooking().getETime(),
                                msg.getBooking().getEstAttend(), msg.getBooking().getEventName());
                        break;
                    case "addRoom":
                        dbManager.addRoom(msg.getRoom().getRoomNo(), msg.getRoom().getCapacity(),
                                msg.getRoom().getType(), msg.getRoom().getPhoneNo());
                        break;
                    case "addStaff":
                        dbManager.addStaff(msg.getStaff().getFirstName(), msg.getStaff().getLastName(),
                                msg.getStaff().getOffice(), msg.getStaff().getEmail(), msg.getStaff().getPhoneNo());
                        break;
                    case "addUser":
                        dbManager.addUser(msg.getUser().getUsername(), msg.getUser().getStaffID(),
                                msg.getUser().getPassword(), msg.getUser().getIsAdmin());
                        break;
                    //    
                    //
                    //
                    default:
                        System.out.println("no option found");
                        clientInvoker(new Message("Error: No Valid Option Selection"));
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("error - at start thread" + e);
            //oos.writeObject(new M);
        }
    }

    public static void clientInvoker(Message msg) {
        try {
            oos.reset();
            oos.writeObject(msg);
            oos.flush();
        } catch (Exception e) {
            System.out.println("error at client invoker: " + e);
        }

    }

}
