package org.example.mst_medical_app.controller.admin;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class DoctorsController {

    @FXML private TextField searchField;
    @FXML private Button addDoctorBtn;
    @FXML private ComboBox<String> specializationFilter;
    @FXML private ComboBox<String> statusFilter;
    @FXML private FlowPane doctorContainer;

    @FXML
    public void initialize() {
        // bộ lọc
        specializationFilter.getItems().addAll("Dermatology", "Surgery", "Aesthetic", "Cardiology");
        statusFilter.getItems().addAll("Available", "Busy", "On Leave");

        // Hiển thị danh sách bác sĩ demo
        addDoctorCard("Dr. Toan Nghiem", "DB-001", "Dermatology", "(123) 456-7891", 4.9, "2,150 reviews");
        addDoctorCard("Dr. DT", "DB-002", "Cosmetic Surgery", "(123) 456-7892", 4.8, "1,980 reviews");
        addDoctorCard("Dr. San Nguyen", "DB-003", "Aesthetic Medicine", "(123) 456-7893", 4.7, "1,750 reviews");
        addDoctorCard("Dr. Tuan Tran", "DB-004", "Plastic Surgery", "(123) 456-7894", 4.6, "1,500 reviews");
        addDoctorCard("Dr. Messi", "DB-005", "Cosmetic Dermatology", "(123) 456-7895", 4.5, "1,200 reviews");
        addDoctorCard("Dr. Ricon", "DB-006", "Body Contouring", "(123) 456-7896", 4.4, "980 reviews");
    }

    // Hàm tạo card bác sĩ
    private void addDoctorCard(String name, String id, String specialty, String phone, double rating, String reviews) {

        Label nameLabel = new Label(name);
        nameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Label idLabel = new Label(id + " • " + specialty);
        idLabel.setStyle("-fx-text-fill: #555;");

        Label phoneLabel = new Label(phone);
        phoneLabel.setStyle("-fx-text-fill: #666;");

        Label ratingLabel = new Label("⭐ " + rating + " (" + reviews + ")");
        ratingLabel.setStyle("-fx-text-fill: #f4b400;");

        // Gom vào card
        VBox card = new VBox(5, nameLabel, idLabel, phoneLabel, ratingLabel);
        card.setPadding(new Insets(15));
        card.setPrefSize(200, 150);
        card.setStyle("""
            -fx-background-color: white;
            -fx-background-radius: 15;
            -fx-border-color: #e0e0e0;
            -fx-border-radius: 15;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 8, 0, 0, 3);
            """);

        // Hiệu ứng hover
        card.setOnMouseEntered(e ->
                card.setStyle(card.getStyle() + "-fx-scale-x:1.03; -fx-scale-y:1.03; -fx-cursor: hand;"));
        card.setOnMouseExited(e ->
                card.setStyle(card.getStyle().replace("-fx-scale-x:1.03; -fx-scale-y:1.03; -fx-cursor: hand;", "")));

        // Thêm card vào FlowPane
        doctorContainer.getChildren().add(card);
    }
}
