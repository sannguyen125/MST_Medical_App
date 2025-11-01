package org.example.mst_medical_app.model;

public class UserModel {
    private int userId;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private String roleName;

    public UserModel(int userId, String username, String fullName, String email,String phone , String roleName) {
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.roleName = roleName;
    }

    public int getId() { return userId; }
    public String getUsername() { return username; }
    public String getFullName() { return fullName; }
    public String getRole() {return roleName; }
    public String getEmail() { return email; }
    public String getPhone() { return  phone; }


    public void setRole(String role) {
        this.roleName = role;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
