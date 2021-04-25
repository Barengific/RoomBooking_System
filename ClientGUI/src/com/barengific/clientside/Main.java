package com.barengific.clientside;

import com.barengific.Messages.Message;
import com.barengific.Messages.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.barengific.gui.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
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
//        Scene scene = new Scene(root);
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
            LoginController.ress = "msgs.getOps()";

            user = new User(uname, upass);
            oos.writeObject(user);
            oos.flush();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


}
