package ui;

import services.BookingService;
import javax.swing.*;
import java.awt.*;

public class UpdateBookingStatusFrame extends JFrame {
    private int bookingId;

    public UpdateBookingStatusFrame(int bookingId, JFrame parentFrame) {
        this.bookingId = bookingId;
        
        setTitle("Update Booking Status");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentFrame);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Booking Status:"), gbc);

        String[] bookingStatuses = {"Pending", "Confirmed", "Completed", "Cancelled"};
        JComboBox<String> bookingStatusCombo = new JComboBox<>(bookingStatuses);
        gbc.gridx = 1;
        panel.add(bookingStatusCombo, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Payment Status:"), gbc);

        String[] paymentStatuses = {"Paid", "Unpaid"};
        JComboBox<String> paymentStatusCombo = new JComboBox<>(paymentStatuses);
        gbc.gridx = 1;
        panel.add(paymentStatusCombo, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton updateBtn = new JButton("Update");
        updateBtn.addActionListener(e -> {
            String bookingStatus = (String) bookingStatusCombo.getSelectedItem();
            String paymentStatus = (String) paymentStatusCombo.getSelectedItem();

            String result = BookingService.updateBookingStatus(bookingId, bookingStatus, paymentStatus);
            
            if (result.contains("successfully")) {
                JOptionPane.showMessageDialog(this, result, "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(updateBtn, gbc);

        add(panel);
        setVisible(true);
    }
}
