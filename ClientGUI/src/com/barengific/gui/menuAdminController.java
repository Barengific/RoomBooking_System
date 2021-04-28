package com.barengific.gui;

import com.barengific.Messages.Booking;
import com.barengific.Messages.Room;
import com.barengific.Messages.Staff;
import com.barengific.Messages.User;
import com.barengific.clientside.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

import java.sql.Date;

//import java.sql.Date;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;


public class MenuAdminController {

    //add booking
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
    //add room
    @FXML
    TextField txtRoomNo;
    @FXML
    TextField txtCapacity;
    @FXML
    TextField txtRoomPhone;
    @FXML
    TextField txtType;
    //add staff
    @FXML
    TextField txtFirstname;
    @FXML
    TextField txtLastname;
    @FXML
    TextField txtOffice;
    @FXML
    TextField txtEmail;
    @FXML
    TextField txtStaffPhone;
    //add user
    @FXML
    TextField txtAddUsername;
    @FXML
    TextField txtAddUserpass;
    @FXML
    TextField txtAdduserStaff;
    @FXML
    CheckBox ckbxIsAdmin;

    //removes
    @FXML
    TextField txtRemoveBooking;
    @FXML
    TextField txtRemoveUser;
    @FXML
    TextField txtRemoveRoom;
    @FXML
    TextField txtRemoveStaff;

    @FXML
    private TableView<Booking> tblBooking;
    @FXML
    private TableView<User> tblUser;
    @FXML
    private TableView<Room> tblRoom;
    @FXML
    private TableView<Staff> tblStaff;
    //booking table
    @FXML
    private TableColumn<Booking, String> tblBookingid;
    @FXML
    private TableColumn<Booking, String> tblBookRoom;
    @FXML
    private TableColumn<Booking, String> tblBookStaffid;
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
    //user table
    @FXML
    private TableColumn<User, String> tblUsername;
    @FXML
    private TableColumn<User, String> tblPassword;
    @FXML
    private TableColumn<User, String> tblUserStaffid;
    @FXML
    private TableColumn<User, String> tblIsAdmin;
    //room table
    @FXML
    private TableColumn<Room, String> tblRoomNo;
    @FXML
    private TableColumn<Room, String> tblType;
    @FXML
    private TableColumn<Room, String> tblCapa;
    @FXML
    private TableColumn<Room, String> tblRoomPhone;
    //staff table
    @FXML
    private TableColumn<Staff, String> tblStaffid;
    @FXML
    private TableColumn<Staff, String> tblName;
    @FXML
    private TableColumn<Staff, String> tblOffice;
    @FXML
    private TableColumn<Staff, String> tblEmail;
    @FXML
    private TableColumn<Staff, String> tblStaffPhone;

    public static ObservableList<Booking> olBooking = FXCollections.observableArrayList();
    public static ObservableList<User> olUser = FXCollections.observableArrayList();
    public static ObservableList<Room> olRoom = FXCollections.observableArrayList();
    public static ObservableList<Staff> olStaff = FXCollections.observableArrayList();

    public void refreshBooking(ActionEvent event) {
        //Main.state = "refreshBooking";
        tblBookingid.setCellValueFactory(new PropertyValueFactory<Booking, String>("bookingID"));
        tblBookRoom.setCellValueFactory(new PropertyValueFactory<Booking, String>("roomNo"));
        tblBookStaffid.setCellValueFactory(new PropertyValueFactory<Booking, String>("staffID"));
        tblRecurring.setCellValueFactory(new PropertyValueFactory<Booking, String>("recurringID"));
        tblsTime.setCellValueFactory(new PropertyValueFactory<Booking, String>("sTime"));
        tbleTime.setCellValueFactory(new PropertyValueFactory<Booking, String>("eTime"));
        tblAttendees.setCellValueFactory(new PropertyValueFactory<Booking, String>("estAttend"));
        tblEvent.setCellValueFactory(new PropertyValueFactory<Booking, String>("eventName"));

        tblUsername.setCellValueFactory(new PropertyValueFactory<User, String>("Username"));
        tblPassword.setCellValueFactory(new PropertyValueFactory<User, String>("Password"));
        tblUserStaffid.setCellValueFactory(new PropertyValueFactory<User, String>("staffID"));
        tblIsAdmin.setCellValueFactory(new PropertyValueFactory<User, String>("isAdmin"));

        tblRoomNo.setCellValueFactory(new PropertyValueFactory<Room, String>("RoomNo"));
        tblType.setCellValueFactory(new PropertyValueFactory<Room, String>("Type"));
        tblCapa.setCellValueFactory(new PropertyValueFactory<Room, String>("Capacity"));
        tblRoomPhone.setCellValueFactory(new PropertyValueFactory<Room, String>("PhoneNo"));

        tblStaffid.setCellValueFactory(new PropertyValueFactory<Staff, String>("StaffID"));
        tblName.setCellValueFactory(new PropertyValueFactory<Staff, String>("fullName"));
        tblOffice.setCellValueFactory(new PropertyValueFactory<Staff, String>("office"));
        tblEmail.setCellValueFactory(new PropertyValueFactory<Staff, String>("Email"));
        tblStaffPhone.setCellValueFactory(new PropertyValueFactory<Staff, String>("PhoneNo"));

        tblBooking.getItems().setAll(olBooking);
        tblUser.getItems().setAll(olUser);
        tblRoom.getItems().setAll(olRoom);
        tblStaff.getItems().setAll(olStaff);

        //tblBooking.setItems(data);
        System.out.println("done");
    }

    public void addBooking() {
        txtRoom.getText();
        txtAtendees.getText();
        txtEvent.getText();

//        txtsTime.editorProperty();
//
//        txtsTime.getDayCellFactory();
//        txtsTime.getAccessibleText();
//        txtsTime.valueProperty();
//        System.out.println( txtsTime.getValue());

        //java.sql.Date sqlDate = Date.valueOf(txtsTime.getValue());
//        //System.out.println(txtsTime.getValue());
//        //txteTime.getChronology();
//        Main.clientInvocation("addBooking");
//
//        txtsTime.setConverter(new StringConverter<LocalDate>() {
//            String pattern = "yyyy-MM-dd";
//            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
//
//            {
//                txtsTime.setPromptText(pattern.toLowerCase());
//            }
//
//            @Override
//            public String toString(LocalDate date) {
//                if (date != null) {
//                    return dateFormatter.format(date);
//                } else {
//                    return "";
//                }
//            }
//
//            @Override
//            public LocalDate fromString(String string) {
//                if (string != null && !string.isEmpty()) {
//                    return LocalDate.parse(string, dateFormatter);
//                } else {
//                    return null;
//                }
//            }
//        });

    }

    public void addRoom(){

    }

    public void addStaff(){

    }

    public void addUser() {
        //Main.state = "addUser";
    }

    public void removeBooking() {
        //Main.state = "removeBooking";
    }

    public void removeRoom(){

    }

    public void removeStaff(){

    }
    public void removeUser() {
        //Main.state = "removeUser";
    }

    public void resetUser(){

    }

}
