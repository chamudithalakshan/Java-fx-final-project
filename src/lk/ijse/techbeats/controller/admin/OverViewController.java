package lk.ijse.techbeats.controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.techbeats.tm.OverviewTm;
import lk.ijse.techbeats.tm.RepaireTm;
import lk.ijse.techbeats.util.GetItems;

import java.net.URL;
import java.util.ResourceBundle;

public class OverViewController implements Initializable {
    public TableColumn cid;
    public TableColumn orderId;
    public TableColumn qty;
    public TableColumn orderDate;
    public Label lblTotalOrder;
    public Label lblTotalRepaire;

    public TableView<OverviewTm> tblOverview;
    ObservableList<OverviewTm> obs= FXCollections.observableArrayList();
    ObservableList<RepaireTm> obs1= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cid.setCellValueFactory(new PropertyValueFactory<>("custId"));
        orderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        orderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        qty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        tblOverview.setItems(GetItems.loadOverViewData(obs));
        System.out.println(obs);
        lblTotalOrder.setText(String.valueOf(obs.size()));
      obs1=  GetItems.loadRepareData(obs1);

        lblTotalRepaire.setText(String.valueOf(obs1.size()));

    }
}
