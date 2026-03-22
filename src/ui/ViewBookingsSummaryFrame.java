package ui;

import models.Booking;
import services.BookingService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewBookingsSummaryFrame extends JFrame {
    public ViewBookingsSummaryFrame() {
        setTitle("Bookings Summary");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        JPanel summaryPanel = createSummaryPanel();
        mainPanel.add(summaryPanel, BorderLayout.NORTH);

        
        String[] columns = {"Booking ID", "User ID", "Package ID", "Date", "Status", "Payment"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        
        refreshTable(tableModel);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createSummaryPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Booking Summary"));

        List<Booking> pendingBookings = BookingService.getBookingsByStatus("Pending");
        List<Booking> confirmedBookings = BookingService.getBookingsByStatus("Confirmed");
        List<Booking> completedBookings = BookingService.getBookingsByStatus("Completed");
        List<Booking> allBookings = BookingService.getAllBookings();

        JLabel pendingLabel = new JLabel("Pending: " + pendingBookings.size());
        pendingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel confirmedLabel = new JLabel("Confirmed: " + confirmedBookings.size());
        confirmedLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel completedLabel = new JLabel("Completed: " + completedBookings.size());
        completedLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel totalLabel = new JLabel("Total: " + allBookings.size());
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));

        panel.add(pendingLabel);
        panel.add(confirmedLabel);
        panel.add(completedLabel);
        panel.add(totalLabel);

        return panel;
    }

    private void refreshTable(DefaultTableModel tableModel) {
        List<Booking> bookings = BookingService.getAllBookings();
        for (Booking booking : bookings) {
            tableModel.addRow(new Object[]{
                booking.getBookingId(),
                booking.getUserId(),
                booking.getPackageId(),
                booking.getEventDate(),
                booking.getBookingStatus(),
                booking.getPaymentStatus()
            });
        }
    }
}
