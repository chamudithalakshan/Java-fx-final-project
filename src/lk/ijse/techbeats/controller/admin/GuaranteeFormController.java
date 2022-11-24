package lk.ijse.techbeats.controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.techbeats.db.DbConnection;
import lk.ijse.techbeats.model.GuaranteeTm;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GuaranteeFormController implements Initializable {

    public TableColumn GuaranteeId;
    public TableColumn ItemCode;
    public TableView<GuaranteeTm> tblGuarantee;
    public TableColumn guaranteeperiod;

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
}
