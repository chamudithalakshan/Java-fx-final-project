package lk.ijse.techbeats.model;


import javafx.scene.control.Button;

public class ItemTm {
    private String code;
    private String description;
    private String brand;
    private Double unitPrice;
    private int qtyOnHand;
    private Button btnDelete;

    public ItemTm() {
    }

    public ItemTm(String code, String description, String brand, Double unitPrice, Integer qtyOnHand, Button btnDelete) {
        this.code = code;
        this.description = description;
        this.brand = brand;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
        this.btnDelete = btnDelete;
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }
}
