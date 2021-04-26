package com.barengific.gui;

import com.barengific.Messages.Booking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author barengific
 */

public class MenuController {

    @FXML
    private TableView<Booking> tblBooking;

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



    public void MakeBooking(){

    }

//    final ObservableList<Booking> data = FXCollections.observableArrayList(
//                    new Booking(1, 12, 11, 98,"mon","tue",10,"rollball"),
//                    new Booking(2, 23, 22, 87, "tue", "wed", 20, "khokho"),
//                    new Booking(3, 34, 33, 76, "wed", "thu",30, "programming"),
//                    new Booking(4, 45, 44, 65, "thu", "fri",40, "science"),
//                    new Booking(5, 56, 55, 54, "fri", "sat", 50, "math")
//    );
//    public void RefreshBooking(){
//        tblBookingid.setCellValueFactory(new PropertyValueFactory<Booking, String>("bookingID"));
//        tblRoom.setCellValueFactory(new PropertyValueFactory<Booking, String>("roomNo"));
//        tblStaffid.setCellValueFactory(new PropertyValueFactory<Booking, String>("staffID"));
//        tblRecurring.setCellValueFactory(new PropertyValueFactory<Booking, String>("recurringID"));
//        tblsTime.setCellValueFactory(new PropertyValueFactory<Booking, String>("sTime"));
//        tbleTime.setCellValueFactory(new PropertyValueFactory<Booking, String>("fTime"));
//        tblAttendees.setCellValueFactory(new PropertyValueFactory<Booking, String>("estAttend"));
//        tblEvent.setCellValueFactory(new PropertyValueFactory<Booking, String>("eventName"));
//
//        tblBooking.getItems().setAll(data);
//        //tblBooking.setItems(data);
//        System.out.println("done");
//    }

}
