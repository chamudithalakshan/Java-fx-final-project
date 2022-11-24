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
import lk.ijse.techbeats.model.ItemTm;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
    public TableColumn btnDelete;
    @FXML
    private TableView<ItemTm> tblitem;

    @FXML
    private TableColumn itemCode;

    @FXML
    private TableColumn description;

    @FXML
    private TableColumn barand;

    @FXML
    private TableColumn unitPrice;

    @FXML
    private TableColumn qty;

    ObservableList<ItemTm> obs = FXCollections.observableArrayList();

    @FXML
    void btmAddItem(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/techbeats/view/inventoryManager/AddItemForm.fxml"));
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
        itemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        barand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        unitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        qty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        btnDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        tblitem.setItems(loadItem(obs));

    }

    private ObservableList<ItemTm> loadItem(ObservableList<ItemTm> obs) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from item");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Button btn = new Button("Delete");
                btn.setOnAction((e) -> {
                    ItemTm selectedItem = tblitem.getSelectionModel().getSelectedItem();

                    deleteItem(selectedItem);

                });
                obs.add(new ItemTm(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(4),
                                resultSet.getDouble(3),
                                resultSet.getInt(5),
                                btn
                        )
                );

            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return obs;
    }

    private void deleteItem(ItemTm selectedItem) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("delete from item where item_code=?");
            pst.setObject(1, selectedItem.getCode());
            int i = pst.executeUpdate();
            tblitem.getItems().clear();
            obs.removeAll();
            loadItem(obs);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
