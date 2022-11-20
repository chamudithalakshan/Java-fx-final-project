package lk.ijse.techbeats.tm;


import javafx.scene.control.Button;

import java.sql.Date;

public class OrderTm {
    private String custId;
    private String orderId;
    private Date orderDate;
    private double cost;

    private Button btnDelete;

    public OrderTm(String custId, String orderId, Date orderDate, double cost, Button btnDelete) {
        this.custId = custId;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.cost = cost;
        this.btnDelete = btnDelete;
    }

    public OrderTm() {
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "OrderTm{" +
                "custId='" + custId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", cost=" + cost +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
