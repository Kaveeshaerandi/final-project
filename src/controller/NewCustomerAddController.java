package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.sql.SQLException;

public class NewCustomerAddController {
    public AnchorPane updateCustomerForm;
    public AnchorPane deleteCustomerForm;
    public AnchorPane addCustomer;
    public JFXButton addNew;
    public JFXButton update;
    public JFXButton delete;
    public AnchorPane newCustomerAddForm;
    public TextField txtId;
    public ComboBox cmbTitle;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtCity;
    public TextField txtProvince;
    public TextField txtPostalCode;
    public TextField txtCustId;
    public ComboBox cmbcustTitle;
    public TextField txtCustName;
    public TextField txtCustAddress;
    public TextField txtCustCity;
    public TextField txtCustProvince;
    public TextField txtPostal;
    public TextField txtdelId;
    public ComboBox cmbDelTitle;
    public TextField txtDelName;
    public TextField txtDelAddress;
    public TextField txtDelCity;
    public TextField txtDelProvince;
    public TextField txtDelPostal;

    public void  initialize(){
        cmbTitle.getItems().addAll("Mrs","Miss");
        cmbcustTitle.getItems().addAll("Mrs","Miss");
        cmbDelTitle.getItems().addAll("Mrs","Miss");
    }

    public void addNewCustomer(ActionEvent actionEvent) {
        addCustomer.setVisible(true);
            updateCustomerForm.setVisible(false);
            deleteCustomerForm.setVisible(false);
    }

    public void updateCustomer(ActionEvent actionEvent) {
        updateCustomerForm.setVisible(true);
        addCustomer.setVisible(false);
        deleteCustomerForm.setVisible(false);
    }

    public void deleteCustomer(ActionEvent actionEvent) {
        deleteCustomerForm.setVisible(true);
        updateCustomerForm.setVisible(false);
        addCustomer.setVisible(false);
    }

    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1 = new Customer(
                txtId.getText(), String.valueOf(cmbTitle.getValue()),
                txtName.getText(),txtAddress.getText(),txtCity.getText(),txtProvince.getText(),txtPostalCode.getText()
        );
        if(new CustomerController().saveCustomer(c1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1= new Customer(
                txtCustId.getText(),cmbcustTitle.getAccessibleText(),
                txtCustName.getText(),txtCustAddress.getText(),txtCustCity.getText(),txtCustProvince.getText(),txtCustProvince.getText()
        );


        if (new CustomerController().updateCustomer(c1))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
    }

    public void idOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtCustId.getText();

        Customer c1= new CustomerController().getCustomer(customerId);
        if (c1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(c1);
        }

    }
    void setData(Customer c){
        txtCustId.setText(c.getCustId());
        cmbTitle.getSelectionModel();
        txtCustName.setText(c.getCustName());
        txtAddress.setText(c.getCustAddress());
        txtCustCity.setText(c.getCity());
        txtCustProvince.setText(c.getProvince());
        txtCustProvince.setText(c.getProvince());

    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtdelId.getText();

        Customer c1= new CustomerController().getCustomer(customerId);
        if (c1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setDataDel(c1);
        }
    }

    private void setDataDel(Customer c1) {
        txtdelId.setText(c1.getCustId());
        cmbDelTitle.getSelectionModel();
        txtDelName.setText(c1.getCustName());
        txtDelAddress.setText(c1.getCustAddress());
        txtDelCity.setText(c1.getCity());
        txtDelProvince.setText(c1.getProvince());
        txtDelPostal.setText(c1.getProvince());
    }

    public void addCustIdOnAction(ActionEvent actionEvent) {
        String x=String.valueOf(cmbTitle.getValue());
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) newCustomerAddForm.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AddNewCustomer.fxml"))));
    }
}
