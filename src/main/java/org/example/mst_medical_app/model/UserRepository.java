package org.example.mst_medical_app.model;

import org.example.mst_medical_app.core.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class UserRepository {

    public static UserModel checkLogin(String username, String password) {
        String sql = """
            SELECT u.user_id, u.username, u.full_name, u.email,u.phone_number, r.role_name
            FROM users u
            JOIN roles r ON u.role_id = r.role_id
            WHERE u.username = ? AND u.password_hash = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new UserModel(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("role_name")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean updateProfile(UserModel user) {
        String sql = """
                Update users
                Set full_name = ?, email = ?, phone_number = ?
                Where user_id = ?
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhone());
            stmt.setInt(4, user.getId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật thông tin người dùng: " + e.getMessage());
            return false;
        }
    }
}
