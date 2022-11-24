package lk.ijse.techbeats.controller.inventoryManager;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.techbeats.db.DbConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddSupplierFormController implements Initializable {
    @FXML
    private JFXTextField txtSupplierId;

    @FXML
    private JFXTextField txtSupplierName;

    @FXML
    private JFXTextField txtContactNmb;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    void AddSupplier(ActionEvent event) throws IOException {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("insert into supplier values (?,?,?,?,?)");
            pst.setObject(1, txtSupplierId.getText());
            pst.setObject(2 , txtSupplierName.getText());
            pst.setObject(3 , txtContactNmb.getText());
            pst.setObject(4 , txtAddress.getText());
            String selectedItem = (String) cmbItemCode.getSelectionModel().getSelectedItem();
            pst.setObject(5, selectedItem);
            int i = pst.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

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
            cmbItemCode.setItems(cmb);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
