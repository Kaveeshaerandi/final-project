package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import model.Item;

import java.sql.SQLException;

public class RegisterNewItemController {
    public AnchorPane registerNewItemContext;
    public TextField txtItemCode;
    public TextField txtDescription;
    public TextField txtPackSize;
    public TextField txtUnitPrice;
    public TextField txtQtyOnHand;
    public TextField txtDiscount;

    public void registerNewItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item i=new Item(
                txtItemCode.getText(),txtDescription.getText(),Double.parseDouble(txtPackSize.getText()),
                Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQtyOnHand.getText()),Double.parseDouble(txtDiscount.getText())
        );

        if (new ItemController().saveItem(i)){
            new Alert(Alert.AlertType.CONFIRMATION, "Save..").show();

        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();

        }

    }
}
