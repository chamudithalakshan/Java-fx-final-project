package lk.ijse.techbeats.controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import lk.ijse.techbeats.db.DbConnection;
import lk.ijse.techbeats.model.OrderTm;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class OrderFormController implements Initializable {
    public TableColumn btnDelete;
    Pattern id = Pattern.compile("^(R-)[0-9]{3,4}$");
    Pattern type = Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$");
    Pattern cost1 = Pattern.compile("^[1-9][0-9]*([.][0-9]{1,2})?$");
    ObservableList<OrderTm> obs = FXCollections.observableArrayList();
    @FXML
    private TableView<OrderTm> tblOrder;
    @FXML
    private TableColumn orderId;
    @FXML
    private TableColumn custId;
    @FXML
    private TableColumn orderDate;
    @FXML
    private TableColumn cost;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tbl_init();


    }

    private void tbl_init() {
        tblOrder.setEditable(true);

        orderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        orderId.setCellFactory(TextFieldTableCell.forTableColumn());

        orderId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<OrderTm, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<OrderTm, String> event) {
                if (id.matcher((CharSequence) event.getNewValue()).matches()) {
                    updateOrderId(event.getOldValue(), event.getNewValue());
                }
            }
        });


        custId.setCellValueFactory(new PropertyValueFactory<>("custId"));


        orderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));


        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        btnDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
        tblOrder.setItems(loadOrderData(obs));
        System.out.println(obs);
    }

    private void updateOrderId(String oldValue, String newValue) {

    }

    public ObservableList<OrderTm> loadOrderData(ObservableList<OrderTm> obs) {
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from `order`");
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                Button btn = new Button("Delete");
                btn.setOnAction(event -> {
                    OrderTm selectedItem = tblOrder.getSelectionModel().getSelectedItem();
                    System.out.println(selectedItem);
                    deleteOrder(selectedItem);
                });
                obs.add(new OrderTm(
                        rst.getString(4),
                        rst.getString(1),
                        rst.getDate(3),
                        rst.getDouble(6),
                        btn
                ));
            }


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obs;


    }

    private void deleteOrder(OrderTm selectedItem) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("delete from `order` where id=?");
            pst.setObject(1, selectedItem.getOrderId());
            int i = pst.executeUpdate();
            tblOrder.getItems().clear();
            obs.removeAll();
            loadOrderData(obs);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
