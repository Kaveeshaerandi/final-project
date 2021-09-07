package model;

public class OrderDetails  {
    private String itemCode;
    private String description;
    private double packSize;
    private double unitPrice;
    private double discount;
    private int qtyOnHand;
    private int qty;
    private double total;

    public OrderDetails() {
    }

    public OrderDetails(String itemCode, String description, double packSize, double unitPrice, double discount, int qtyOnHand, int qty, double total) {
        this.setItemCode(itemCode);
        this.setDescription(description);
        this.setPackSize(packSize);
        this.setUnitPrice(unitPrice);
        this.setDiscount(discount);
        this.setQtyOnHand(qtyOnHand);
        this.setQty(qty);
        this.setTotal(total);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPackSize() {
        return packSize;
    }

    public void setPackSize(double packSize) {
        this.packSize = packSize;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "itemCode='" + itemCode + '\'' +
                ", description='" + description + '\'' +
                ", packSize=" + packSize +
                ", unitPrice=" + unitPrice +
                ", discount=" + discount +
                ", qtyOnHand=" + qtyOnHand +
                ", qty=" + qty +
                ", total=" + total +
                '}';
    }
}

