package org.example.mst_medical_app.core.utils;

public class UserSession {
    private static String role;
    private  static String username;

    public static void setUser(String username, String role) {
        UserSession.role = role;
        UserSession.username = username;
    }

    public static String getRole() {
        return role;
    }

    public static boolean isAdmin() {
        return "ADMIN".equals(role);
    }

    public static boolean isPatient() {
        return "PATIENT".equals(role);
    }
    public static boolean isDoctor() {return "DOCTOR".equals(role);}
}
