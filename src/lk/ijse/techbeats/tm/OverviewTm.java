package lk.ijse.techbeats.tm;

import java.sql.Date;

public class OverviewTm {

    private String custId;
    private String orderId;
    private Date orderDate;
    private int qty;

    public OverviewTm() {

    }

    public OverviewTm(String custId, String orderId, Date orderDate, int qty) {

        this.custId = custId;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OverviewTm{" +
                ", custId='" + custId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", qty='" + qty + '\'' +
                '}';
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
