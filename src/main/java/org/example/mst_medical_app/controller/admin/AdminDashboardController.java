package org.example.mst_medical_app.controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminDashboardController {

    @FXML private Label patientCountLabel, prescriptionCountLabel, appointmentCountLabel, revenueLabel;
    @FXML private LineChart<String, Number> patientLineChart;
    @FXML private PieChart patientPieChart;
    @FXML private TableView<Patient> recentPatientTable;
    @FXML private TableColumn<Patient, String> nameColumn, genderColumn, doctorColumn, statusColumn;
    @FXML private TableColumn<Patient, Integer> ageColumn;

    @FXML
    public void initialize() {

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Patients 2025");
        series.getData().add(new XYChart.Data<>("Jan", 120));
        series.getData().add(new XYChart.Data<>("Feb", 135));
        series.getData().add(new XYChart.Data<>("Mar", 160));
        series.getData().add(new XYChart.Data<>("Apr", 180));
        series.getData().add(new XYChart.Data<>("May", 210));
        patientLineChart.getData().add(series);


        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("Male", 60),
                new PieChart.Data("Female", 40)
        );
        patientPieChart.setData(pieData);


        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        doctorColumn.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        recentPatientTable.setItems(FXCollections.observableArrayList(
                new Patient("San Nguyen", 25, "Female", "Dr. Olivia Grant", "Completed"),
                new Patient("Nghiem Toan", 32, "Male", "Dr. Carter", "Pending"),
                new Patient("Tuan Tran", 29, "Female", "Dr. Emily Ross", "Confirmed")
        ));
    }

    public static class Patient {
        private final String name;
        private final int age;
        private final String gender;
        private final String doctor;
        private final String status;

        public Patient(String name, int age, String gender, String doctor, String status) {
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.doctor = doctor;
            this.status = status;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
        public String getGender() { return gender; }
        public String getDoctor() { return doctor; }
        public String getStatus() { return status; }
    }
}
