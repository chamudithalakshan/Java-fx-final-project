package lk.ijse.techbeats.tm;

import javafx.scene.control.Button;

public class CustomerTm {
    private String customerId;
    private String customerName;
    private String email;
    private String customerContact;
    private String customerAddress;

    private Button deleteBtn;

    public Button getDeleteBtn() {
        return deleteBtn;
    }

    public void setDeleteBtn(Button deleteBtn) {
        this.deleteBtn = deleteBtn;
    }

    public CustomerTm(String customerId, String customerName, String email, String customerContact, String customerAddress, Button deleteBtn) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.customerContact = customerContact;
        this.customerAddress = customerAddress;
        this.deleteBtn = deleteBtn;
    }

    public CustomerTm() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Override
    public String toString() {
        return "CustomerTm{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", email='" + email + '\'' +
                ", customerContact='" + customerContact + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                '}';
    }
}
