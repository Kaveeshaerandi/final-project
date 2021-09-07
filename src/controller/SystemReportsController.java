package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Income;
import model.OrderDetails;
import view.tm.CartTm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SystemReportsController {
    public AnchorPane reportIncome;
    public AnchorPane dailyIncomeContext;
    public AnchorPane monthlyContext;
    public AnchorPane annualContext;
    public DatePicker datePick;
    public TableView tblIncome;
    public TableColumn colItemCodeIncome;
    public TableColumn colDescriptionIncome;
    public TableColumn colQtyOnHandIncome;
    public TableColumn colSellQty;
    public TableColumn colDailyIncome;
    public Label lblTotalIncome;

    public  void  initialize(){


        colItemCodeIncome.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescriptionIncome.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQtyOnHandIncome.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colSellQty.setCellValueFactory(new PropertyValueFactory<>("sellingQty"));
        colDailyIncome.setCellValueFactory(new PropertyValueFactory<>("dailyIncome"));

    }

    public void gotoAdminOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) reportIncome.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/SystemAdministrator.fxml"))));
    }

    public void dailyIncome(ActionEvent actionEvent) {
        dailyIncomeContext.setVisible(true);
        monthlyContext.setVisible(false);
        annualContext.setVisible(false);

    }

    public void monthIncomeOnAction(ActionEvent actionEvent) {
        monthlyContext.setVisible(true);
        annualContext.setVisible(false);
        dailyIncomeContext.setVisible(false);
        new Alert(Alert.AlertType.WARNING, "Monthly has not pass", ButtonType.CLOSE).show();


    }

    public void annualIncomeOnAction(ActionEvent actionEvent) {
        annualContext.setVisible(true);
        monthlyContext.setVisible(false);
        dailyIncomeContext.setVisible(false);
        new Alert(Alert.AlertType.WARNING, "Year has not pass", ButtonType.CLOSE).show();


    }

    public void searchDateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

       ArrayList<Income> orderIncome = new OrderController().getDailyIncome(String.valueOf(datePick.getValue()));
        setData( orderIncome);
    }
    ObservableList<Income> obListItem= FXCollections.observableArrayList();

    private void setData(ArrayList<Income> orderDetailRecord) {
        double totalIncome=0;
        if(orderDetailRecord!=null){
            obListItem.clear();
            for (Income temp:orderDetailRecord
            ) {
                // System.out.println(tm.get);

                obListItem.add(temp);
                totalIncome+=temp.getDailyIncome();

                // tblManageCustomer.getItems().clear();
                tblIncome.setItems(obListItem);
            }

        }
        lblTotalIncome.setText(String.valueOf(totalIncome));
    }
}
