package org.example.mst_medical_app.controller.doctor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.mst_medical_app.controller.MainLayoutController;

public class DoctorDashboardController {

    private MainLayoutController mainLayoutController;

    public void setMainLayoutController(MainLayoutController controller) {
        this.mainLayoutController = controller;
    }

    // Header + KPI
    @FXML private Label welcomeLbl;
    @FXML private Label kpiTodayAppointments, kpiNewPatients, kpiCompletedWeek, kpiSatisfaction;

    // Charts
    @FXML private PieChart patientsPie;
    @FXML private LineChart<String, Number> healthLine;
    @FXML private BarChart<String, Number> overallBar;

    // Right-side lists
    @FXML private ListView<String> upcomingList;
    @FXML private ListView<String> previousList;

    // Patients table
    @FXML private TableView<PatientRow> patientsTable;
    @FXML private TableColumn<PatientRow, String> colPatientName;
    @FXML private TableColumn<PatientRow, String> colGender;
    @FXML private TableColumn<PatientRow, String> colChat;
    @FXML private TableColumn<PatientRow, String> colAction;

    @FXML private Button goPatientsBtn;

    @FXML
    public void initialize() {


        // Header
        welcomeLbl.setText("Welcome, Doctor. How are you feeling today?");

        // KPIs (demo data)
        kpiTodayAppointments.setText("8");
        kpiNewPatients.setText("12");
        kpiCompletedWeek.setText("26");
        kpiSatisfaction.setText("95%");

        setupCharts();
        setupRightLists();
        setupPatientsTable();
    }

    /* ----------------- Charts ----------------- */
    private void setupCharts() {

        // Patients Pie
        patientsPie.setData(FXCollections.observableArrayList(
                new PieChart.Data("Men", 48),
                new PieChart.Data("Women", 50),
                new PieChart.Data("Children", 2)
        ));

        // Health Line
        XYChart.Series<String, Number> health = new XYChart.Series<>();
        health.setName("Health Index");
        health.getData().add(new XYChart.Data<>("Jun", 60));
        health.getData().add(new XYChart.Data<>("Jul", 64));
        health.getData().add(new XYChart.Data<>("Aug", 68));
        health.getData().add(new XYChart.Data<>("Sep", 70));
        health.getData().add(new XYChart.Data<>("Oct", 74));
        health.getData().add(new XYChart.Data<>("Nov", 78));
        health.getData().add(new XYChart.Data<>("Dec", 82));
        healthLine.getData().setAll(health);

        // Overall Appointments
        XYChart.Series<String, Number> routine = new XYChart.Series<>();
        routine.setName("Routine");
        routine.getData().add(new XYChart.Data<>("Apr", 20));
        routine.getData().add(new XYChart.Data<>("May", 26));
        routine.getData().add(new XYChart.Data<>("Jun", 22));
        routine.getData().add(new XYChart.Data<>("Jul", 30));
        routine.getData().add(new XYChart.Data<>("Aug", 28));

        XYChart.Series<String, Number> exam = new XYChart.Series<>();
        exam.setName("Examination");
        exam.getData().add(new XYChart.Data<>("Apr", 14));
        exam.getData().add(new XYChart.Data<>("May", 18));
        exam.getData().add(new XYChart.Data<>("Jun", 12));
        exam.getData().add(new XYChart.Data<>("Jul", 20));
        exam.getData().add(new XYChart.Data<>("Aug", 16));

        overallBar.getData().setAll(routine, exam);
    }

    /* --------- Upcoming + Previous Lists --------- */
    private void setupRightLists() {

        upcomingList.setItems(FXCollections.observableArrayList(
                "Tue, Oct 24  •  09:00  •  Laura Bennett (Emergency)",
                "Mon, Nov 2  •  10:30  •  Ebuka Kelechi (Consultation)",
                "Fri, Nov 13 •  14:00  •  Bridget Olowojebe (Routine)"
        ));

        previousList.setItems(FXCollections.observableArrayList(
                "Fri, Aug 18  •  Scott Tam  •  Sick visit",
                "Tue, Aug 29  •  Annie Ahmed  •  Consultation",
                "Wed, Aug 30 •  Brian Paul  •  Emergency"
        ));
    }

    /* ------------- Patients table ---------------- */
    private void setupPatientsTable() {

        colPatientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colChat.setCellValueFactory(new PropertyValueFactory<>("chat"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));

        ObservableList<PatientRow> rows = FXCollections.observableArrayList(
                new PatientRow("Ibrahim Yakeni", "Male", "Open", "View"),
                new PatientRow("Bridget Olowojebe", "Female", "Open", "View")
        );

        patientsTable.setItems(rows);

        // Go to full Patients page
        goPatientsBtn.setOnAction(e -> {
            if (mainLayoutController != null) {
                mainLayoutController.setContent("/org/example/mst_medical_app/doctor/DoctorPatients_View.fxml");
            }
            else{
                System.out.println("MainLayoutController is null");
            }
        });
    }

    /* ------ Simple model class for table ------ */
    public static class PatientRow {
        private final String name;
        private final String gender;
        private final String chat;
        private final String action;

        public PatientRow(String name, String gender, String chat, String action) {
            this.name = name;
            this.gender = gender;
            this.chat = chat;
            this.action = action;
        }

        public String getName() { return name; }
        public String getGender() { return gender; }
        public String getChat() { return chat; }
        public String getAction() { return action; }
    }
}
