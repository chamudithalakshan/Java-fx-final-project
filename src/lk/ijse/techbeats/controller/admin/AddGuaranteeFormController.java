package lk.ijse.techbeats.controller.admin;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import lk.ijse.techbeats.db.DbConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddGuaranteeFormController implements Initializable {
    public JFXComboBox cmbItem;
    public JFXTextField txtGuaranteeId;
    public JFXTextField txtGuaranteePeriod;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCmb();
    }

    private void loadCmb() {
        try {
            ObservableList<String> cmb = FXCollections.observableArrayList();
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from item");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                cmb.add(resultSet.getString(1));
            }
            cmbItem.setItems(cmb);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddGuarantee(ActionEvent actionEvent) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("insert into guarantee values (?,?,?)");
            pst.setObject(3, txtGuaranteeId.getText());
            pst.setObject(1 , txtGuaranteePeriod.getText());
            String selectedItem = (String) cmbItem.getSelectionModel().getSelectedItem();
            pst.setObject(2, selectedItem);
            int i = pst.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
