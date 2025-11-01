package org.example.mst_medical_app.controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AppointmentsController {

    @FXML private TextField searchField;
    @FXML private Button addAppointmentBtn;
    @FXML private TableView<Appointment> appointmentTable;
    @FXML private TableColumn<Appointment, String> timeColumn;
    @FXML private TableColumn<Appointment, String> doctorColumn;
    @FXML private TableColumn<Appointment, String> patientColumn;
    @FXML private TableColumn<Appointment, String> roomColumn;
    @FXML private TableColumn<Appointment, String> statusColumn;

    @FXML
    public void initialize() {
        // dữ liệu demo
        ObservableList<Appointment> data = FXCollections.observableArrayList(
                new Appointment("10:00 AM", "Dr. Toan Nghiem", "San Nguyen", "A101", "Confirmed"),
                new Appointment("11:30 AM", "Dr. DT", "Ngoc San", "A102", "Pending"),
                new Appointment("02:00 PM", "Dr. TN", "Tuan Tran", "A203", "Completed")
        );


        timeColumn.setCellValueFactory(c -> c.getValue().timeProperty());
        doctorColumn.setCellValueFactory(c -> c.getValue().doctorProperty());
        patientColumn.setCellValueFactory(c -> c.getValue().patientProperty());
        roomColumn.setCellValueFactory(c -> c.getValue().roomProperty());
        statusColumn.setCellValueFactory(c -> c.getValue().statusProperty());

        appointmentTable.setItems(data);
    }

    public static class Appointment {
        private final javafx.beans.property.SimpleStringProperty time;
        private final javafx.beans.property.SimpleStringProperty doctor;
        private final javafx.beans.property.SimpleStringProperty patient;
        private final javafx.beans.property.SimpleStringProperty room;
        private final javafx.beans.property.SimpleStringProperty status;

        public Appointment(String time, String doctor, String patient, String room, String status) {
            this.time = new javafx.beans.property.SimpleStringProperty(time);
            this.doctor = new javafx.beans.property.SimpleStringProperty(doctor);
            this.patient = new javafx.beans.property.SimpleStringProperty(patient);
            this.room = new javafx.beans.property.SimpleStringProperty(room);
            this.status = new javafx.beans.property.SimpleStringProperty(status);
        }

        public javafx.beans.property.StringProperty timeProperty() { return time; }
        public javafx.beans.property.StringProperty doctorProperty() { return doctor; }
        public javafx.beans.property.StringProperty patientProperty() { return patient; }
        public javafx.beans.property.StringProperty roomProperty() { return room; }
        public javafx.beans.property.StringProperty statusProperty() { return status; }
    }
}
