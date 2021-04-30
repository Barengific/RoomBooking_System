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

            MenuAdminController.usern = uname;
            MenuAdminController.userp = upass;
            MenuAdminController.staffi = msgs.getRmID();

            if (msgs.getOps().equals("confirmed_Super")) {
                LoginController.ress = "Valid Logging In";
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../gui/menuAdmin.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.getIcons().add(new Image("file:icon.png"));
                stage.setScene(new Scene(root1));
                stage.show();
                MenuAdminController.isa = true;
                msgs = (Message) ois.readObject();
                tableFill(msgs);
            }
            else if (msgs.getOps().equals("confirmed_User")) {
                LoginController.ress = "Valid Logging In";
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../gui/menu.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.getIcons().add(new Image("file:icon.png"));
                stage.setScene(new Scene(root1));
                stage.show();
                tableFill(msgs);
            }
            else if (msgs.getOps().equals("NOT_Confirmed")) {
                LoginController.ress = "Login Details Not Valid";
            }

        } catch (Exception ex) {
            System.out.println("error at server CONN" + ex);
        }
    }

    public static void serverRefresh(){
        try{
            oos.writeObject(new Message("refresh"));
            oos.flush();

            Message msgs = (Message) ois.readObject();
            tableFill(msgs);

            MenuAdminController.outFromServer = "Data Successfully Refreshed";
        }catch (Exception e){
            System.out.println("rr at refresh server");
        }
    }

    public static void serverInvoke(Message msg){
        try {
            oos.writeObject(msg);
            oos.flush();
            Message msgs = (Message) ois.readObject();
            System.out.println(msgs.getOps());
            MenuAdminController.outFromServer = msgs.getOps();

        }catch(Exception e){
            System.out.println("errroe at ADDDEERR " + e);
        }
    }

    public static void tableFill(Message mes){
        for (int i = 0; i < mes.getArrBooking().size(); i++) {
            MenuAdminController.olBooking.add(new Booking(
                    mes.getArrBooking().get(i).getBookingID(),
                    mes.getArrBooking().get(i).getRoomNo(),
                    mes.getArrBooking().get(i).getStaffID(),
                    mes.getArrBooking().get(i).getRecurringID(),
                    mes.getArrBooking().get(i).getSTime(),
                    mes.getArrBooking().get(i).getETime(),
                    mes.getArrBooking().get(i).getEstAttend(),
                    mes.getArrBooking().get(i).getEventName()));
        }
        for (int i = 0; i < mes.getArrRoom().size(); i++) {
            MenuAdminController.olRoom.add(new Room(
                    mes.getArrRoom().get(i).getRoomNo(),
                    mes.getArrRoom().get(i).getCapacity(),
                    mes.getArrRoom().get(i).getType(),
                    mes.getArrRoom().get(i).getPhoneNo()));
        }
        for (int i = 0; i < mes.getArrStaff().size(); i++) {
            MenuAdminController.olStaff.add(new Staff(
                    mes.getArrStaff().get(i).getStaffID(),
                    mes.getArrStaff().get(i).getFirstName(),
                    mes.getArrStaff().get(i).getLastName(),
                    mes.getArrStaff().get(i).getOffice(),
                    mes.getArrStaff().get(i).getEmail(),
                    mes.getArrStaff().get(i).getPhoneNo()));
        }
        for (int i = 0; i < mes.getArrUser().size(); i++) {
            MenuAdminController.olUser.add(new User(
                    mes.getArrUser().get(i).getUsername(),
                    mes.getArrUser().get(i).getStaffID(),
                    mes.getArrUser().get(i).getPassword(),
                    mes.getArrUser().get(i).getIsAdmin()));
        }
    }
}
