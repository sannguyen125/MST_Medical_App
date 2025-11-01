package org.example.mst_medical_app.controller.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.mst_medical_app.core.utils.SceneManager;
import org.example.mst_medical_app.controller.MainLayoutController;

public class SidebarController {

    private MainLayoutController mainLayoutController;

    public void setMainLayoutController(MainLayoutController controller) {
        this.mainLayoutController = controller;
    }
    @FXML
    private Button overviewButton, doctorButton, patientButton, appointmentButton, reportButton, settingButton, logoutButton;

    @FXML

    public void initialize() {
        overviewButton.setOnAction(e -> mainLayoutController.setContent("/org/example/mst_medical_app/admin/AdminDashboardView.fxml"));
        doctorButton.setOnAction(e -> mainLayoutController.setContent("/org/example/mst_medical_app/admin/Doctors_View.fxml"));
        patientButton.setOnAction(e -> mainLayoutController.setContent("/org/example/mst_medical_app/admin/Patients_View.fxml"));
        appointmentButton.setOnAction(e -> mainLayoutController.setContent("/org/example/mst_medical_app/admin/Appointments_View.fxml"));
        settingButton.setOnAction(e -> mainLayoutController.setContent("/org/example/mst_medical_app/admin/Setting_View.fxml"));
        reportButton.setOnAction(e -> mainLayoutController.setContent("/org/example/mst_medical_app/admin/Reports_View.fxml"));
    }
    @FXML
    private void handleLogout(ActionEvent event) {
        SceneManager.switchScene("/org/example/mst_medical_app/LoginView.fxml", "Login");
    }
}
