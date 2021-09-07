package model;

public class Income {
    private String itemCode;
    private String description;
    private int qtyOnHand;
    private int sellingQty;
    private double dailyIncome;

    public Income() {
    }

    public Income(String itemCode, String description, int qtyOnHand, int sellingQty, double dailyIncome) {
        this.setItemCode(itemCode);
        this.setDescription(description);
        this.setQtyOnHand(qtyOnHand);
        this.setSellingQty(sellingQty);
        this.setDailyIncome(dailyIncome);
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

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public int getSellingQty() {
        return sellingQty;
    }

    public void setSellingQty(int sellingQty) {
        this.sellingQty = sellingQty;
    }

    public double getDailyIncome() {
        return dailyIncome;
    }

    public void setDailyIncome(double dailyIncome) {
        this.dailyIncome = dailyIncome;
    }

    @Override
    public String toString() {
        return "Income{" +
                "itemCode='" + itemCode + '\'' +
                ", description='" + description + '\'' +
                ", qtyOnHand=" + qtyOnHand +
                ", sellingQty=" + sellingQty +
                ", dailyIncome=" + dailyIncome +
                '}';
    }
}
