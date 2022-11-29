package lk.ijse.techbeats.controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.DoubleStringConverter;
import lk.ijse.techbeats.db.DbConnection;
import lk.ijse.techbeats.model.RepaireTm;
import lk.ijse.techbeats.util.GetItems;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RepaireFormController implements Initializable {
    public TableView<RepaireTm> repaireTable;
    ObservableList<RepaireTm> obs = FXCollections.observableArrayList();
    Pattern id = Pattern.compile("^(R-)[0-9]{3,4}$");
    Pattern type = Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$");
    Pattern cost1 = Pattern.compile("^[1-9][0-9]*([.][0-9]{1,2})?$");
    @FXML
    private TableColumn repaireId;
    @FXML
    private TableColumn customerId;
    @FXML
    private TableColumn repaireType;
    @FXML
    private TableColumn cost;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblInit();


    }

    private void tblInit() {
        repaireId.setCellValueFactory(new PropertyValueFactory<>("repairId"));
        customerId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        repaireType.setCellValueFactory(new PropertyValueFactory<>("repairType"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        repaireTable.setEditable(true);
        repaireId.setCellFactory(TextFieldTableCell.forTableColumn());

        repaireId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<RepaireTm, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<RepaireTm, String> event) {
                if (!id.matcher((CharSequence) event.getNewValue()).matches()) {
                    System.out.println("fuck");
                } else {
                    updateRepairId(event.getOldValue(), event.getNewValue());
                }
            }
        });

        repaireType.setCellFactory(TextFieldTableCell.forTableColumn());

        repaireType.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<RepaireTm, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<RepaireTm, String> event) {
                if (!type.matcher(event.getNewValue()).matches()) {

                } else {
                    updateRepairType(event.getOldValue(), event.getNewValue(), event.getRowValue());
                }
            }
        });

        cost.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        cost.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<RepaireTm, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<RepaireTm, Double> event) {
                if (!cost1.matcher(String.valueOf(event.getNewValue())).matches()) {

                } else {
                    updateRepairCost(event.getOldValue(), event.getNewValue(), event.getRowValue());
                }
            }
        });

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from repiar_item");
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                obs.add(
                        new RepaireTm(
                                rst.getString(1),
                                rst.getString(7),
                                rst.getString(3),
                                rst.getDouble(6)
                        )
                );
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        repaireTable.setItems(obs);
    }

    private void updateRepairCost(Double oldValue, Double newValue, RepaireTm rowValue) {
        try {
            DbConnection.getInstance().getConnection().
                    prepareStatement(
                            "UPDATE repiar_item SET repiar_cost = '" + newValue + "'" + "WHERE repiar_item_id = '" + rowValue.getRepairId() + "'").executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void updateRepairType(String oldValue, String newValue, RepaireTm rowValue) {
        try {
            DbConnection.getInstance().getConnection().
                    prepareStatement(
                            "UPDATE repiar_item SET repiar_item_description = '" + newValue + "'" + "WHERE repiar_item_id = '" + rowValue.getRepairId() + "'").executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void updateRepairId(String oldValue, String newValue) {
        try {
            DbConnection.getInstance().
                    getConnection().prepareStatement(
                            "UPDATE repiar_item SET repiar_item_id = '" + newValue + "'" + "WHERE repiar_item_id = '" + oldValue + "'").executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void btnAddRepare(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/techbeats/view/admin/AddRepaireForm.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
}
