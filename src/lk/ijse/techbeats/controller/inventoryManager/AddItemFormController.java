package lk.ijse.techbeats.controller.inventoryManager;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import lk.ijse.techbeats.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddItemFormController {

    public JFXTextField txtItemId;
    public JFXTextField txtItemDescription;
    public JFXTextField txtItemBrand;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;

    public void btnItemAdd(ActionEvent actionEvent) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("insert into item values (?,?,?,?,?)");
            pst.setObject(1,txtItemId.getText());
            pst.setObject(2,txtItemDescription.getText());
            pst.setObject(3,txtUnitPrice.getText());
            pst.setObject(4,txtItemBrand.getText());
            pst.setObject(5,txtQtyOnHand.getText());

            pst.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
