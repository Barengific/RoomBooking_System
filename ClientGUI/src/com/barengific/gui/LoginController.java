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

    public static String ress = "";

    @FXML
    Label res;
    @FXML
    TextField txtUsername;
    @FXML
    TextField txtPassword;
    @FXML
    Button btnLogs;


    private MenuAdminController aaa;

    public void MenuAdminController(MenuAdminController aaa) {
        this.aaa = aaa;
    }

    public void onLogin(){
        if(!txtUsername.getText().equals("") && !txtPassword.getText().equals("")){
            try {
                Main.serverConn(txtUsername.getText(), txtPassword.getText());
                res.setText(ress);
                if(!ress.equals( "Login Details Not Valid")){
                    Stage stage = (Stage) btnLogs.getScene().getWindow();
                    stage.close();
                }
            }catch (Exception ex){
                System.out.println(ex);
            }
        }else{
            res.setText("Username and password \ncannot be empty!");
        }
    }

//    public static void handleClose() {
//        Stage stage = (Stage) res.getScene().getWindow();
//        stage.close();
//    }

}
