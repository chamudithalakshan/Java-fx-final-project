package lk.ijse.techbeats.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class UserFormController {

    public TableColumn userId;
    public TableColumn userName;
    public TableColumn address;
    public TableColumn contactNumber;
    public TableColumn DeleteBtn;

    public void btnAddUser(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/techbeats/view/admin/AddUserForm.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
}
