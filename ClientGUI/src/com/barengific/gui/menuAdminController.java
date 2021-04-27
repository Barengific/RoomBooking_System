package com.barengific.gui;

import com.barengific.Messages.Booking;
import com.barengific.Messages.User;
import com.barengific.clientside.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MenuAdminController {

    @FXML
    TextField txtRoom;
    @FXML
    TextField txtAtendees;
    @FXML
    TextField txtEvent;
    @FXML
    DatePicker txtsTime;
    @FXML
    DatePicker txteTime;

    @FXML
    TextField txtAddUsername;
    @FXML
    TextField txtAddUserpass;
    @FXML
    TextField txtRemoveBooking;
    @FXML
    TextField txtRemoveUser;

    @FXML
    private TableView<Booking> tblBooking;
    @FXML
    private TableView<User> tblUser;

    @FXML
    private TableColumn<Booking, String> tblBookingid;
    @FXML
    private TableColumn<Booking, String> tblRoom;
    @FXML
    private TableColumn<Booking, String> tblStaffid;
    @FXML
    private TableColumn<Booking, String> tblRecurring;
    @FXML
    private TableColumn<Booking, String> tblsTime;
    @FXML
    private TableColumn<Booking, String> tbleTime;
    @FXML
    private TableColumn<Booking, String> tblAttendees;
    @FXML
    private TableColumn<Booking, String> tblEvent;

    @FXML
    private TableColumn<User, String> tblUsername;
    @FXML
    private TableColumn<User, String> tblPassword;
    @FXML
    private TableColumn<User, String> tblIsAdmin;


    public static ObservableList<Booking> olBooking = FXCollections.observableArrayList();
    public static ObservableList<User> olUser = FXCollections.observableArrayList();

    public void refreshBooking(ActionEvent event) {
        //Main.state = "refreshBooking";
        tblBookingid.setCellValueFactory(new PropertyValueFactory<Booking, String>("bookingID"));
        tblRoom.setCellValueFactory(new PropertyValueFactory<Booking, String>("roomNo"));
        tblStaffid.setCellValueFactory(new PropertyValueFactory<Booking, String>("staffID"));
        tblRecurring.setCellValueFactory(new PropertyValueFactory<Booking, String>("recurringID"));
        tblsTime.setCellValueFactory(new PropertyValueFactory<Booking, String>("sTime"));
        tbleTime.setCellValueFactory(new PropertyValueFactory<Booking, String>("eTime"));
        tblAttendees.setCellValueFactory(new PropertyValueFactory<Booking, String>("estAttend"));
        tblEvent.setCellValueFactory(new PropertyValueFactory<Booking, String>("eventName"));

        tblUsername.setCellValueFactory(new PropertyValueFactory<User, String>("Username"));
        tblPassword.setCellValueFactory(new PropertyValueFactory<User, String>("Password"));
        tblIsAdmin.setCellValueFactory(new PropertyValueFactory<User, String>("isAdmin"));

        tblBooking.getItems().setAll(olBooking);
        tblUser.getItems().setAll(olUser);

        //tblBooking.setItems(data);
        System.out.println("done");
    }

    public void addBooking() {
        txtRoom.getText();
        txtAtendees.getText();
        txtEvent.getText();
        //txtsTime.getChronology();
        //txteTime.getChronology();
        Main.clientInvocation("addBooking");
    }

    public void removeBooking() {
        //Main.state = "removeBooking";
    }

    public void addUser() {
        //Main.state = "addUser";
    }

    public void removeUser() {
        //Main.state = "removeUser";
    }

}
