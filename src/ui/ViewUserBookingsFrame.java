package ui;

import models.Booking;
import models.EventPackage;
import services.BookingService;
import services.EventPackageService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewUserBookingsFrame extends JFrame {
    private int userId;

    public ViewUserBookingsFrame(int userId) {
        this.userId = userId;
        setTitle("Event Booking System - My Bookings");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        String[] columns = {"Booking ID", "Package", "Date", "Guests", "Status", "Payment"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        
        refreshTable(tableModel);

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> {
            tableModel.setRowCount(0);
            refreshTable(tableModel);
        });
        buttonPanel.add(refreshBtn);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    private void refreshTable(DefaultTableModel tableModel) {
        List<Booking> bookings = BookingService.getBookingsByUserId(userId);
        for (Booking booking : bookings) {
            EventPackage pkg = EventPackageService.getEventPackageById(booking.getPackageId());
            String packageName = pkg != null ? pkg.getPackageName() : "Unknown";

            tableModel.addRow(new Object[]{
                booking.getBookingId(),
                packageName,
                booking.getEventDate(),
                booking.getGuestCount(),
                booking.getBookingStatus(),
                booking.getPaymentStatus()
            });
        }
    }
}
