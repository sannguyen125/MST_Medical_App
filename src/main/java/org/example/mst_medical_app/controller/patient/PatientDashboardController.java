package org.example.mst_medical_app.controller.patient;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import org.example.mst_medical_app.controller.MainLayoutController;
import org.example.mst_medical_app.model.Appointment;
import org.example.mst_medical_app.model.AppointmentRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class PatientDashboardController {

    @FXML private TableView<Appointment> appointmentTable;
    @FXML private TableColumn<Appointment, String> colDate, colTime, colDoctor, colStatus;
    @FXML private LineChart<String, Number> healthLineChart;
    @FXML private Label kpiAppointments, kpiDoctors, kpiMessages, kpiTips;
    @FXML private Button viewAllAppointmentsBtn;

    private MainLayoutController mainLayoutController;

    public void setMainLayoutController(MainLayoutController controller) {
        this.mainLayoutController = controller;
    }

    @FXML
    public void initialize() {
        loadKPIs();
        loadAppointments();
        loadHealthChart();

        viewAllAppointmentsBtn.setOnAction(e -> {
            if (mainLayoutController != null) {
                mainLayoutController.setContent("/org/example/mst_medical_app/admin/Appointments_View.fxml");
            }
        });
    }

    private void loadKPIs() {
        kpiAppointments.setText("3");
        kpiDoctors.setText("4");
        kpiMessages.setText("5");
        kpiTips.setText("12");
    }

    private void loadAppointments() {
        List<Appointment> data = AppointmentRepository.loadForCurrentUser();

        colDate.setCellValueFactory(a -> new javafx.beans.property.SimpleStringProperty(
                a.getValue().getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        colTime.setCellValueFactory(a -> new javafx.beans.property.SimpleStringProperty(
                a.getValue().getStart() + " - " + a.getValue().getEnd()));
        colDoctor.setCellValueFactory(a -> new javafx.beans.property.SimpleStringProperty(a.getValue().getDoctor()));
        colStatus.setCellValueFactory(a -> new javafx.beans.property.SimpleStringProperty(a.getValue().getStatus().toString()));

        appointmentTable.getItems().setAll(data);
    }

    private void loadHealthChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Health Index");

        series.getData().add(new XYChart.Data<>("Jan", 75));
        series.getData().add(new XYChart.Data<>("Feb", 78));
        series.getData().add(new XYChart.Data<>("Mar", 82));
        series.getData().add(new XYChart.Data<>("Apr", 80));
        series.getData().add(new XYChart.Data<>("May", 85));

        healthLineChart.getData().add(series);
    }
}
