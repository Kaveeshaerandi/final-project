package controller;

import model.Customer;
import model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerService {
    public boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException;
    public boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String custId) throws SQLException, ClassNotFoundException;
    public ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException;


}
