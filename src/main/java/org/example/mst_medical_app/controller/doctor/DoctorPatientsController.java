package org.example.mst_medical_app.controller.doctor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import org.example.mst_medical_app.core.security.AuthManager;
import org.example.mst_medical_app.model.Patient;

public class DoctorPatientsController {

    @FXML private TableView<Patient> patientsTable;
    @FXML private TableColumn<Patient, String> colName;
    @FXML private TableColumn<Patient, String> colAge;
    @FXML private TableColumn<Patient, String> colGender;
    @FXML private TableColumn<Patient, String> colCondition;
    @FXML private TableColumn<Patient, String> colStatus;
    @FXML private TableColumn<Patient, Void> colAction;

    @FXML private TextField searchField;
    @FXML private ComboBox<String> statusFilter;

    private ObservableList<Patient> patientsList;

    @FXML
    public void initialize() {
        // Lọc demo
        statusFilter.setItems(FXCollections.observableArrayList("All", "Active", "Recovered", "Critical"));
        statusFilter.setValue("All");

        // Dữ liệu bệnh nhân mẫu
        patientsList = FXCollections.observableArrayList(
                new Patient("San Nguyen", 25, "Female", "Skin Rash", "Active"),
                new Patient("Tuan Tran", 30, "Male", "Post Surgery", "Recovered"),
                new Patient("Henry Vu", 40, "Male", "High Fever", "Critical"),
                new Patient("Mai Linh", 21, "Female", "Acne", "Active")
        );

        setupTable();

        // Gán dữ liệu ban đầu
        patientsTable.setItems(patientsList);

        // Lọc theo status
        statusFilter.setOnAction(e -> applyFilter());
        searchField.textProperty().addListener((obs, old, val) -> applyFilter());
    }

    private void setupTable() {
        colName.setCellValueFactory(c -> c.getValue().nameProperty());
        colAge.setCellValueFactory(c -> c.getValue().ageProperty().asString());
        colGender.setCellValueFactory(c -> c.getValue().genderProperty());
        colCondition.setCellValueFactory(c -> c.getValue().conditionProperty());
        colStatus.setCellValueFactory(c -> c.getValue().statusProperty());

        colAction.setCellFactory(tc -> new TableCell<>() {
            private final Button viewBtn = new Button("View");

            {
                viewBtn.setStyle("-fx-background-color: #2563EB; -fx-text-fill: white; -fx-background-radius: 8;");
                viewBtn.setOnAction(e -> {
                    Patient p = getTableView().getItems().get(getIndex());
                    new Alert(Alert.AlertType.INFORMATION,
                            "Viewing details for " + p.getName() + " (Doctor: " + AuthManager.getFullName() + ")"
                    ).showAndWait();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) setGraphic(null);
                else setGraphic(new HBox(viewBtn));
            }
        });
    }

    private void applyFilter() {
        String text = searchField.getText().toLowerCase().trim();
        String status = statusFilter.getValue();

        patientsTable.setItems(patientsList.filtered(p ->
                (status.equals("All") || p.getStatus().equals(status)) &&
                        (p.getName().toLowerCase().contains(text) ||
                                p.getCondition().toLowerCase().contains(text))
        ));
    }
}
