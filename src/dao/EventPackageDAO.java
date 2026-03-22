package dao;

import models.EventPackage;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventPackageDAO {

    
    public static boolean addEventPackage(EventPackage pkg) {
        String query = "INSERT INTO event_packages (package_name, description, price, capacity) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, pkg.getPackageName());
            stmt.setString(2, pkg.getDescription());
            stmt.setDouble(3, pkg.getPrice());
            stmt.setInt(4, pkg.getCapacity());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding event package: " + e.getMessage());
            return false;
        }
    }

    
    public static List<EventPackage> getAllEventPackages() {
        List<EventPackage> packages = new ArrayList<>();
        String query = "SELECT * FROM event_packages";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                packages.add(new EventPackage(
                    rs.getInt("package_id"),
                    rs.getString("package_name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("capacity"),
                    rs.getString("created_at")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all event packages: " + e.getMessage());
        }
        return packages;
    }

    
    public static EventPackage getEventPackageById(int packageId) {
        String query = "SELECT * FROM event_packages WHERE package_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, packageId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new EventPackage(
                    rs.getInt("package_id"),
                    rs.getString("package_name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("capacity"),
                    rs.getString("created_at")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error getting event package: " + e.getMessage());
        }
        return null;
    }

    
    public static List<EventPackage> searchEventPackages(String searchTerm) {
        List<EventPackage> packages = new ArrayList<>();
        String query = "SELECT * FROM event_packages WHERE package_name LIKE ? OR description LIKE ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            String searchPattern = "%" + searchTerm + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                packages.add(new EventPackage(
                    rs.getInt("package_id"),
                    rs.getString("package_name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("capacity"),
                    rs.getString("created_at")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error searching event packages: " + e.getMessage());
        }
        return packages;
    }

    
    public static boolean updateEventPackage(EventPackage pkg) {
        String query = "UPDATE event_packages SET package_name = ?, description = ?, price = ?, capacity = ? WHERE package_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, pkg.getPackageName());
            stmt.setString(2, pkg.getDescription());
            stmt.setDouble(3, pkg.getPrice());
            stmt.setInt(4, pkg.getCapacity());
            stmt.setInt(5, pkg.getPackageId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating event package: " + e.getMessage());
            return false;
        }
    }

    
    public static boolean deleteEventPackage(int packageId) {
        String query = "DELETE FROM event_packages WHERE package_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, packageId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting event package: " + e.getMessage());
            return false;
        }
    }
}
