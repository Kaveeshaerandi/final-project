package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import model.*;
import view.tm.CartTm;
import view.tm.CustomerTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AddNewCustomerController {

    public Label lblDate;
    public Label lblTime;
    public AnchorPane placeOrder;
    public AnchorPane customerTable;
    public TableView<CustomerTm> tblCustomer;
    public AnchorPane itemThings;
    public AnchorPane itemContext;
    public TextField txtDescription;
    public TextField txtPackSize;
    public TextField txtQtyOnHand;
    public TextField txtUnitPrice;

    public TextField txtCustomerAddress;
    public TextField txtProvince;
    public TextField txtCity;
    public TextField txtCustomerName;
    public TextField txtPostalCode;
    public ComboBox<String> cmbCustomerId;
    public TextField txtCustomerTitle;
    public TableColumn colId;
    public TableColumn codTitle;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colPostal;
    public ComboBox<String> cmbItemCode;
    public TextField txtDiscount;
    public TextField txtQty;
    public TableView<CartTm> tblCart;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public TableColumn colDiscount;
    public TableColumn colQty;
    public TableColumn colTotal;
    public Label tblTotal;
    public Label lblOrderId;

    ////////////////////////////////////////////////////////////////////

    public AnchorPane orderConformContext;
    public AnchorPane editCostomerOrderContext;
   
   
   
   ///////////////////////////////////////////////////////
   
    public TableView<CartTm> tblManageCustomer;
    public TextField txtTotalManage;
    public TextField txtCustomerIdMansge;
    public TextField txtOrderDateManage;
    public TextField txtOrderTimeManage;
    public TableColumn colItemCodeManage;
    public TableColumn colDescriptionManage;
    public TableColumn colPackSizeManage;
    public TableColumn colUnitPriceManage;
    public TableColumn colDiscountManage;
    public TableColumn colQtyOnHandManage;
    public TableColumn<CartTm ,Integer> colQtyManage= new TableColumn<>("qty");
    public TableColumn colTableManage;
    public TextField txtDescriptionManage;
    public TextField txtPackSizeManage;
    public TextField txtUnitPriceManage;
    public TextField txtDiscountManage;
    public TextField txtQtyOnHandManage;

    public ComboBox cmbSearOrder;
    public ComboBox cmbItemCodeManage;
    public TextField txtQtyManage;


    int manageOrderClearRemove=-1;
    int cartSelectedRowRemove=-1;
    ObservableList<CartTm> obListItem= FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {

        colItemCodeManage.setCellValueFactory(new  PropertyValueFactory<>("code"));
        colDescriptionManage.setCellValueFactory(new  PropertyValueFactory<>("description"));
        colPackSizeManage.setCellValueFactory(new  PropertyValueFactory<>("packSize"));
        colUnitPriceManage.setCellValueFactory(new  PropertyValueFactory<>("unitPrice"));
        colDiscountManage.setCellValueFactory(new  PropertyValueFactory<>("discount"));
        colQtyOnHandManage.setCellValueFactory(new  PropertyValueFactory<>("qtyOnHand"));
        colQtyManage.setCellValueFactory(new  PropertyValueFactory<>("qty"));
        colTableManage.setCellValueFactory(new  PropertyValueFactory<>("total"));


        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new  PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new  PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new  PropertyValueFactory<>("qtyOnHand"));
        colDiscount.setCellValueFactory(new  PropertyValueFactory<>("discount"));
        colQty.setCellValueFactory(new  PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new  PropertyValueFactory<>("total"));
        loadDateAndTime();
        setOrderId();
        try {
            loadItemId();
            itemIdLoad();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            try {
                setCustomerData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {



            try {
                setItemData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });

        tblCart.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowRemove=(int)newValue;
        });

        colId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        codTitle.setCellValueFactory(new PropertyValueFactory<>("custTitle"));
        colName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("custAddress"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostal.setCellValueFactory(new PropertyValueFactory<>("postalCode"));


        //////////////////////////////////////////////////////////////////
        //ObservableList<String> manageList=FXCollections.observableArrayList();
        dataSetManage();


        cmbSearOrder.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                OrderRecord orderRecord = new OrderController().getOrderRecord((String) newValue);
                setOrderRecorde(orderRecord);
                ArrayList<OrderDetails> orderDetailRecord = new OrderController().getOrderDetailRecord((String) newValue);
                setData( orderDetailRecord);

                //System.out.println(orderRecord);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

   // ObservableList<OrderDetails> obListData= FXCollections.observableArrayList();

    private void setData(ArrayList<OrderDetails> orderDetailRecord) {

        if(orderDetailRecord!=null){
            obListItem.clear();
            for (OrderDetails temp:orderDetailRecord
                 ) {
                CartTm tm =new CartTm(
                        temp.getItemCode(),
                        temp.getDescription(),
                        temp.getPackSize(),
                        temp.getUnitPrice(),
                        temp.getQtyOnHand(),
                        temp.getDiscount(),
                        temp.getQty(),
                        temp.getTotal()
                );
               // System.out.println(tm.get);

                obListItem.add(tm);

               // tblManageCustomer.getItems().clear();
                tblManageCustomer.setItems(obListItem);
            }

        }
    }

    private void setOrderRecorde( OrderRecord orderRecord) {
        if (orderRecord==null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");

        }else{
            txtCustomerIdMansge.setText(orderRecord.getCustomerId());
            txtOrderDateManage.setText(orderRecord.getOrderDate());
            txtOrderTimeManage.setText(orderRecord.getTime());
            txtTotalManage.setText(String.valueOf(orderRecord.getTotal()));
        }
        

    }

    private void setOrderId() {
        try {
            lblOrderId.setText(new  OrderController().getOrderId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void itemIdLoad() throws SQLException, ClassNotFoundException {
        List<String> itemIds = new ItemController().getAllItems();
        cmbItemCode.getItems().addAll(itemIds);
    }

    private void setItemData(Object itemCode) throws SQLException, ClassNotFoundException {
        Item i1 = new ItemController().getItem((String) itemCode);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {

            txtDescription.setText(i1.getDescription());
            txtPackSize.setText(String.valueOf(i1.getPackSize()));
            txtUnitPrice.setText(String.valueOf(i1.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(i1.getQtyOnHand()));
            txtDiscount.setText(String.valueOf(i1.getDiscount()));
        }
    }


    private void setCustomerData(String customerId) throws SQLException, ClassNotFoundException {
        Customer c1=new CustomerController().getCustomer(customerId);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtCustomerTitle.setText(c1.getCustTitle());
            txtCustomerName.setText(c1.getCustName());
            txtCustomerAddress.setText(c1.getCustAddress());
            txtCity.setText(c1.getCity());
            txtProvince.setText(c1.getProvince());
            txtPostalCode.setText(c1.getPostalCode());
        }
    }

    private void loadItemId() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new CustomerController().getCustomerIds();
        cmbCustomerId.getItems().addAll(customerIds);
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void addNewCustomer(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) placeOrder.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/NewCustomerAdd.fxml"))));
    }

    ObservableList<CustomerTm> obList= FXCollections.observableArrayList();
    public void listTable(ActionEvent actionEvent) {
        tblCustomer.setVisible(true);
        itemContext.setVisible(false);



        String custTitle=txtCustomerTitle.getText();
        String custName=txtCustomerName.getText();
        String custAddress=txtCustomerAddress.getText();
        String city=txtCity.getText();
        String province=txtProvince.getText();
        String postalCode=txtPostalCode.getText();
        CustomerTm tm=new CustomerTm(
                cmbCustomerId.getValue(),
                custTitle,
                custName,
                custAddress,
                city,
                province,
                postalCode
        );
        obList.add(tm);
        tblCustomer.setItems(obList);

    }

    public void customerTable(ActionEvent actionEvent) {
        itemContext.setVisible(true);
        tblCustomer.setVisible(false);
    }

    public void addToCartOnAction(ActionEvent actionEvent) {

     //  if (){}
        String description = txtDescription.getText();
        double packSize= Double.parseDouble(txtPackSize.getText());
        double unitPrice=Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand= Integer.parseInt(txtQtyOnHand.getText());
        double discount =Double.parseDouble(txtDiscount.getText());
        int  qtyItem =Integer.parseInt(txtQty.getText());
        double total=(qtyItem*unitPrice)*((100.0-discount)/100);




        if (qtyOnHand<qtyItem){
            new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
            return;
        }

        CartTm tm =new CartTm(
                cmbItemCode.getValue(),
                description,
                packSize,
                unitPrice,
                qtyOnHand,
                discount,
                qtyItem,
                total
        );
        int rowNumber=isExists(tm);

        if (rowNumber==-1){//add new value
            obListItem.add(tm);

        }else {
            CartTm temp=obListItem.get(rowNumber);
            CartTm newTm = new CartTm(
                    temp.getCode(),
                    temp.getDescription(),
                    temp.getPackSize(),
                    temp.getUnitPrice(),
                    temp.getQtyOnHand(),
                    temp.getDiscount(),
                    temp.getQty()+qtyItem,
                    temp.getTotal()+temp.getTotal()
            );

           /* if (qtyOnHand<temp.getQty()){
                new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
                return;
            }*/


            obListItem.remove(rowNumber);
            obListItem.add(newTm);
        }


        tblCart.setItems(obListItem);
        calculateCost();

    }
    private int isExists(CartTm tm){
        for (int i = 0; i <obListItem.size() ; i++) {
            if (tm.getCode().equals(obListItem.get(i).getCode())){
                return i;
            }
        }
        return -1;

    }
    void calculateCost(){
        double  ttl=0;
        for (CartTm tm:obListItem
             ) {
            ttl+=tm.getTotal();

        }
        tblTotal.setText(ttl+"/=");
    }

    public void clearOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowRemove==-1){
            new Alert(Alert.AlertType.WARNING,"Please Select a row").show();
        }else{
            obListItem.remove(cartSelectedRowRemove);
            tblCart.refresh();
        }
    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ArrayList<ItemDetails> items=new ArrayList<>();
        double total=0;

        for (CartTm tempTm:obListItem) {
            total+=tempTm.getTotal();
            items.add(new ItemDetails(
                         tempTm.getCode(),
                            tempTm.getQty(),
                            tempTm.getDiscount(),
                            tempTm.getUnitPrice()));
        }
        Order order=new Order(
              lblOrderId.getText() ,
              cmbCustomerId.getValue(),
                lblDate.getText(),
                lblTime.getText(),
                total,
                items
        );
        if (new OrderController().placeOrder(order)){
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            setOrderId();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();

        }
    }

    public void editOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        orderConformContext.setVisible(false);
        editCostomerOrderContext.setVisible(true);
        dataSetManage();
    }

    public void conformOrderOnAction(ActionEvent actionEvent) {
        orderConformContext.setVisible(true);
        editCostomerOrderContext.setVisible(false);
    }

   ///////////////////////////////////////////////////////////////////////////

    public void edithOrderOnActionManager(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<ItemDetails> items=new ArrayList<>();
        double total=0;

        for (CartTm tempTm:obListItem) {
            total+=tempTm.getTotal();
            items.add(new ItemDetails(
                    tempTm.getCode(),
                    tempTm.getQty(),
                    tempTm.getDiscount(),
                    tempTm.getUnitPrice()));
        }
        Order order=new Order(
                (String) cmbSearOrder.getValue(),
                cmbCustomerId.getValue(),
                lblDate.getText(),
                lblTime.getText(),
                total,
                items
        );
        if (new OrderController().updateOrder(order)){
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            setOrderId();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();

        }




    }
    private void dataSetManage() throws SQLException, ClassNotFoundException {
        ObservableList<String> allOrderIdManage = new OrderController().getAllOrderIdManage();
        cmbSearOrder.setItems(allOrderIdManage);
    }
    public void toCartOnAction(ActionEvent actionEvent) {

    }

    public void logOutFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource= getClass().getResource("../view/DashboardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window=(Stage) placeOrder.getScene().getWindow();
        window.setScene(new Scene(load));


        /*Stage window = (Stage) placeOrder.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"))));*/
    }

    public void searchOrderOnAction(ActionEvent actionEvent) {


    }

    public void clearManageOnAction(ActionEvent actionEvent) {
        /*if (manageOrderClearRemove==-1){
            new Alert(Alert.AlertType.WARNING,"Please Select a row").show();
        }else{
            obListItem.remove(manageOrderClearRemove);
            tblManageCustomer.refresh();
        }*/
    }

    public void payManageOnAction(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.CONFIRMATION, "Payment Success").show();
    }

    public void payNowOnAction(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.WARNING, "Payment Success", ButtonType.CLOSE).show();

    }

    public void changeTableDataOnAction(InputMethodEvent inputMethodEvent) {
       // tblManageCustomer.setEditable(true);
    }


    public void tblDataChangeOnMouseClick(MouseEvent mouseEvent) {
        tblManageCustomer.setEditable(true);
        colQtyManage.setCellValueFactory(new  PropertyValueFactory<CartTm,Integer>("qty"));
        colQtyManage.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colQtyManage.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CartTm, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CartTm, Integer> event) {

                CartTm cart =event.getRowValue();

                if (cart.getQtyOnHand()<event.getNewValue()){
                    new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
                }else {
                    double tot=((event.getNewValue()*cart.getUnitPrice())*((100.0-cart.getDiscount())/100));
                    cart.setQty(event.getNewValue());
                    cart.setTotal(tot);
                    System.out.println(tot);
                }
            }
        });
       //CartTm selectedCartTm=tblManageCustomer.getSelectionModel().getSelectedItem();

       // colTableManage.
    }

}
