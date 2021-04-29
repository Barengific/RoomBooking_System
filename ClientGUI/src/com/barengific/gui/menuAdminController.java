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
    public static String qwert = "Click Refresh Button";

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

    public void refreshBooking(ActionEvent event) {
        System.out.println(usern + " " + userp + " " + staffi + " " + isa);

        textLoginAs.setText("Logged in as: " + usern);
        textStaffIDas.setText("Staff ID: " + staffi);
        textPrivilege.setText("Admin: " + isa);

        if (sens) {
            sens = false;
        } else {
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
        taFromServer.setText(qwert);
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
        Main.serverInvoke(new Message("resetUser", new User(txtAddUsername.getText(), txtAddUserpass.getText())));
        taFromServer.setText(qwert);

        txtAddUsername.clear();
        txtAddUserpass.clear();
    }

    //
    //adds
    //
    public void addBooking() {
        String ssDateTime = txtsTime.getValue().toString() + " " + txtAddsHour.getText() + ":00.0";
        String eeDateTime = txteTime.getValue().toString() + " " + txtAddeHour.getText() + ":00.0";

        Main.serverInvoke(new Message("addBooking", new Booking(Integer.parseInt(txtAddRoomBooking.getText()), staffi,
                Integer.parseInt(txtAddRecurring.getText()), ssDateTime, eeDateTime,
                Integer.parseInt(txtAtendees.getText()), txtEvent.getText())));

        taFromServer.setText(qwert);

        txtAddRoomBooking.clear();
        txtAddRecurring.clear();
        txtsTime.getEditor().clear();
        txteTime.getEditor().clear();
        txtAtendees.clear();
        txtEvent.clear();
        txtAddsHour.clear();
        txtAddeHour.clear();
    }

    public void addRoom() {
        //check whether roomno, capacity and phoneno are int before senind to server
        Main.serverInvoke(new Message("addRoom", new Room(Integer.parseInt(txtRoomNo.getText()), Integer.parseInt(txtCapacity.getText()),
                txtType.getText(), Integer.parseInt(txtRoomPhone.getText()))));

        taFromServer.setText(qwert);

        txtRoomNo.clear();
        txtCapacity.clear();
        txtType.clear();
        txtRoomPhone.clear();
    }

    public void addStaff() {
        Main.serverInvoke(new Message("addStaff", new Staff(txtFirstname.getText(), txtLastname.getText(),
                Integer.parseInt(txtOffice.getText()), txtEmail.getText(), Long.parseLong(txtStaffPhone.getText()))));

        taFromServer.setText(qwert);

        txtFirstname.clear();
        txtLastname.clear();
        txtOffice.clear();
        txtEmail.clear();
        txtStaffPhone.clear();
    }

    public void addUser() {
        //need to find staff idd
        Main.serverInvoke(new Message("addUser", new User(txtAddUsername.getText(), Integer.parseInt(txtAdduserStaff.getText()),
                txtAddUserpass.getText(), ckbxIsAdmin.isSelected())));
        taFromServer.setText(qwert);

        txtAddUsername.clear();
        txtAddUserpass.clear();
        txtAdduserStaff.clear();
        ckbxIsAdmin.disarm();
    }

    //
    //removes
    //
    public void removeBooking() {
        //catch exception if booking id is not a integer
        Main.serverInvoke(new Message("removeBooking", Integer.parseInt(txtRemoveBooking.getText())));
        taFromServer.setText(qwert);

        txtRemoveBooking.clear();
    }

    public void removeRoom() {
        Main.serverInvoke(new Message("removeRoom", Integer.parseInt(txtRemoveRoom.getText())));
        taFromServer.setText(qwert);

        txtRemoveRoom.clear();
    }

    public void removeStaff() {
        Main.serverInvoke(new Message("removeStaff", Integer.parseInt(txtRemoveStaff.getText())));
        taFromServer.setText(qwert);

        txtRemoveStaff.clear();
    }

    public void removeUser() {
        Main.serverInvoke(new Message("removeUser", txtRemoveUser.getText()));
        taFromServer.setText(qwert);

        txtRemoveUser.clear();
    }

}
