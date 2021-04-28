package com.barengific.gui;

import com.barengific.Messages.*;
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
    public static String qwert = "aaa";

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

    public void refreshBooking(ActionEvent event) {
        if(sens){
            sens = false;
        }else{
            System.out.println("yeeeeh");
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
        taFromServer.setText("From Server");
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

    public void addBooking() {
        //txtAddsHour
        //txtAddeHour
//        Main.serverAdder(new Message("addBooking", new Booking(txtAddRoomBooking.getText(),
//                txtAddRecurring.getText(),txtsTime.getValue(),txteTime.getValue(),txtAtendees.getText(),txtEvent.getText())));

        //System.out.println("addding -room- ");
        //taFromServer.setText(qwert);

        //txtAddRoomBooking.clear();
        //txtAddRecurring.clear();
        //txtsTime.
        //txtAtendees.clear();
        //txtEvent.clear();

    }

    public void addRoom() {
        //check whether roomno, capacity and phoneno are int before senind to server
        Main.serverAddRoom(new Room(Integer.valueOf(txtRoomNo.getText()), Integer.valueOf(txtCapacity.getText()),
                txtType.getText(), Integer.valueOf(txtRoomPhone.getText())));

        System.out.println("addding -room- ");
        taFromServer.setText(qwert);

        txtRoomNo.clear();
        txtCapacity.clear();
        txtType.clear();
        txtRoomPhone.clear();
    }

    public void addStaff() {
        Main.serverAdder(new Message("addStaff", new Staff(txtFirstname.getText(), txtLastname.getText(),
                Integer.valueOf(txtOffice.getText()), txtEmail.getText(), Long.valueOf(txtStaffPhone.getText()))));

        System.out.println("adding stafff -- ");
        taFromServer.setText(qwert);

        txtFirstname.clear();
        txtLastname.clear();
        txtOffice.clear();
        txtEmail.clear();
        txtStaffPhone.clear();
    }

    public void addUser() {
        Main.serverAdder(new Message("addUser", new User(txtAddUsername.getText(), txtAddUserpass.getText(), ckbxIsAdmin.isSelected())));

        System.out.println("adding userssss -- ");
        taFromServer.setText(qwert);

        txtAddUsername.clear();
        txtAddUsername.clear();
        ckbxIsAdmin.disarm();
    }

    public void removeBooking() {
        //Main.state = "removeBooking";
        //catch exception if booking id is not a integer
        Main.serverRemove("removeBooking", Integer.valueOf(txtRemoveBooking.getText()));
        taFromServer.setText(qwert);

        txtRemoveBooking.clear();
    }

    public void removeRoom() {
        Main.serverRemove("removeRoom", Integer.valueOf(txtRemoveRoom.getText()));
        taFromServer.setText(qwert);

        txtRemoveRoom.clear();
    }

    public void removeStaff() {
        Main.serverRemove("removeStaff", Integer.valueOf(txtRemoveRoom.getText()));
        taFromServer.setText(qwert);

        txtRemoveRoom.clear();
    }

    public void removeUser() {
        //Main.state = "removeUser";

        txtRemoveUser.clear();
    }

    public void resetUser() {



        txtAddUsername.clear();
        txtAddUserpass.clear();

        olBooking.clear();
        olRoom.clear();
        olStaff.clear();
        olUser.clear();
        tblBooking.getItems().clear();
        tblRoom.getItems().clear();
        tblStaff.getItems().clear();
        tblUser.getItems().clear();
    }

}
