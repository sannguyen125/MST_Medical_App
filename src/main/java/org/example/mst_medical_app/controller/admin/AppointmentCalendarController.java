package org.example.mst_medical_app.controller.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.mst_medical_app.model.Appointment;
import org.example.mst_medical_app.model.AppointmentRepository;

import java.io.IOException;
import java.time.*;
import java.time.format.TextStyle;
import java.util.*;

public class AppointmentCalendarController {

    @FXML private GridPane calendarGrid;
    @FXML private Label monthLabel;
    @FXML private Button prevBtn, nextBtn;
    @FXML private ToggleButton dayBtn, weekBtn, monthBtn;

    private YearMonth currentMonth;

    @FXML
    public void initialize() {

        ToggleGroup viewToggle = new ToggleGroup();
        dayBtn.setToggleGroup(viewToggle);
        weekBtn.setToggleGroup(viewToggle);
        monthBtn.setToggleGroup(viewToggle);

        currentMonth = YearMonth.now();
        prevBtn.setOnAction(e -> { currentMonth = currentMonth.minusMonths(1); renderMonth(); });
        nextBtn.setOnAction(e -> { currentMonth = currentMonth.plusMonths(1); renderMonth(); });

        // (Day/Week có thể thêm sau – hiện chỉ render Month)
        renderMonth();
    }

    private void renderMonth() {
        calendarGrid.getChildren().clear();
        calendarGrid.getColumnConstraints().clear();
        calendarGrid.getRowConstraints().clear();

        monthLabel.setText(currentMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()).toUpperCase()
                + " " + currentMonth.getYear());

        String[] days = {"MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY","SUNDAY"};
        for (int c=0; c<7; c++) {
            Label h = new Label(days[c].substring(0,1)+days[c].substring(1).toLowerCase());
            h.setStyle("-fx-font-weight:bold; -fx-text-fill:#6b7280;");
            HBox wrap = new HBox(h); wrap.setAlignment(Pos.CENTER);
            wrap.setStyle("-fx-padding:8 0 8 0; -fx-background-color:transparent;");
            calendarGrid.add(wrap, c, 0);
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100/7.0);
            calendarGrid.getColumnConstraints().add(cc);
        }

        // 6 rows for weeks
        for (int r=1; r<=6; r++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100/6.0);
            calendarGrid.getRowConstraints().add(rc);
        }

        // First day cell index
        LocalDate first = currentMonth.atDay(1);
        int firstDayOfWeek = (first.getDayOfWeek().getValue() + 6) % 7; // Mon=0..Sun=6

        // build cells
        LocalDate cursor = first.minusDays(firstDayOfWeek);
        for (int r=1; r<=6; r++) {
            for (int c=0; c<7; c++) {
                VBox cell = createDayCell(cursor, cursor.getMonth().equals(currentMonth.getMonth()));
                calendarGrid.add(cell, c, r);
                cursor = cursor.plusDays(1);
            }
        }

        // place appointments
        List<Appointment> data = AppointmentRepository.loadForCurrentUser();
        data.stream()
                .filter(a -> YearMonth.from(a.getDate()).equals(currentMonth))
                .forEach(this::addEventToCalendar);
    }

    private VBox createDayCell(LocalDate date, boolean inMonth) {
        VBox cell = new VBox(6);
        cell.setPadding(new Insets(10));
        cell.setStyle("-fx-background-color: white; -fx-background-radius:10; -fx-border-color:#e5e7eb; -fx-border-radius:10;");
        if (!inMonth) cell.setOpacity(0.45);

        Label dayNum = new Label(String.valueOf(date.getDayOfMonth()));
        dayNum.setStyle("-fx-font-weight:bold; -fx-text-fill:#111827;");
        cell.getChildren().add(dayNum);

        return cell;
    }

    private void addEventToCalendar(Appointment appt) {
        // locate cell
        LocalDate first = currentMonth.atDay(1);
        int firstDayOfWeek = (first.getDayOfWeek().getValue() + 6) % 7; // Mon=0..Sun=6
        LocalDate cursor = first.minusDays(firstDayOfWeek);

        int targetIndex = (int) java.time.temporal.ChronoUnit.DAYS.between(cursor, appt.getDate());
        int row = targetIndex / 7 + 1; // +1 for header
        int col = targetIndex % 7;

        // event pill
        HBox pill = new HBox(6);
        pill.setAlignment(Pos.CENTER_LEFT);
        pill.setPadding(new Insets(4,8,4,8));
        pill.setStyle("-fx-background-radius:10; -fx-background-color:" + appt.getColor() + "; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 6,0,0,2);");

        Label title = new Label(appt.getTitle());
        title.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        Label time = new Label("  " + appt.getStart());
        time.setStyle("-fx-text-fill: rgba(255,255,255,0.9);");

        pill.getChildren().addAll(title, time);

        pill.setOnMouseClicked(e -> openEventPopup(appt));

        // add into cell VBox
        Optional<javafx.scene.Node> cell = calendarGrid.getChildren().stream()
                .filter(n -> GridPane.getColumnIndex(n)==col && GridPane.getRowIndex(n)==row)
                .findFirst();

        cell.ifPresent(n -> ((VBox)n).getChildren().add(pill));
    }

    private void openEventPopup(Appointment appt) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/mst_medical_app/admin/Appointment_Event_Popup.fxml"));
            Parent root = loader.load();

            AppointmentEventPopupController c = loader.getController();
            c.bind(appt);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Appointment details");
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
