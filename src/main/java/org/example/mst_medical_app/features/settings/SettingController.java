package org.example.mst_medical_app.features.settings;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.example.mst_medical_app.controller.MainLayoutController;
import org.example.mst_medical_app.core.security.AuthManager;
import org.example.mst_medical_app.model.Patient;
import org.example.mst_medical_app.model.UserModel;
import org.example.mst_medical_app.model.UserRepository;

import java.io.File;

public class SettingController{

    private MainLayoutController mainLayoutController;

    @FXML private TextField nameField, emailField, phoneField;
    @FXML private PasswordField passwordField;
    @FXML private ImageView avatarImage;
    @FXML private Button saveButton, changeAvatarBtn;
    @FXML private Label messageLabel;

    private UserModel currentUser;


    public void setMainLayoutController(MainLayoutController controller) {
        this.mainLayoutController = controller;
    }

    @FXML
    public void initialize() {
        currentUser = AuthManager.getCurUser();
        if (currentUser != null) {
            nameField.setText(currentUser.getFullName());
            emailField.setText(currentUser.getEmail());
            phoneField.setText(currentUser.getPhone());
        }

        changeAvatarBtn.setOnAction(e -> handleAvatarChange());
        saveButton.setOnAction(e -> saveProfile());
    }

    private void handleAvatarChange() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            avatarImage.setImage(new Image(file.toURI().toString()));
            // TODO: Lưu đường dẫn ảnh vào DB
        }
    }

    private void saveProfile() {
        if (currentUser == null) return;

        currentUser.setFullName(nameField.getText());
        currentUser.setEmail(emailField.getText());
        currentUser.setPhone(phoneField.getText());
        boolean success = UserRepository.updateProfile(currentUser);
        if(success) {
            messageLabel.setText("Profile update successfully!");
            messageLabel.setStyle("-fx-text-fill: green;");
        } else {
            messageLabel.setText("Failed to update profile!");
            messageLabel.setStyle("-fx-text-fill: red;");
        }

    }
}
