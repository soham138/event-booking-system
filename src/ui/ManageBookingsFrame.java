package ui;

import models.Booking;
import models.EventPackage;
import models.User;
import services.BookingService;
import services.EventPackageService;
import services.UserService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManageBookingsFrame extends JFrame {
    public ManageBookingsFrame() {
        setTitle("Event Booking System - Manage Bookings");
        setSize(1100, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel filterLabel = new JLabel("Filter by Status:");
        String[] statuses = {"All", "Pending", "Confirmed", "Completed", "Cancelled"};
        JComboBox<String> statusCombo = new JComboBox<>(statuses);
        filterPanel.add(filterLabel);
        filterPanel.add(statusCombo);
        
        mainPanel.add(filterPanel, BorderLayout.NORTH);

        
        String[] columns = {"Booking ID", "Username", "Package", "Date", "Guests", "Status", "Payment"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        
        refreshTable(tableModel, "All");

        
        statusCombo.addActionListener(e -> {
            String selectedStatus = (String) statusCombo.getSelectedItem();
            tableModel.setRowCount(0);
            refreshTable(tableModel, selectedStatus);
        });

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> {
            tableModel.setRowCount(0);
            String selectedStatus = (String) statusCombo.getSelectedItem();
            refreshTable(tableModel, selectedStatus);
        });
        buttonPanel.add(refreshBtn);

        JButton updateBtn = new JButton("Update Status");
        updateBtn.addActionListener(e -> {
            int rows = table.getSelectedRow();
            if (rows < 0) {
                JOptionPane.showMessageDialog(this, "Please select a booking!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int bookingId = (int) tableModel.getValueAt(rows, 0);
            new UpdateBookingStatusFrame(bookingId, this);
        });
        buttonPanel.add(updateBtn);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void refreshTable(DefaultTableModel tableModel, String statusFilter) {
        List<Booking> bookings;
        if ("All".equals(statusFilter)) {
            bookings = BookingService.getAllBookings();
        } else {
            bookings = BookingService.getBookingsByStatus(statusFilter);
        }

        for (Booking booking : bookings) {
            User user = UserService.getUserById(booking.getUserId());
            EventPackage pkg = EventPackageService.getEventPackageById(booking.getPackageId());

            String username = user != null ? user.getUsername() : "Unknown";
            String packageName = pkg != null ? pkg.getPackageName() : "Unknown";

            tableModel.addRow(new Object[]{
                booking.getBookingId(),
                username,
                packageName,
                booking.getEventDate(),
                booking.getGuestCount(),
                booking.getBookingStatus(),
                booking.getPaymentStatus()
            });
        }
    }
}
