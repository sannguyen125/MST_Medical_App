package org.example.mst_medical_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.example.mst_medical_app.core.utils.SceneManager;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            SceneManager.setMainStage(stage);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setTitle("Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
