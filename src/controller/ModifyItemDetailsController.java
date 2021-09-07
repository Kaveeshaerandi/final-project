package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Item;

import java.sql.SQLException;

public class ModifyItemDetailsController {
    public AnchorPane modifyItemDetailsOnAction;
    public TextField txtItemCode;
    public TextField txtDescription;
    public TextField txtPackSize;
    public TextField txtUnitPrice;
    public TextField txtQtyOnHand;
    public TextField txtDiscount;

    public void selectItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String idItem =txtItemCode.getText();
        Item i= new ItemController().getItem(idItem);
        if (i==null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();

        }else {
            setData(i);

        }
    }


    public void modifyItemDetailsOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Item i=new Item(
                txtItemCode.getText(),txtDescription.getText(),Double.parseDouble(txtPackSize.getText()),
                Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQtyOnHand.getText()),Double.parseDouble(txtDiscount.getText())
        );
        if (new ItemController().modifyItem(i)){
            new Alert(Alert.AlertType.CONFIRMATION, "Modified..").show();

        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();

        }
    }

    void setData(Item i){
        txtItemCode.setText(i.getItemCode());
        txtDescription.setText(i.getDescription());
        txtPackSize.setText(String.valueOf(i.getPackSize()));
        txtUnitPrice.setText(String.valueOf(i.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(i.getQtyOnHand()));
        txtDiscount.setText(String.valueOf(i.getDiscount()));
    }

}
