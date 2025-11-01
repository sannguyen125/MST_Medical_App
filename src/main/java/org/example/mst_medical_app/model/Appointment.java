package org.example.mst_medical_app.model;

import java.time.*;

public class Appointment {

    public enum Status { PENDING, CONFIRMED, COMPLETED, CANCELED }
    public enum Type { EXAMINATION, EMERGENCY, FOLLOW_UP }

    private final LocalDate date;
    private final LocalTime start;
    private final LocalTime end;
    private final String title;
    private final String doctor;
    private final String patient;
    private final int doctorId;
    private final int patientId;
    private final Status status;
    private final Type type;
    private final String color; // hex màu hiển thị event

    public Appointment(LocalDate date, LocalTime start, LocalTime end,
                       String title, String doctor,int doctorId,String patient, int patientId,
                       Status status, Type type, String color) {
        this.date = date;
        this.start = start;
        this.end = end;
        this.title = title;
        this.doctor = doctor;
        this.patient = patient;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.status = status;
        this.type = type;
        this.color = color;
    }

    public LocalDate getDate() { return date; }
    public LocalTime getStart() { return start; }
    public LocalTime getEnd() { return end; }
    public String getTitle() { return title; }
    public String getDoctor() { return doctor; }
    public String getPatient() { return patient; }
    public int getDoctorId() { return doctorId; }
    public int getPatientId() { return patientId; }
    public Status getStatus() { return status; }
    public Type getType() { return type; }
    public String getColor() { return color; }
}
