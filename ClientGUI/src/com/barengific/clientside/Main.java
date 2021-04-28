package com.barengific.clientside;

import com.barengific.Messages.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.barengific.gui.LoginController;
import com.barengific.gui.MenuAdminController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * @author barengific
 */
public class Main extends Application {
    static Message msg;
    static User user;

    private static final int port = 1234;
    private static final String host = "localhost";
    private static Socket socket;

    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/login.fxml"));
//      Scene scene = new Scene(root);
        stage.getIcons().add(new Image("file:icon.png"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();
        LoginController.ress = "msgs.getOps()";

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        launch(args);
    }

    public static void serverConn(String uname, String upass) throws IOException {
        socket = new Socket(host, port);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
        try {
            Message msgs = (Message) ois.readObject();
            System.out.println(msgs.getOps());

            user = new User(uname, upass);
            oos.writeObject(user);
            oos.flush();
            msgs = (Message) ois.readObject();
            System.out.println(msgs.getOps());
            if (msgs.getOps().equals("confirmed_Super")) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../gui/menuAdmin.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.getIcons().add(new Image("file:icon.png"));
                stage.setScene(new Scene(root1));
                stage.show();

                msgs = (Message) ois.readObject();
                for (int i = 0; i < msgs.getArrBooking().size(); i++) {
                    //System.out.println(msgs.getArrBooking().get(i).getEventName());
                    MenuAdminController.olBooking.add(new Booking(
                            msgs.getArrBooking().get(i).getBookingID(),
                            msgs.getArrBooking().get(i).getRoomNo(),
                            msgs.getArrBooking().get(i).getStaffID(),
                            msgs.getArrBooking().get(i).getRecurringID(),
                            msgs.getArrBooking().get(i).getSTime(),
                            msgs.getArrBooking().get(i).getETime(),
                            msgs.getArrBooking().get(i).getEstAttend(),
                            msgs.getArrBooking().get(i).getEventName()));
                }
                for (int i = 0; i < msgs.getArrRoom().size(); i++) {
                    //System.out.println(msgs.getArrRoom().get(i).getType());
                    MenuAdminController.olRoom.add(new Room(
                            msgs.getArrRoom().get(i).getRoomNo(),
                            msgs.getArrRoom().get(i).getCapacity(),
                            msgs.getArrRoom().get(i).getType(),
                            msgs.getArrRoom().get(i).getPhoneNo()));
                }
                for (int i = 0; i < msgs.getArrStaff().size(); i++) {
                    //System.out.println(msgs.getArrStaff().get(i).getFirstName());
                    MenuAdminController.olStaff.add(new Staff(
                            msgs.getArrStaff().get(i).getStaffID(),
                            msgs.getArrStaff().get(i).getFirstName(),
                            msgs.getArrStaff().get(i).getLastName(),
                            msgs.getArrStaff().get(i).getOffice(),
                            msgs.getArrStaff().get(i).getEmail(),
                            msgs.getArrStaff().get(i).getPhoneNo()));
                }
                for (int i = 0; i < msgs.getArrUser().size(); i++) {
//                    System.out.println("checkss: " +msgs.getArrUser().get(i).getStaffID());
//                    System.out.println(msgs.getArrUser().get(i).getUsername());
                    MenuAdminController.olUser.add(new User(
                            msgs.getArrUser().get(i).getUsername(),
                            msgs.getArrUser().get(i).getStaffID(),
                            msgs.getArrUser().get(i).getPassword(),
                            msgs.getArrUser().get(i).getIsAdmin()));
                }

                //MenuAdminController.data = msgs.getData();
                //new Booking(1, 12, 11, 98,"mon","tue",10,"rollball");
                //MenuAdminController.refreshBooking();

            } else if (msgs.getOps().equals("confirmed_User")) {
                System.out.println("standard");

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../gui/menu.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.getIcons().add(new Image("file:icon.png"));
                stage.setScene(new Scene(root1));
                stage.show();
            } else if (msgs.getOps().equals("NOT_Confirmed")) {
                System.out.println("not valid");

            }

            LoginController.ress = "msgs.getOps()";
        } catch (Exception ex) {
            System.out.println("error at server CONN" + ex);
        }

        serverConnCon(socket);

    }

    public static void serverConnCon(Socket socket) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
//                    while (true) {
//                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public static void clientInvocation (String header) {
        Booking bk = new Booking(1,2,3,"12","a",22,"intellij");
        try {
            System.out.println("aaa");
            oos.writeObject(new Message("addBooking",bk));
            oos.flush();
        }catch(Exception e){
            System.out.println("at client inooov: " + e);
        }
    }
}
