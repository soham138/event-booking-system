package services;

import dao.BookingDAO;
import dao.EventPackageDAO;
import models.Booking;
import models.EventPackage;
import utils.ValidationUtils;

import java.util.List;

public class BookingService {

    
    public static String createBooking(int userId, int packageId, String eventDate, String guestCount, String specialRequests) {
        
        if (!ValidationUtils.isValidDate(eventDate)) {
            return "Invalid date format! Use YYYY-MM-DD";
        }
        if (!ValidationUtils.isPositiveNumber(guestCount)) {
            return "Guest count must be a positive number!";
        }

        
        if (BookingDAO.dateConflictExists(packageId, eventDate)) {
            return "This package is already booked for the selected date! Please choose another date.";
        }

        
        EventPackage pkg = EventPackageDAO.getEventPackageById(packageId);
        if (pkg == null) {
            return "Event package not found!";
        }

        int guestCountValue = Integer.parseInt(guestCount);
        if (guestCountValue > pkg.getCapacity()) {
            return "Guest count exceeds package capacity! Max capacity: " + pkg.getCapacity();
        }

        
        Booking booking = new Booking(userId, packageId, eventDate, guestCount, specialRequests);
        if (BookingDAO.createBooking(booking)) {
            return "Booking created successfully! Your booking status is Pending.";
        } else {
            return "Failed to create booking!";
        }
    }

    
    public static Booking getBookingById(int bookingId) {
        return BookingDAO.getBookingById(bookingId);
    }

    
    public static List<Booking> getBookingsByUserId(int userId) {
        return BookingDAO.getBookingsByUserId(userId);
    }

    
    public static List<Booking> getAllBookings() {
        return BookingDAO.getAllBookings();
    }

    
    public static List<Booking> getBookingsByStatus(String status) {
        return BookingDAO.getBookingsByStatus(status);
    }

    
    public static String updateBookingStatus(int bookingId, String bookingStatus, String paymentStatus) {
        
        if (!isValidBookingStatus(bookingStatus)) {
            return "Invalid booking status!";
        }
        if (!isValidPaymentStatus(paymentStatus)) {
            return "Invalid payment status!";
        }

        Booking booking = BookingDAO.getBookingById(bookingId);
        if (booking == null) {
            return "Booking not found!";
        }

        if (BookingDAO.updateBookingStatus(bookingId, bookingStatus, paymentStatus)) {
            return "Booking status updated successfully!";
        } else {
            return "Failed to update booking status!";
        }
    }

    
    public static String deleteBooking(int bookingId) {
        if (BookingDAO.deleteBooking(bookingId)) {
            return "Booking deleted successfully!";
        } else {
            return "Failed to delete booking!";
        }
    }

    
    private static boolean isValidBookingStatus(String status) {
        return status.equals("Pending") || status.equals("Confirmed") || status.equals("Completed") || status.equals("Cancelled");
    }

    
    private static boolean isValidPaymentStatus(String status) {
        return status.equals("Paid") || status.equals("Unpaid");
    }

    
    public static String getBookingDetails(int bookingId) {
        Booking booking = BookingDAO.getBookingById(bookingId);
        if (booking == null) {
            return "Booking not found!";
        }

        EventPackage pkg = EventPackageDAO.getEventPackageById(booking.getPackageId());
        if (pkg == null) {
            return "Package information not available!";
        }

        return "Booking ID: " + booking.getBookingId() + "\n" +
               "Package: " + pkg.getPackageName() + "\n" +
               "Price: $" + pkg.getPrice() + "\n" +
               "Date: " + booking.getEventDate() + "\n" +
               "Guests: " + booking.getGuestCount() + "\n" +
               "Status: " + booking.getBookingStatus() + "\n" +
               "Payment: " + booking.getPaymentStatus();
    }
}
