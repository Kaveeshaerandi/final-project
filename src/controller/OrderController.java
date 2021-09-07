package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController  implements OrderService{

    public  String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().
                prepareStatement("SELECT * FROM `Order` ORDER BY orderId DESC LIMIT 1").
                executeQuery();
        if (rst.next()){
            int tempId =Integer.parseInt( rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<9){
                return "O-00"+tempId;
            }else if(tempId<99){
                return "O-0"+tempId;
            }else {
                return "O-"+tempId;
            }

        }else {
            return "O-001";
        }
    }

    public OrderController() throws SQLException, ClassNotFoundException {
    }
    public  boolean updateOrder(Order order){
        try {
            PreparedStatement stm = DbConnection.getInstance().getConnection().
                    prepareStatement("DELETE FROM `Order Detail` od WHERE od.orderId=? ");
            stm.setObject(1, order.getOrderId());
            if (stm.executeUpdate()>0){
                return saveOrderDetail(order.getOrderId(),order.getItems());

            }else {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public  boolean placeOrder(Order order)  {
        try {
            PreparedStatement stm =
                    DbConnection.getInstance().getConnection().
                    prepareStatement("INSERT INTO `Order` VALUES(?,?,?,?,?)");

            stm.setObject(1, order.getOrderId());
            stm.setObject(2, order.getCustomerId());
            stm.setObject(3, order.getOrderDate());
            stm.setObject(4, order.getTime());
            stm.setObject(5, order.getTotal());


            if (stm.executeUpdate()>0){

                return saveOrderDetail(order.getOrderId(),order.getItems());

            }else {
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  false;

    }
    private boolean updateItems(ItemDetails items) throws SQLException, ClassNotFoundException {

            PreparedStatement statement =
                    DbConnection.getInstance().getConnection().prepareStatement("SELECT  i.qtyOnHand FROM Item i WHERE i.itemCode=? ");
            statement.setObject(1,items.getItemCode());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int qtyOnHand = Integer.parseInt(resultSet.getString(1));
                PreparedStatement stm =
                        DbConnection.getInstance().getConnection().prepareStatement("UPDATE  Item i SET i.qtyOnHand=? WHERE i.itemCode=? ");
                stm.setObject(1,(qtyOnHand -items.getQty()));
                stm.setObject(2,items.getItemCode());
                if (stm.executeUpdate()>0){
                    return true;
                }else {
                    return false;
                }
            }

        return false;
    }

    private boolean saveOrderDetail(String orderId, ArrayList<ItemDetails> items) throws SQLException, ClassNotFoundException {
        for (ItemDetails temp:items) {

            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO `Order Detail` VALUES (?,?,?,?,?)");
            stm.setObject(1,orderId);
            stm.setObject(2,temp.getItemCode());
            stm.setObject(3,temp.getQty());
            stm.setObject(4,temp.getDiscount());
            stm.setObject(5,temp.getUnitPrice());

            System.out.println(orderId + " " + temp.getItemCode() + " " + temp.getQty() + " " + temp.getDiscount() + " " + temp.getUnitPrice());

            if(stm.executeUpdate()>0){
               if(updateItems(temp)){
                    return true;
               }else {
                   return false;
               }
            }else {
                return  false;
            }
        }
        return false;
    }

    private  boolean updateQty(String itemCode ,int qty) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection()
                .prepareStatement
                        ("UPDATE ITEM SET qtyOnHand=(qtyOnHand-" + qty
                                + ") WHERE itemCode='" + itemCode + "'");
        return stm.executeUpdate()>0;

    }

    /////////////////////////////////////////////////////

    @Override
    public boolean modifyOrder() {
        return false;
    }

    @Override
    public ObservableList<String> getAllOrderIdManage() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT orderId FROM `Order`");
        ResultSet resultSet = stm.executeQuery();

        ObservableList<String> comboList= FXCollections.observableArrayList();
        while (resultSet.next()){
            comboList.add(resultSet.getString(1));
        }

        return comboList;
    }

    @Override
    public OrderRecord getOrderRecord(String orderId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Order` WHERE orderId=? ");
        stm.setObject(1,orderId);
        ResultSet resultSet = stm.executeQuery();

        while (resultSet.next()){
            return new OrderRecord(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    Double.parseDouble(resultSet.getString(5))
            );

        }
        return null;
    }

    @Override
    public ArrayList<OrderDetails> getOrderDetailRecord(String orderId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT i.itemCode,i.description,i.packSize,i.unitPrice,i.discount,i.qtyOnHand,od.orderQty FROM Item i, `Order Detail` od WHERE i.itemCode = od.itemCode AND  od.orderId=? ");
        stm.setObject(1,orderId);
        ResultSet resultSet = stm.executeQuery();
       // System.out.println(resultSet);
        ArrayList<OrderDetails> itemArray=new ArrayList<>();
        while (resultSet.next()) {
           // System.out.println(resultSet.getString(1));
            OrderDetails newItem=new OrderDetails();
            newItem.setItemCode(resultSet.getString(1));
            newItem.setDescription(resultSet.getString(2));
            newItem.setPackSize(Double.parseDouble(resultSet.getString(3)));
            newItem.setUnitPrice(Double.parseDouble(resultSet.getString(4)));
            newItem.setDiscount(Double.parseDouble(resultSet.getString(5)));
            newItem.setQtyOnHand(Integer.parseInt(resultSet.getString(6)));
            newItem.setQty(Integer.parseInt(resultSet.getString(7)));
            Double total=(newItem.getQty()*newItem.getUnitPrice())*((100.0-newItem.getDiscount())/100);

            newItem.setTotal(total);
            itemArray.add(newItem);

        }
        System.out.println(itemArray);
        return itemArray;
    }

    public ArrayList<Income> getDailyIncome(String date) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().
                getConnection().
                prepareStatement(" SELECT i.itemCode,i.description,i.qtyOnHand,i.unitPrice,i.discount,od.orderQty FROM Item i,`Order` o, `Order Detail` od WHERE o.orderId=od.orderId AND i.itemCode = od.itemCode AND  o.orderDate=? GROUP BY i.itemCode ;");
        stm.setObject(1,date);
        ResultSet resultSet = stm.executeQuery();
        ArrayList<Income> incomeArray=new ArrayList<>();
        while (resultSet.next()) {
            Income newIncome=new Income();
            newIncome.setItemCode(resultSet.getString(1));
            newIncome.setDescription(resultSet.getString(2));
            newIncome.setQtyOnHand(Integer.parseInt(resultSet.getString(3)));
            newIncome.setSellingQty(Integer.parseInt(resultSet.getString(6)));
            newIncome.setDailyIncome((Integer.parseInt(resultSet.getString(6))*(Double.parseDouble(resultSet.getString(4)))) *((100-Double.parseDouble(resultSet.getString(5)))/100));

            incomeArray.add(newIncome);
        }

        System.out.println(incomeArray);
        return incomeArray;
    }


   /* @Override
    public OrderRecord getAllOrderManager(String orderId) throws SQLException, ClassNotFoundException {

        return null;
    }*/
}



