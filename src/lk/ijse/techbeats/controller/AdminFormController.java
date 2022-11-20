package lk.ijse.techbeats.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminFormController implements Initializable {

    public JFXButton btnOverView;
    public BorderPane borderPane;
    public AnchorPane getScene;

    public void setMouseOnClick(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            borderPane.setCenter(FXMLLoader.load(getClass().getResource("../view/OverView.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSystemReport(ActionEvent actionEvent) throws IOException {
        borderPane.setCenter(FXMLLoader.load(getClass().getResource("../view/admin/AdminReportForm.fxml")));
    }

    public void btnOrdersOnAction(ActionEvent actionEvent) throws IOException {
        borderPane.setCenter(FXMLLoader.load(getClass().getResource("../view/admin/OrderForm.fxml")));
    }

    public void btnRepaireOnAction(ActionEvent actionEvent) throws IOException {
        borderPane.setCenter(FXMLLoader.load(getClass().getResource("../view/admin/RepaireForm.fxml")));
    }

    public void btnGuaranteeOnAction(ActionEvent actionEvent) throws IOException {
        borderPane.setCenter(FXMLLoader.load(getClass().getResource("../view/admin/GuaranteeForm.fxml")));
    }

    public void btnUsersOnAction(ActionEvent actionEvent) throws IOException {
        borderPane.setCenter(FXMLLoader.load(getClass().getResource("../view/admin/UserForm.fxml")));
    }

    public void btnPermissionOnAction(ActionEvent actionEvent) throws IOException {
        borderPane.setCenter(FXMLLoader.load(getClass().getResource("../view/admin/PermissionForm.fxml")));
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        borderPane.setCenter(FXMLLoader.load(getClass().getResource("../view/admin/CustomerForm.fxml")));
    }

    public void btnSignOutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) getScene.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/techbeats/view/LoginForm.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }

    public void overViewOnAction(ActionEvent actionEvent) throws IOException {
        borderPane.setCenter(FXMLLoader.load(getClass().getResource("../view/OverView.fxml")));
    }
}
