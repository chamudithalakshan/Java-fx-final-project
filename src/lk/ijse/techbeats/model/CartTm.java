package lk.ijse.techbeats.model;

import javafx.scene.control.Button;

public class CartTm {
    private String itemCode;
    private String description;
    private double unitPrice;
    private int orderQty;
    private double total;
    private Button deleteBtn;

    public CartTm() {
    }

    public CartTm(String itemCode, String description, double unitPrice, int orderQty, double total, Button deleteBtn) {
        this.setItemCode(itemCode);
        this.setDescription(description);
        this.setUnitPrice(unitPrice);
        this.setOrderQty(orderQty);
        this.setTotal(total);
        this.setDeleteBtn(deleteBtn);
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getDeleteBtn() {
        return deleteBtn;
    }

    public void setDeleteBtn(Button deleteBtn) {
        this.deleteBtn = deleteBtn;
    }
}
