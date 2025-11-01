package org.example.mst_medical_app.core.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;

import java.io.IOException;
import java.net.URL;

public class SceneManager {

    private static Stage mainStage;

    public static void setMainStage(Stage stage) {
        mainStage = stage;
        mainStage.setMaximized(true);
    }

    public static void switchScene(String fxmlPath, String title) {
        if (mainStage == null) {
            System.err.println("Lỗi SceneManager: mainStage chưa được thiết lập.");
            return;
        }

        try {
            URL fxmlUrl = SceneManager.class.getResource(fxmlPath);
            if (fxmlUrl == null)
                throw new IOException("Không thể tìm thấy file FXML: " + fxmlPath);

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Scene scene = new Scene(loader.load());

            mainStage.setScene(scene);
            mainStage.setTitle(title);
            setFullScreenBounds();
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public static void switchScene(String fxmlPath, String title, Object controller) {
//        if (mainStage == null) {
//            System.err.println("Lỗi SceneManager: mainStage chưa được thiết lập.");
//            return;
//        }
//
//        try {
//            URL fxmlUrl = SceneManager.class.getResource(fxmlPath);
//            if (fxmlUrl == null)
//                throw new IOException("Không thể tìm thấy file FXML: " + fxmlPath);
//
//            FXMLLoader loader = new FXMLLoader(fxmlUrl);
//            loader.setController(controller);
//
//            Scene scene = new Scene(loader.load());
//
//            mainStage.setScene(scene);
//            mainStage.setTitle(title);
//            setFullScreenBounds();
//            mainStage.show();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private static void setFullScreenBounds() {
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        mainStage.setX(bounds.getMinX());
        mainStage.setY(bounds.getMinY());
        mainStage.setWidth(bounds.getWidth());
        mainStage.setHeight(bounds.getHeight());
    }
}
