package dao;

import models.Admin;
import utils.DatabaseConnection;

import java.sql.*;

public class AdminDAO {

    
    public static Admin authenticateAdmin(String username, String password) {
        String query = "SELECT * FROM admins WHERE admin_username = ? AND admin_password = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Admin(
                    rs.getInt("admin_id"),
                    rs.getString("admin_username"),
                    rs.getString("admin_password"),
                    rs.getString("email"),
                    rs.getString("created_at")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error authenticating admin: " + e.getMessage());
        }
        return null;
    }

    
    public static Admin getAdminById(int adminId) {
        String query = "SELECT * FROM admins WHERE admin_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, adminId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Admin(
                    rs.getInt("admin_id"),
                    rs.getString("admin_username"),
                    rs.getString("admin_password"),
                    rs.getString("email"),
                    rs.getString("created_at")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error getting admin: " + e.getMessage());
        }
        return null;
    }

    
    public static boolean adminExists(String username) {
        String query = "SELECT * FROM admins WHERE admin_username = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Error checking admin existence: " + e.getMessage());
            return false;
        }
    }
}
