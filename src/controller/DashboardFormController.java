package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {


    public TextField txtUserName;
    public PasswordField txtPassword;
    public AnchorPane dashBorderContext;

    public void singIn(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equalsIgnoreCase("admin") && txtPassword.getText().equalsIgnoreCase("1234")) {
            Stage window = (Stage) dashBorderContext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/SystemAdministrator.fxml"))));
        } else if (txtUserName.getText().equalsIgnoreCase("cash") && txtPassword.getText().equalsIgnoreCase("1234")) {
            Stage window = (Stage) dashBorderContext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AddNewCustomer.fxml"))));


        } else {
            new Alert(Alert.AlertType.WARNING, "Invalid login, please try again", ButtonType.CLOSE).show();

        }
    }
}
