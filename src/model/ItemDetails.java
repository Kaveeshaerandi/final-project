package model;

public class ItemDetails {

    private String itemCode;
    private int qty;
    private double discount;
    private double unitPrice;

    public ItemDetails() {
    }

    public ItemDetails(String itemCode, int qty, double discount, double unitPrice) {
        this.setItemCode(itemCode);
        this.setQty(qty);
        this.setDiscount(discount);
        this.setUnitPrice(unitPrice);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "ItemDetails{" +
                "itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                ", discount=" + discount +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
