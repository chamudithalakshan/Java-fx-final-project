package lk.ijse.techbeats.controller.cashier;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.techbeats.db.DbConnection;
import lk.ijse.techbeats.model.CartTm;
import lk.ijse.techbeats.model.ItemDetails;
import lk.ijse.techbeats.model.ItemTm;
import lk.ijse.techbeats.model.PlaceOrder;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {
    public TableColumn deleteBtn;
    ObservableList<CartTm> obs = FXCollections.observableArrayList();
    @FXML
    private JFXComboBox<String> cmbSelectItem;
    @FXML
    private JFXComboBox<String> cmbSelectCustomer;
    @FXML
    private TextField lblAvailableQty;
    @FXML
    private TableView<CartTm> tblCart;
    @FXML
    private TableColumn itemCode;
    @FXML
    private TableColumn description;
    @FXML
    private TableColumn unitPrice;
    @FXML
    private TableColumn orderQty;
    @FXML
    private TableColumn  total;
    @FXML
    private Label lblPrice;
    @FXML
    private TextField Qty;
    @FXML
    private TextField txtDiscount;

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

        int qty = Integer.parseInt(Qty.getText());
        int lblTempqty = Integer.parseInt(lblAvailableQty.getText());
        int tempQty = Integer.parseInt(lblAvailableQty.getText()) - qty;
        double price = Double.parseDouble(lblPrice.getText());
        double total1 = qty * price;

        if (qty > lblTempqty) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
        } else {


            for (int i = 0; i < tblCart.getItems().size(); i++) {
                if (itemCode.getCellData(i).equals(cmbSelectItem.getValue())) {
                    int y = (int) orderQty.getCellData(i);
                    obs.get(i).setOrderQty(y + qty);
                    int tempQty1 = Integer.parseInt(lblAvailableQty.getText()) - qty;
                    lblAvailableQty.setText(String.valueOf(tempQty1));
                    double newPrice= Double.parseDouble(lblPrice.getText());
                    double newTo=qty*newPrice;
                    double cellData = (double) total.getCellData(i);
                    double newT=newTo+cellData;
                    obs.get(i).setTotal(newT);




                    tblCart.refresh();
                    return;
                }

            }

            Button btnDelete = new Button("Delete");
            btnDelete.setOnAction(event1 -> {
                CartTm selectedItem = tblCart.getSelectionModel().getSelectedItem();
                int i = obs.indexOf(selectedItem);
                CartTm cartTm = obs.get(i);
                int orderQty1 = cartTm.getOrderQty();

                boolean remove = obs.remove(selectedItem);
                if (remove) {
                    int x = Integer.parseInt(lblAvailableQty.getText());
                    lblAvailableQty.setText(String.valueOf(x + orderQty1));
                }
                tblCart.refresh();

            });
            ItemTm item = getItem(cmbSelectItem.getValue());


            lblAvailableQty.setText(String.valueOf(tempQty));
            Qty.clear();

            obs.add(new CartTm(cmbSelectItem.getValue(), item.getDescription(), item.getUnitPrice(), qty, total1, btnDelete));
            tblCart.refresh();
        }

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException,NullPointerException, ClassNotFoundException {
        ArrayList<ItemDetails> items = new ArrayList<>();
        double total=0;
        int totalQty=0;
        for (CartTm tm :
                obs) {
            items.add(new ItemDetails(
                    tm.getItemCode(),
                    tm.getDescription(),
                    "gtx",
                    tm.getUnitPrice(),
                    getItem(tm.getItemCode()).getQtyOnHand()-tm.getOrderQty(),
                    tm.getOrderQty()
            ));
//            System.out.println(getItem(tm.getItemCode()).getQtyOnHand()-tm.getOrderQty());
            total += tm.getTotal();
            totalQty += tm.getOrderQty();
        }

        PlaceOrder placeOrder = new PlaceOrder(
                getOrderId(),
                total,
                new Date(),
                cmbSelectCustomer.getValue(),
                totalQty,
                items

        );


        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        PreparedStatement pst = connection.prepareStatement("insert into `order` values (?,?,?,?,?)");
        pst.setObject(1,placeOrder.getOrderId());
        pst.setObject(2,placeOrder.getTotal_price());
        pst.setObject(3,placeOrder.getDate());
        pst.setObject(4,placeOrder.getCustId());
        pst.setObject(5,placeOrder.getQty());

        if (pst.executeUpdate()>0){
            if (orderDetailsInit(placeOrder)){
                connection.commit();
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.show();
            }else {
                connection.rollback();
            }
        }else {
            connection.rollback();
        }


    }

    private boolean orderDetailsInit(PlaceOrder placeOrder) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            for (int i = 0; i < placeOrder.getObjects().size(); i++) {
                PreparedStatement pst = connection.prepareStatement("insert into order_details values (?,?,?)");
                pst.setObject(1,placeOrder.getOrderId());
                pst.setObject(2,placeOrder.getObjects().get(i).getCode());
                pst.setObject(3,placeOrder.getObjects().get(i).getOrderedQty());

                if (pst.executeUpdate()>0 && updateItem(placeOrder.getObjects().get(i))){
                    return true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private boolean updateItem(ItemDetails itemDetails) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("update item set item_qty=? where item_code=?");
            System.out.println(itemDetails.getQtyOnHand()+""+itemDetails.getOrderedQty());
            pst.setObject(1,itemDetails.getQtyOnHand());
            pst.setObject(2,itemDetails.getCode());
            if (pst.executeUpdate()>0){
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadItemCmb();
        loadCustomerCmb();
        tableInit();
    }

    private void loadItemCmb() {
        try {
            ObservableList<String> cmb = FXCollections.observableArrayList();
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from item");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                cmb.add(resultSet.getString(1));
            }
            cmbSelectItem.setItems(cmb);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerCmb() {
        try {
            ObservableList<String> cmb = FXCollections.observableArrayList();
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from customer");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                cmb.add(resultSet.getString(1));
            }
            cmbSelectCustomer.setItems(cmb);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void cmbOnItemSelect(ActionEvent actionEvent) {
        ItemTm item = getItem(cmbSelectItem.getValue());

        lblAvailableQty.setText(String.valueOf(item.getQtyOnHand()));
        lblPrice.setText(String.valueOf(item.getUnitPrice()));


    }

    private ItemTm getItem(String item_code) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from item where item_code=?");
            pst.setObject(1, cmbSelectItem.getValue());
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                return new ItemTm(resultSet.getString(1), resultSet.getString(2), resultSet.getString(4), resultSet.getDouble(3), resultSet.getInt(5), null);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private void tableInit() {
        itemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        unitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        orderQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        deleteBtn.setCellValueFactory(new PropertyValueFactory<>("deleteBtn"));
        tblCart.setItems(obs);


    }

    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT id FROM `order` ORDER BY id DESC LIMIT 1"
                ).executeQuery();

        if (rst.next()) {

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId = tempId + 1;
            if (tempId < 9) {
                return "O-00" + tempId;
            } else if (tempId < 99) {
                return "O-0" + tempId;
            } else {
                return "O-" + tempId;
            }

        } else {
            return "O-001";
        }
    }
}
