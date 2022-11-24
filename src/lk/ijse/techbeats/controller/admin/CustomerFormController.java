package lk.ijse.techbeats.controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.techbeats.db.DbConnection;
import lk.ijse.techbeats.model.CustomerTm;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {
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

    ObservableList<CustomerTm> obs= FXCollections.observableArrayList();

    @FXML
    void btnAddCustomer(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/techbeats/view/admin/AddCustomerForm.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableInit();
    }

    private void tableInit() {
        custId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        custName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        custAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        contactNmb.setCellValueFactory(new PropertyValueFactory<>("customerContact"));
        deleteBtn.setCellValueFactory(new PropertyValueFactory<>("deleteBtn"));

       tblCustomer.setItems(loadCustomer(obs));
    }

    private ObservableList<CustomerTm> loadCustomer(ObservableList<CustomerTm> obs) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from customer");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()){
                Button btn = new Button("Delete");
                btn.setOnAction(event -> {
                    CustomerTm selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
                    System.out.println(selectedItem);
                    deleteCustomer(selectedItem);

                });
                obs.add(new CustomerTm(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                btn
                        )

                );
            }
            return obs;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void  deleteCustomer(CustomerTm selectedItem) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("delete from customer where c_id=?");
            pst.setObject(1,selectedItem.getCustomerId());
            int i = pst.executeUpdate();
            tblCustomer.getItems().clear();
            obs.removeAll();
            loadCustomer(obs);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
