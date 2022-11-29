package lk.ijse.techbeats.controller.admin;

import com.sun.istack.internal.NotNull;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
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
import java.util.regex.Pattern;

public class CustomerFormController implements Initializable {
    public TableView<CustomerTm> tblCustomer;
    public Pane pane;
    Pattern cId = Pattern.compile("^(C-)[0-9]{3,4}$");
    Pattern nameOraddress = Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$");
    Pattern contact = Pattern.compile("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$");
    ObservableList<CustomerTm> obs = FXCollections.observableArrayList();
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
        tblCustomer.setEditable(true);
        custId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        custId.setCellFactory(TextFieldTableCell.forTableColumn());

        custId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CustomerTm, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CustomerTm, String> event) {
                if (!cId.matcher(event.getNewValue()).matches()) {

                } else {
                    updateCid(event.getOldValue(), event.getNewValue());
                }
            }
        });

        custName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        custName.setCellFactory(TextFieldTableCell.forTableColumn());

        custName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CustomerTm, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CustomerTm, String> event) {
                if (!nameOraddress.matcher(String.valueOf(event.getNewValue())).matches()) {

                } else {
                    updateCustName(event.getOldValue(), event.getNewValue(), event.getRowValue());
                }
            }
        });

        custAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        custAddress.setCellFactory(TextFieldTableCell.forTableColumn());

        custAddress.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CustomerTm, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CustomerTm, String> event) {
                if (!nameOraddress.matcher(String.valueOf(event.getNewValue())).matches()) {

                } else {
                    updateAddress(event.getOldValue(), event.getNewValue(), event.getRowValue());
                }
            }
        });

//        custAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
//        custAddress.setCellFactory(TextFieldTableCell.forTableColumn());
//
//        custAddress.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CustomerTm, String>>() {
//            @Override
//            public void handle(TableColumn.CellEditEvent<CustomerTm, String> event) {
//                if (!nameOraddress.matcher(String.valueOf(event.getNewValue())).matches()) {
//
//                } else {
//                    updateAddress(event.getOldValue(), event.getNewValue(), event.getRowValue());
//                }
//            }
//        });

        contactNmb.setCellValueFactory(new PropertyValueFactory<>("customerContact"));
        contactNmb.setCellFactory(TextFieldTableCell.forTableColumn());

        contactNmb.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CustomerTm, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CustomerTm, String> event) {

                updateContact(event.getOldValue(), event.getNewValue(), event.getRowValue());

            }
        });

//        custId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
//        custName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
//        custAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
//        contactNmb.setCellValueFactory(new PropertyValueFactory<>("customerContact"));
        deleteBtn.setCellValueFactory(new PropertyValueFactory<>("deleteBtn"));

        tblCustomer.setItems(loadCustomer(obs));
    }

    private void updateContact(String oldValue, String newValue, CustomerTm rowValue) {
        try {
            DbConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE customer SET mobile_num = '" + newValue + "'" + "WHERE c_id = '" + rowValue.getCustomerId() + "'").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void updateAddress(String oldValue, String newValue, CustomerTm rowValue) {
        try {
            DbConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE customer SET address = '" + newValue + "'" + "WHERE c_id = '" + rowValue.getCustomerId() + "'").executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void updateCustName(String oldValue, String newValue, @NotNull CustomerTm rowValue) {
        try {
            DbConnection.getInstance()
                    .getConnection().prepareStatement(
                            "UPDATE customer SET name = '" + newValue + "'" + "WHERE c_id = '" + rowValue.getCustomerId() + "'").executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void updateCid(String oldValue, String newValue) {
        try {
            DbConnection.getInstance()
                    .getConnection().prepareStatement(
                            "UPDATE customer SET c_id = '" + newValue + "'" + "WHERE c_id = '" + oldValue + "'").executeUpdate();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private ObservableList<CustomerTm> loadCustomer(ObservableList<CustomerTm> obs) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from customer");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
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

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteCustomer(CustomerTm selectedItem) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("delete from customer where c_id=?");
            pst.setObject(1, selectedItem.getCustomerId());
            int i = pst.executeUpdate();
            tblCustomer.getItems().clear();
            obs.removeAll();
            loadCustomer(obs);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
