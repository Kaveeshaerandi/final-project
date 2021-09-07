package controller;

import model.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemService {
    public boolean saveItem(Item i) throws SQLException, ClassNotFoundException;
    //public ArrayList<Item> getAllItems();
    public  boolean modifyItem(Item i) throws SQLException, ClassNotFoundException;
    public Item getItem(String itemCode) throws SQLException, ClassNotFoundException;
    public boolean removeItem(String itemCode) throws SQLException, ClassNotFoundException;
    public List<String> getAllItems() throws SQLException, ClassNotFoundException;

}
