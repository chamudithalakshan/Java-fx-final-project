package lk.ijse.techbeats.controller.admin;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lk.ijse.techbeats.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCustomerFormController {
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
    void btnAddCustomer(ActionEvent event) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("insert into customer values (?,?,?,?,?)");
            pst.setObject(1,txtCustomerId.getText());
            pst.setObject(2,txtCustomerName.getText());
            pst.setObject(3,txtCustomerEmail.getText());
            pst.setObject(4,txtCustomerContact.getText());
            pst.setObject(5,txtCustomerAddress.getText());
            int i = pst.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
