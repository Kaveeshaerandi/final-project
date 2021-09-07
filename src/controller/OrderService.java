package controller;

import javafx.collections.ObservableList;
import model.ItemDetails;
import model.Order;
import model.OrderDetails;
import model.OrderRecord;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderService {
    public boolean modifyOrder();
    public ObservableList<String> getAllOrderIdManage() throws SQLException, ClassNotFoundException;
    public OrderRecord getOrderRecord(String orderId) throws SQLException, ClassNotFoundException;
    public ArrayList<OrderDetails>  getOrderDetailRecord(String orderId) throws SQLException, ClassNotFoundException;
}
