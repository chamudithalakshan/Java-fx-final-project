package lk.ijse.techbeats.model;

import java.util.ArrayList;
import java.util.Date;


public class PlaceOrder {
    private String orderId;
    private double total_price;
    private Date date;
    private String custId;
    private int qty;
    private ArrayList<ItemDetails> po;

    public PlaceOrder() {

    }

    public PlaceOrder(String orderId, double total_price, Date date, String custId, int qty, ArrayList<ItemDetails> objects) {
        this.orderId = orderId;
        this.total_price = total_price;
        this.date = date;
        this.custId = custId;
        this.qty = qty;
        this.po = objects;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public ArrayList<ItemDetails> getObjects() {
        return po;
    }

    public void setObjects(ArrayList<ItemDetails> objects) {
        this.po = objects;
    }
}
