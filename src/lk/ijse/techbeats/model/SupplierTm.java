package lk.ijse.techbeats.model;

import javafx.scene.control.Button;

public class SupplierTm {
    String supplierId;
    String itemCode;
    String supplierAddress;
    String supplierContact;
    String name;
    Button deleteBtn;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierContact() {
        return supplierContact;
    }

    public void setSupplierContact(String supplierContact) {
        this.supplierContact = supplierContact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Button getDeleteBtn() {
        return deleteBtn;
    }

    public void setDeleteBtn(Button deleteBtn) {
        this.deleteBtn = deleteBtn;
    }

    public SupplierTm() {
    }

    public SupplierTm(String supplierId, String itemCode, String supplierAddress, String supplierContact, String name, Button deleteBtn) {
        this.supplierId = supplierId;
        this.itemCode = itemCode;
        this.supplierAddress = supplierAddress;
        this.supplierContact = supplierContact;
        this.name = name;
        this.deleteBtn = deleteBtn;
    }

    @Override
    public String toString() {
        return "SupplierTm{" +
                "supplierId='" + supplierId + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", supplierAddress='" + supplierAddress + '\'' +
                ", supplierContact='" + supplierContact + '\'' +
                ", name='" + name + '\'' +
                ", deleteBtn=" + deleteBtn +
                '}';
    }
}
