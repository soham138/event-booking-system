package models;

public class Booking {
    private int bookingId;
    private int userId;
    private int packageId;
    private String eventDate;
    private String guestCount;
    private String specialRequests;
    private String bookingStatus; 
    private String paymentStatus; 
    private String createdAt;

    
    public Booking(int bookingId, int userId, int packageId, String eventDate, String guestCount,
                   String specialRequests, String bookingStatus, String paymentStatus, String createdAt) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.packageId = packageId;
        this.eventDate = eventDate;
        this.guestCount = guestCount;
        this.specialRequests = specialRequests;
        this.bookingStatus = bookingStatus;
        this.paymentStatus = paymentStatus;
        this.createdAt = createdAt;
    }

    
    public Booking(int userId, int packageId, String eventDate, String guestCount, String specialRequests) {
        this.userId = userId;
        this.packageId = packageId;
        this.eventDate = eventDate;
        this.guestCount = guestCount;
        this.specialRequests = specialRequests;
        this.bookingStatus = "Pending";
        this.paymentStatus = "Unpaid";
    }

    
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(String guestCount) {
        this.guestCount = guestCount;
    }

    public String getSpecialRequests() {
        return specialRequests;
    }

    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", userId=" + userId +
                ", packageId=" + packageId +
                ", eventDate='" + eventDate + '\'' +
                ", bookingStatus='" + bookingStatus + '\'' +
                '}';
    }
}
