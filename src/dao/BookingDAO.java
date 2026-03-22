package dao;

import models.Booking;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    
    public static boolean createBooking(Booking booking) {
        String query = "INSERT INTO bookings (user_id, package_id, event_date, guest_count, special_requests, booking_status, payment_status) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, booking.getUserId());
            stmt.setInt(2, booking.getPackageId());
            stmt.setString(3, booking.getEventDate());
            stmt.setString(4, booking.getGuestCount());
            stmt.setString(5, booking.getSpecialRequests());
            stmt.setString(6, booking.getBookingStatus());
            stmt.setString(7, booking.getPaymentStatus());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error creating booking: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    
    public static List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                bookings.add(new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("user_id"),
                    rs.getInt("package_id"),
                    rs.getString("event_date"),
                    rs.getString("guest_count"),
                    rs.getString("special_requests"),
                    rs.getString("booking_status"),
                    rs.getString("payment_status"),
                    rs.getString("created_at")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all bookings: " + e.getMessage());
        }
        return bookings;
    }

    
    public static List<Booking> getBookingsByUserId(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE user_id = ? ORDER BY created_at DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                bookings.add(new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("user_id"),
                    rs.getInt("package_id"),
                    rs.getString("event_date"),
                    rs.getString("guest_count"),
                    rs.getString("special_requests"),
                    rs.getString("booking_status"),
                    rs.getString("payment_status"),
                    rs.getString("created_at")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting bookings by user: " + e.getMessage());
        }
        return bookings;
    }

    
    public static Booking getBookingById(int bookingId) {
        String query = "SELECT * FROM bookings WHERE booking_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("user_id"),
                    rs.getInt("package_id"),
                    rs.getString("event_date"),
                    rs.getString("guest_count"),
                    rs.getString("special_requests"),
                    rs.getString("booking_status"),
                    rs.getString("payment_status"),
                    rs.getString("created_at")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error getting booking: " + e.getMessage());
        }
        return null;
    }

    
    public static boolean updateBookingStatus(int bookingId, String bookingStatus, String paymentStatus) {
        String query = "UPDATE bookings SET booking_status = ?, payment_status = ? WHERE booking_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, bookingStatus);
            stmt.setString(2, paymentStatus);
            stmt.setInt(3, bookingId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating booking status: " + e.getMessage());
            return false;
        }
    }

    
    public static boolean deleteBooking(int bookingId) {
        String query = "DELETE FROM bookings WHERE booking_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, bookingId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting booking: " + e.getMessage());
            return false;
        }
    }

    
    public static boolean dateConflictExists(int packageId, String eventDate) {
        String query = "SELECT COUNT(*) as count FROM bookings WHERE package_id = ? AND event_date = ? AND booking_status != 'Cancelled'";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, packageId);
            stmt.setString(2, eventDate);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error checking date conflict: " + e.getMessage());
        }
        return false;
    }

    
    public static List<Booking> getBookingsByStatus(String status) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE booking_status = ? ORDER BY created_at DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                bookings.add(new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("user_id"),
                    rs.getInt("package_id"),
                    rs.getString("event_date"),
                    rs.getString("guest_count"),
                    rs.getString("special_requests"),
                    rs.getString("booking_status"),
                    rs.getString("payment_status"),
                    rs.getString("created_at")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting bookings by status: " + e.getMessage());
        }
        return bookings;
    }
}
