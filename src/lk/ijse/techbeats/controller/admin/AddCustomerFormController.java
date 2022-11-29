package lk.ijse.techbeats.controller.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import lk.ijse.techbeats.db.DbConnection;
import lk.ijse.techbeats.util.Dialog;
import lk.ijse.techbeats.util.KeyRelease;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddCustomerFormController implements Initializable {
    public JFXButton addButton;
    public StackPane stack;
    LinkedHashMap<TextField, Pattern> allValidation = new LinkedHashMap<>();
    Pattern cId = Pattern.compile("^(C-)[0-9]{3,4}$");
    Pattern nameOraddress = Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$");
    //Pattern contact = Pattern.compile("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$");
    Pattern email = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    @FXML
    private JFXTextField txtCustomerId;
    @FXML
    private JFXTextField txtCustomerName;
    @FXML
    private JFXTextField txtCustomerAddress;
    @FXML
    private JFXTextField txtCustomerContact;
    @FXML
    private JFXTextField txtCustomerEmail;

    @FXML
    void btnAddCustomer() {
        addButton.setOnMouseClicked(event ->{
            try {
                Connection connection = DbConnection.getInstance().getConnection();
                PreparedStatement pst = connection.prepareStatement("insert into customer values (?,?,?,?,?)");
                pst.setObject(1, txtCustomerId.getText());
                pst.setObject(2, txtCustomerName.getText());
                pst.setObject(3, txtCustomerEmail.getText());
                pst.setObject(4, txtCustomerContact.getText());
                pst.setObject(5, txtCustomerAddress.getText());
                int i = pst.executeUpdate();
                if (i > 0) {
                    Dialog.showDialog("Customer Added Successfully", stack, "Done");
                } else {
                    Dialog.showDialog("Try Again", stack, "Ok");
                }

            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        });


    }

    private void validate_init() {
        allValidation.put(txtCustomerId, cId);
        allValidation.put(txtCustomerName, nameOraddress);
        allValidation.put(txtCustomerAddress, nameOraddress);
       // allValidation.put(txtCustomerContact, contact);
        allValidation.put(txtCustomerEmail, email);
        addButton.setDisable(true);
    }

    public void release(KeyEvent keyEvent) {
        KeyRelease.btnReleaseOnAction(keyEvent, allValidation, addButton);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validate_init();
        btnAddCustomer();
    }
}
