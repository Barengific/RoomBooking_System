package com.barengific.gui;

import com.barengific.clientside.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    @FXML
    Button btnLogs;

    public void onLogin(ActionEvent event){
        if(!txtUsername.getText().equals("") && !txtPassword.getText().equals("")){
            try {
                Main.serverConn(txtUsername.getText(), txtPassword.getText());

                res.setText(ress);
                //((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            }catch (Exception ex){
                System.out.println(ex);
            }
        }else{
            res.setText("Username and password cannot be empty!");
        }
    }

//    public static void handleClose() {
//        Stage stage = (Stage) res.getScene().getWindow();
//        stage.close();
//    }

}
