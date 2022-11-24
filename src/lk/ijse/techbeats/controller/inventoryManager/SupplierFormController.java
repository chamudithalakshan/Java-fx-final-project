package lk.ijse.techbeats.controller.inventoryManager;

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
import lk.ijse.techbeats.model.SupplierTm;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {
    public TableView<SupplierTm> tblSupplier;
    public TableColumn deleteBtn;
    @FXML
    private TableColumn supplierId;

    @FXML
    private TableColumn itemCode;

    @FXML
    private TableColumn supplierAddress;

    @FXML
    private TableColumn contactnmb;

    @FXML
    private TableColumn name;

    ObservableList<SupplierTm> obs = FXCollections.observableArrayList();

    @FXML
    void btnAddSupplier(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/techbeats/view/inventoryManager/AddSupplierForm.fxml"));
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
        supplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        contactnmb.setCellValueFactory(new PropertyValueFactory<>("supplierContact"));
        supplierAddress.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));
        itemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        deleteBtn.setCellValueFactory(new PropertyValueFactory<>("deleteBtn"));
//        System.out.println("333");
        tblSupplier.setItems(loadSupplier(obs));
    }

    private ObservableList<SupplierTm> loadSupplier(ObservableList<SupplierTm> obs) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from supplier");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Button btn = new Button("Delete");
                btn.setOnAction(event -> {
                    SupplierTm selectedItem = tblSupplier.getSelectionModel().getSelectedItem();
                    System.out.println(selectedItem);
                    deleteSupplier(selectedItem);
                    System.out.println("222");
                });
                obs.add(new SupplierTm(
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

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteSupplier(SupplierTm selectedItem) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("delete from supplier where supplier_id=?");
            pst.setObject(1,selectedItem.getSupplierId());
            int i = pst.executeUpdate();
            tblSupplier.getItems().clear();
            obs.removeAll();
            loadSupplier(obs);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
