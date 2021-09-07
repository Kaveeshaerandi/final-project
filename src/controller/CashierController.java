package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CashierController {
    public AnchorPane cashierContext;

    public void makeCustomerOrderOnAction(ActionEvent actionEvent) throws IOException {
        /*Stage window = (Stage) cashierContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AddNewCustomer.fxml"))));*/
    }
/*
    public Label date;
    public void initialize(){
        loadDateAndTime();
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        date.setText(f.format(date));
    }*/
}
