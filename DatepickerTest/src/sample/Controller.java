package sample;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

//import java.time.LocalDate;

public class Controller {

    @FXML
    DatePicker datepicker;

    public void getBtnDate(){
        datepicker.valueProperty();
        System.out.println( datepicker.getValue());
    }

}
