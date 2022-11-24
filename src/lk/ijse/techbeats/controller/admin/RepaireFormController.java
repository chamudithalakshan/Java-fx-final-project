package lk.ijse.techbeats.controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.techbeats.model.RepaireTm;
import lk.ijse.techbeats.util.GetItems;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RepaireFormController implements Initializable {
    public TableView<RepaireTm> repaireTable;
    @FXML
    private TableColumn repaireId;

    @FXML
    private TableColumn customerId;

    @FXML
    private TableColumn repaireType;

    @FXML
    private TableColumn cost;

    ObservableList<RepaireTm> obs = FXCollections.observableArrayList();

    Pattern id = Pattern.compile("^(R-)[0-9]{3,4}$");
    Pattern type = Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$");
    Pattern cost1 = Pattern.compile("^[1-9][0-9]*([.][0-9]{1,2})?$");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        repaireId.setCellValueFactory(new PropertyValueFactory<>("repairId"));
        customerId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        repaireType.setCellValueFactory(new PropertyValueFactory<>("repairType"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        repaireTable.setItems(GetItems.loadRepareData(obs));


    }
}
