package lk.ijse.techbeats.controller.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import lk.ijse.techbeats.db.DbConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class AddRepareFormController implements Initializable {
    @FXML
    private StackPane stack;

    @FXML
    private JFXTextField txtReparerId;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtItemQty;

    @FXML
    private JFXTextField txtRepareCost;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    void btnAddRepaire(ActionEvent event) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("insert into repiar_item values (?,?,?,?,?,?,?)");
            pst.setObject(1,txtReparerId.getText());
            pst.setObject(2,txtItemName.getText());
            pst.setObject(3,txtDescription.getText());
            pst.setObject(4,txtItemQty.getText());
            pst.setObject(5,new Date());
            pst.setObject(6,txtRepareCost.getText());
            pst.setObject(7,cmbCustomerId.getValue());
            int i = pst.executeUpdate();
            if (i<0){
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void release(KeyEvent event) {

    }
    private void cmbLoad(){
        ObservableList<String> obs= FXCollections.observableArrayList();
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from customer");
            ResultSet rst = pst.executeQuery();
            while (rst.next()){
                obs.add(
                        rst.getString(1)
                );
                cmbCustomerId.setItems(obs);

            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbLoad();
    }
}
