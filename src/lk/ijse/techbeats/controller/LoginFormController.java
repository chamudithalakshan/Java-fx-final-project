package lk.ijse.techbeats.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public AnchorPane pane;

    public void btnCancelOnActin(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void btnLogin(ActionEvent actionEvent) throws IOException {
        String adminU="admin";
        String adminp="admin123";

        String imu="inventory";
        String imp="im1234";

        String cashieru="cashier";
        String cashierp="cashier123";

        if (adminU.equals(txtUsername.getText()) && adminp.equals(txtPassword.getText())){
            Stage stage= (Stage) pane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/techbeats/view/AdminForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        }else if (imu.equals(txtUsername.getText()) && imp.equals(txtPassword.getText())){
            Stage stage= (Stage) pane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/techbeats/view/inventoryManager/InventoryManagerForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } else if (cashieru.equals(txtUsername.getText()) && cashierp.equals(txtPassword.getText())) {
            Stage stage= (Stage) pane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/techbeats/view/cashier/CashierForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        }

    }
}
