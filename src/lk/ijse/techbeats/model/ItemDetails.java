package lk.ijse.techbeats.model;

public class ItemDetails {
    private String code;
    private String description;
    private String brand;
    private double unitPrice;
    private int qtyOnHand;
    private int orderedQty;

    public ItemDetails() {
    }

    public ItemDetails(String code, String description, String brand, Double unitPrice, int qtyOnHand, int orderedQty) {
        this.code = code;
        this.description = description;
        this.brand = brand;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
        this.orderedQty = orderedQty;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public int getOrderedQty() {
        return orderedQty;
    }

    public void setOrderedQty(int orderedQty) {
        this.orderedQty = orderedQty;
    }

    @Override
    public String toString() {
        return "ItemDetails{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyOnHand=" + qtyOnHand +
                ", orderedQty=" + orderedQty +
                '}';
    }
}
