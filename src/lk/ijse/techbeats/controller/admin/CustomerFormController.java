package lk.ijse.techbeats.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CustomerFormController {
    public TableView<CustomerTm> tblCustomer;
    @FXML
    private TableColumn custId;

    @FXML
    private TableColumn custName;

    @FXML
    private TableColumn custAddress;

    @FXML
    private TableColumn contactNmb;

    @FXML
    private TableColumn deleteBtn;

    @FXML
    void btnAddCustomer(ActionEvent event) {

    }
}
