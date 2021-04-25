package com.barengific.gui;

import com.barengific.clientside.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author barengific
 */

public class LoginController {

    public static String ress;

    @FXML
    Label res;
    @FXML
    TextField txtUsername;
    @FXML
    TextField txtPassword;

    public void onLogin(){
        System.out.println("aaaaa here button pressed");
        System.out.println(ress);
        res.setText(ress);
        try {
            Main.serverConn(txtUsername.getText(), txtPassword.getText());
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public static void ressM(String a){
        ress = a;
    }
}
