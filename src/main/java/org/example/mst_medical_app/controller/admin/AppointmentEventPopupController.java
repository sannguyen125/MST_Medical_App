package org.example.mst_medical_app.controller.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.mst_medical_app.model.Appointment;

public class AppointmentEventPopupController {
    @FXML private Label titleLbl, doctorLbl, patientLbl, datetimeLbl, statusLbl;

    public void bind(Appointment a) {
        titleLbl.setText("Title: " + a.getTitle());
        doctorLbl.setText("Doctor: " + a.getDoctor());
        patientLbl.setText("Patient: " + a.getPatient());
        datetimeLbl.setText("Time: " + a.getDate() + " " + a.getStart() + " - " + a.getEnd());
        statusLbl.setText("Status: " + a.getStatus());
    }

    @FXML private void close() {
        ((Stage) titleLbl.getScene().getWindow()).close();
    }
}
