package lk.ijse.techbeats.controller;

import com.jfoenix.controls.JFXProgressBar;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreenController implements Initializable {


    public JFXProgressBar bar;

    public void initialize(URL location, ResourceBundle resources) {
        start();

    }

    private void start() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (bar.getProgress() <= 1) {
                    Thread.sleep(5);
                    bar.setProgress(bar.getProgress() + 0.001);
                }
                return null;
            }
        };
        Thread thread = new Thread(task);
        // This method allows us to handle any Exceptions thrown by the task
        task.setOnFailed(wse -> {
            wse.getSource().getException().printStackTrace();
        });

        // If the task completed successfully, perform other updates here
        task.setOnSucceeded(wse -> {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
                Scene scene = new Scene(parent);
                Stage stage1 = new Stage();
                stage1.initStyle(StageStyle.UNDECORATED);
                stage1.setScene(scene);
                stage1.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage) bar.getScene().getWindow();
            stage.close();
            thread.stop();
        });
        thread.start();
    }


}
