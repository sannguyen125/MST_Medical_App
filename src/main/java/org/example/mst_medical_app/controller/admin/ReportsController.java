package org.example.mst_medical_app.controller.admin;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class ReportsController {
    @FXML
    private PieChart pieChart;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private Label totalPatientsLabel, totalAppointmentsLabel, totalRevenueLabel;

    @FXML
    public void initialize() {
        loadPieChart();
        loadBarChart();
        updateSummary();
    }

    private void loadPieChart() {
        pieChart.getData().addAll(
                new PieChart.Data("Hoàn thành", 120),
                new PieChart.Data("Đang chờ", 45),
                new PieChart.Data("Hủy", 10)
        );
    }

    private void loadBarChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Cuộc hẹn theo tháng");

        series.getData().add(new XYChart.Data<>("Tháng 1", 40));
        series.getData().add(new XYChart.Data<>("Tháng 2", 60));
        series.getData().add(new XYChart.Data<>("Tháng 3", 30));
        series.getData().add(new XYChart.Data<>("Tháng 4", 80));

        barChart.getData().add(series);
    }

    private void updateSummary() {
        totalPatientsLabel.setText("Tổng bệnh nhân: 175");
        totalAppointmentsLabel.setText("Tổng cuộc hẹn: 320");
        totalRevenueLabel.setText("Doanh thu: 58.000.000đ");
    }
}
