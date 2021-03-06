package controller;

import db.DbConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements CustomerService {

    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Customer").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }


    @Override
    public boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Customer VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,c.getCustId());
        stm.setObject(2,c.getCustTitle());
        stm.setObject(3,c.getCustName());
        stm.setObject(4,c.getCustAddress());
        stm.setObject(5,c.getCity());
        stm.setObject(6,c.getProvince());
        stm.setObject(7,c.getPostalCode());
        //System.out.println("show :"+stm.executeUpdate());
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET  custTitle=?, custName=?,custAddress=?,city=?,province=?,postalCode=? WHERE custId=?");
        stm.setObject(1,c.getCustTitle());
        stm.setObject(2,c.getCustName());
        stm.setObject(3,c.getCustAddress());
        stm.setObject(4,c.getCity());
        stm.setObject(5,c.getProvince());
        stm.setObject(6,c.getPostalCode());
        stm.setObject(7,c.getCustId());
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteCustomer(String custId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE custId='"+custId+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer");
        ResultSet rst = stm.executeQuery();
        ArrayList<Customer> customers = new ArrayList<>();
        while (rst.next()) {
            customers.add(new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            ));
        }
        return customers;
    }

    public Customer getCustomer(String customerId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Customer WHERE custId=?");
        stm.setObject(1,customerId );

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)

            );

        } else {
            return null;
        }
    }
}
