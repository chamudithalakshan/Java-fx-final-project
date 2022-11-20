package lk.ijse.techbeats.controller.cashier;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CashierFormController implements Initializable {

    public BorderPane boarderPane;
    public AnchorPane getScene;

    public void btnOverView(ActionEvent actionEvent) {
    }

    public void btnManageOrder(ActionEvent actionEvent) throws IOException {
        boarderPane.setCenter(FXMLLoader.load(getClass().getResource("/lk/ijse/techbeats/view/cashier/ManageOrder.fxml")));
    }

    public void btnPlaceOrder(ActionEvent actionEvent) throws IOException {
        boarderPane.setCenter(FXMLLoader.load(getClass().getResource("/lk/ijse/techbeats/view/cashier/PlaceOrderForm.fxml")));
    }

    public void btnSignOut(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) getScene.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/techbeats/view/LoginForm.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            boarderPane.setCenter(FXMLLoader.load(getClass().getResource("/lk/ijse/techbeats/view/OverView.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
