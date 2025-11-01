package org.example.mst_medical_app.core.security;

import org.example.mst_medical_app.model.UserModel;

public class AuthManager {
    private static UserModel curUser;

    public static void login(UserModel user) {
        curUser = user;
    }

    public static void logOut() {
        curUser = null;
    }

    public static boolean isLoggedIn() {
        return curUser != null;
    }

    public static UserModel getCurUser() {
        return curUser;
    }

    public static String getCurRole() {
        return curUser != null ? curUser.getRole() : null;
    }

    public static boolean isAdmin() {
        return curUser != null && "ADMIN".equalsIgnoreCase(curUser.getRole());
    }

    public static boolean isDoctor() {
        return curUser != null && "DOCTOR".equalsIgnoreCase(curUser.getRole());
    }

    public static boolean isPatient() {
        return curUser != null && "PATIENT".equalsIgnoreCase(curUser.getRole());
    }

    public static String getFullName() {
        return curUser != null ? curUser.getFullName() : null;
    }
    public static String getEmail() {return curUser != null ? curUser.getEmail() : null;}
//    public static String getPhone() {return curUser != null ? curUser.getPhone() : null;}
}
