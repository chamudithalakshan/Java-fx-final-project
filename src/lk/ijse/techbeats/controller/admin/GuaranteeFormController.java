package lk.ijse.techbeats.controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.util.converter.IntegerStringConverter;
import lk.ijse.techbeats.db.DbConnection;
import lk.ijse.techbeats.model.GuaranteeTm;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class GuaranteeFormController implements Initializable {

    public TableColumn GuaranteeId;
    public TableColumn ItemCode;
    public TableView<GuaranteeTm> tblGuarantee;
    public TableColumn guaranteeperiod;

    Pattern gId = Pattern.compile("^(G-)[0-9]{3,4}$");
    Pattern gPeriod = Pattern.compile("^[1-9][0-9]*?$");
    Pattern iCode = Pattern.compile("^(I-)[0-9]{3,4}$");

    public void btnAddGuarantee(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/techbeats/view/admin/AddGuaranteeForm.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            tableInit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void tableInit() throws SQLException, ClassNotFoundException {
        tblGuarantee.setEditable(true);
        GuaranteeId.setCellValueFactory(new PropertyValueFactory<>("guaranteeId"));
        GuaranteeId.setCellFactory(TextFieldTableCell.forTableColumn());

        GuaranteeId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<GuaranteeTm, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<GuaranteeTm, String> event) {
                if (!gId.matcher(event.getNewValue()).matches()) {

                } else {
                    updateGid(event.getOldValue(), event.getNewValue());
                }
            }
        });

        guaranteeperiod.setCellValueFactory(new PropertyValueFactory<>("guaranteePeriod"));
        guaranteeperiod.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        guaranteeperiod.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<GuaranteeTm, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<GuaranteeTm, Integer> event) {
                if (!gPeriod.matcher(String.valueOf(event.getNewValue())).matches()) {

                } else {
                    updateGperiod(event.getOldValue(), event.getNewValue(), event.getRowValue());
                }
            }
        });


        GuaranteeId.setCellValueFactory(new PropertyValueFactory<>("guaranteeId"));
        ItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        guaranteeperiod.setCellValueFactory(new PropertyValueFactory<>("guaranteePeriod"));
        ObservableList<GuaranteeTm> obs = FXCollections.observableArrayList();
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from guarantee");
        ResultSet resultSet = pst.executeQuery();
        while (resultSet.next()) {
            obs.add(new GuaranteeTm(
                    resultSet.getString(3),
                    resultSet.getString(1),
                    resultSet.getString(2)
            ));

        }
        tblGuarantee.setItems(obs);
    }

    private void updateGperiod(Integer oldValue, Integer newValue, GuaranteeTm rowValue) {
        try {
            DbConnection.getInstance()
                    .getConnection().prepareStatement(
                            "UPDATE guarantee SET guaranteePeriod = '" + newValue + "'" + "WHERE guaranteeId = '" + rowValue.getGuaranteeId() + "'").executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void updateGid(String oldValue, String newValue) {
        try {
            DbConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE guarantee SET guaranteeId = '" + newValue + "'" + "WHERE guaranteeId = '" + oldValue + "'").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
