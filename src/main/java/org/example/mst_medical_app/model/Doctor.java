package org.example.mst_medical_app.model;

import java.util.List;

public class Doctor {
    private final String name;
    private final String specialization;
    private final String status;

    public Doctor(String name, String specialization, String status) {
        this.name = name;
        this.specialization = specialization;
        this.status = status;
    }

    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public String getStatus() { return status; }

    public static List<Doctor> getSampleDoctors() {
        return List.of(
                new Doctor("Dr. Olivia Grant", "Dermatology", "Available"),
                new Doctor("Dr. Ethan Ross", "Cardiology", "Busy"),
                new Doctor("Dr. Emily Tran", "Neurology", "Available")
        );
    }
}
