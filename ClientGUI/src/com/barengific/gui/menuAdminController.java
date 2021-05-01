package com.barengific.gui;

import com.barengific.Messages.*;
import com.barengific.clientside.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.util.Scanner;

public class MenuAdminController {

    //add booking
    @FXML
    TextField txtAddRoomBooking;
    @FXML
    TextField txtAtendees;
    @FXML
    TextField txtEvent;
    @FXML
    TextField txtAddRecurring;
    @FXML
    DatePicker txtsTime;
    @FXML
    TextField txtAddsHour;
    @FXML
    DatePicker txteTime;
    @FXML
    TextField txtAddeHour;
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
    public TextArea taFromServer;
    public static String outFromServer = "Refreshed";

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

    public boolean sens = true;
    public static String usern = "default";
    public static String userp = "default";
    public static int staffi = 0;
    public static boolean isa = false;

    @FXML
    Text textLoginAs;
    @FXML
    Text textStaffIDas;
    @FXML
    Text textPrivilege;

    public void refreshBooking() {
        textLoginAs.setText("Logged in as: " + usern);
        textStaffIDas.setText("Staff ID: " + staffi);
        textPrivilege.setText("Admin: " + isa);

        if (sens) {
            sens = false;
        } else {
            olBooking.clear();
            olRoom.clear();
            olStaff.clear();
            olUser.clear();
            tblBooking.getItems().clear();
            tblRoom.getItems().clear();
            tblStaff.getItems().clear();
            tblUser.getItems().clear();
            Main.serverRefresh();
        }
        taFromServer.setText(outFromServer);
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
    }

    public void resetUser() {
        if (isa = false && txtAddUsername.getText().equals(usern)) {
            taFromServer.setText("Caution: You Are Not An Admin!\nYou Cannot Only Change Your Own Password");
        } else {
            if (!txtAddUsername.getText().equals("") && !txtAddUserpass.getText().equals("")) {
                Main.serverInvoke(new Message("resetUser", new User(txtAddUsername.getText(), txtAddUserpass.getText())));
                taFromServer.setText(outFromServer);

                txtAddUsername.clear();
                txtAddUserpass.clear();
            } else {
                taFromServer.setText("Username and New Password \nCannot Be Empty");
            }
        }
    }

    //
    //adds
    //
    public void addBooking() {
        if (txtsTime.getValue() == null || txteTime.getValue() == null) {
            taFromServer.setText("Bookings Dates Cannot Be Empty!");
        } else {
            if (!txtsTime.getValue().equals("") && !txteTime.getValue().equals("")
                    && !txtAddsHour.getText().equals("") && !txtAddeHour.getText().equals("")) {

                String ssDateTime = txtsTime.getValue().toString() + " " + txtAddsHour.getText() + ":00.0";
                String eeDateTime = txteTime.getValue().toString() + " " + txtAddeHour.getText() + ":00.0";

                if (!txtAddRoomBooking.getText().equals("") && !txtAddRecurring.getText().equals("") && !ssDateTime.equals("")
                        && !eeDateTime.equals("") && !txtAtendees.getText().equals("") && !txtEvent.getText().equals("")) {

                    if (isInteger(txtAddRoomBooking.getText()) && isInteger(txtAddRecurring.getText()) && isInteger(txtAtendees.getText())) {
                        Main.serverInvoke(new Message("addBooking", new Booking(Integer.parseInt(txtAddRoomBooking.getText()), staffi,
                                Integer.parseInt(txtAddRecurring.getText()), ssDateTime, eeDateTime,
                                Integer.parseInt(txtAtendees.getText()), txtEvent.getText())));
                        taFromServer.setText(outFromServer);
                        txtAddRoomBooking.clear();
                        txtAddRecurring.clear();
                        txtsTime.getEditor().clear();
                        txteTime.getEditor().clear();
                        txtAtendees.clear();
                        txtEvent.clear();
                        txtAddsHour.clear();
                        txtAddeHour.clear();
                    } else {
                        taFromServer.setText("Bookings Fields; Room No#, Recurring Weeks \nAnd No# Attendees Must Be An Integer!");
                    }
                } else {
                    taFromServer.setText("Bookings Fields Cannot Be Empty!");
                }
            } else {
                taFromServer.setText("Bookings Dates Cannot Be Empty!");
            }
        }
    }

    public void addRoom() {
        if (!txtRoomNo.getText().equals("") && !txtCapacity.getText().equals("") && !txtType.getText().equals("")
                && !txtRoomPhone.getText().equals("")) {
            if (isInteger(txtRoomNo.getText()) && isInteger(txtCapacity.getText()) && isInteger(txtRoomPhone.getText())) {
                Main.serverInvoke(new Message("addRoom", new Room(Integer.parseInt(txtRoomNo.getText()), Integer.parseInt(txtCapacity.getText()),
                        txtType.getText(), Integer.parseInt(txtRoomPhone.getText()))));
                taFromServer.setText(outFromServer);
                txtRoomNo.clear();
                txtCapacity.clear();
                txtType.clear();
                txtRoomPhone.clear();
            } else {
                taFromServer.setText("Room No#, Capacity and Phone No# \nMust Be An Integer!");
            }
        } else {
            taFromServer.setText("Room No#, Capacity, Type and Phone No# \nCannot Be Empty!");
        }
    }

    public void addStaff() {
        if(isa == true) {
            if (!txtFirstname.getText().equals("") && !txtLastname.getText().equals("") && !txtOffice.getText().equals("")
                    && !txtEmail.getText().equals("") && !txtStaffPhone.getText().equals("")) {
                if (isInteger(txtOffice.getText()) && isInteger(txtStaffPhone.getText())) {
                    Main.serverInvoke(new Message("addStaff", new Staff(txtFirstname.getText(), txtLastname.getText(),
                            Integer.parseInt(txtOffice.getText()), txtEmail.getText(), Long.parseLong(txtStaffPhone.getText()))));
                    taFromServer.setText(outFromServer);
                    txtFirstname.clear();
                    txtLastname.clear();
                    txtOffice.clear();
                    txtEmail.clear();
                    txtStaffPhone.clear();
                } else {

                }
            } else {
                taFromServer.setText("Staff First Name, Last Name, Office No#, \nEmail and Phone No# Cannot Be Empty!");
            }
        }else{
            taFromServer.setText("You Are Not An Admin, Error Adding Staff");
        }
    }

    public void addUser() {
        if(isa = true) {
            if (!txtAddUsername.getText().equals("") && !txtAdduserStaff.getText().equals("") && !txtAddUserpass.getText().equals("")) {
                if (isInteger(txtAdduserStaff.getText())) {
                    Main.serverInvoke(new Message("addUser", new User(txtAddUsername.getText(), Integer.parseInt(txtAdduserStaff.getText()),
                            txtAddUserpass.getText(), ckbxIsAdmin.isSelected())));
                    taFromServer.setText(outFromServer);

                    txtAddUsername.clear();
                    txtAddUserpass.clear();
                    txtAdduserStaff.clear();
                    ckbxIsAdmin.disarm();
                } else {

                }
            } else {
                taFromServer.setText("Username, Password And \nStaff ID Cannot Be Empty!");
            }
        }else{
            taFromServer.setText("You Are Not An Admin, Error Adding User");
        }
    }

    //
    //removes
    //
    public void removeBooking() {
        if (!txtRemoveBooking.getText().equals("")) {
            if (isInteger(txtRemoveBooking.getText())) {
                Main.serverInvoke(new Message("removeBooking", Integer.parseInt(txtRemoveBooking.getText())));
                taFromServer.setText(outFromServer);
                txtRemoveBooking.clear();
            } else {
                taFromServer.setText("Remove Booking ID Must Be An Integer!");
            }
        } else {
            taFromServer.setText("Remove Booking ID Cannot Be Empty!");
        }
    }

    public void removeRoom() {
        if (!txtRemoveRoom.getText().equals("")) {
            if (isInteger(txtRemoveRoom.getText())) {
                Main.serverInvoke(new Message("removeRoom", Integer.parseInt(txtRemoveRoom.getText())));
                taFromServer.setText(outFromServer);
                txtRemoveRoom.clear();
            } else {
                taFromServer.setText("Remove Room No# Must Be An Integer!");
            }
        } else {
            taFromServer.setText("Remove Room No# Cannot Be Empty!");
        }
    }

    public void removeStaff() {
        if(isa = true) {
            if (!txtRemoveStaff.getText().equals("")) {
                if (isInteger(txtRemoveStaff.getText())) {
                    Main.serverInvoke(new Message("removeStaff", Integer.parseInt(txtRemoveStaff.getText())));
                    taFromServer.setText(outFromServer);
                    txtRemoveStaff.clear();
                } else {
                    taFromServer.setText("Remove Staff ID Must Be An Integer!");
                }
            } else {
                taFromServer.setText("Remove Staff ID Cannot Be Empty!");
            }
        }else{
            taFromServer.setText("You Are Not An Admin, Error Removing Staff");
        }
    }

    public void removeUser() {
        if(isa = true) {
            if (!txtRemoveUser.getText().equals("")) {
                Main.serverInvoke(new Message("removeUser", txtRemoveUser.getText()));
                taFromServer.setText(outFromServer);
                txtRemoveUser.clear();
            } else {
                taFromServer.setText("Remove Username Cannot Be Empty!");
            }
        }else{
            taFromServer.setText("You Are Not An Admin, Error Removing User");
        }
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

}
