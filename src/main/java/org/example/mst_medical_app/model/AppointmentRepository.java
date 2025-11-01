package org.example.mst_medical_app.model;

import org.example.mst_medical_app.core.security.AuthManager;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

public class AppointmentRepository {

    public static List<Appointment> sample() {
        LocalDate base = LocalDate.now().withDayOfMonth(1);
        return List.of(
                new Appointment(base.plusDays(3),  LocalTime.of(9,0),  LocalTime.of(9,30),  "Hand Infection",        "Dr. Toan",1,   "Akule Vivian", 101, Appointment.Status.CONFIRMED, Appointment.Type.EXAMINATION, "#3B82F6"),
                new Appointment(base.plusDays(5),  LocalTime.of(11,30),LocalTime.of(12,0),  "Monthly Checkup",       "Dr. DT",2,     "Ola",102,          Appointment.Status.PENDING,   Appointment.Type.FOLLOW_UP,  "#EF4444"),
                new Appointment(base.plusDays(12), LocalTime.of(8,30), LocalTime.of(17,30), "Malaria Fever",         "Dr. San",3,    "Henry",103,        Appointment.Status.CONFIRMED, Appointment.Type.EMERGENCY,  "#60A5FA"),
                new Appointment(base.plusDays(16), LocalTime.of(16,0), LocalTime.of(17,0),  "Routine Checkup",       "Dr. San",4,    "Sister Ayo",104,   Appointment.Status.CONFIRMED, Appointment.Type.FOLLOW_UP,  "#F43F5E"),
                new Appointment(base.plusDays(21), LocalTime.of(8,30), LocalTime.of(9,0),   "Sperm Boost",           "Dr. Tuan",5,   "Mark",105,         Appointment.Status.CONFIRMED, Appointment.Type.EXAMINATION, "#8B5CF6"),
                new Appointment(base.plusDays(26), LocalTime.of(11,0), LocalTime.of(12,0),  "Ayo's Routine Checkup", "Dr. San",6,    "Sister Ayo",106,   Appointment.Status.CONFIRMED, Appointment.Type.FOLLOW_UP,  "#F43F5E")
        );
    }

    public static List<Appointment> loadForCurrentUser() {
        var all = sample();
        if (AuthManager.isAdmin()) return all;

        var user = AuthManager.getCurUser();
        if (user == null) return all;

        if (AuthManager.isDoctor()) {
            return all.stream().filter(a -> a.getDoctorId() == user.getId()).collect(Collectors.toList());
        }
        if (AuthManager.isPatient()) {
            return all.stream().filter(a -> a.getPatientId() == user.getId()).collect(Collectors.toList());
        }
        return all;
    }
}
