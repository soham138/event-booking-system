package ui;

import models.EventPackage;
import services.BookingService;
import services.EventPackageService;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BookEventFrame extends JFrame {
    private int userId;

    public BookEventFrame(int userId) {
        this.userId = userId;
        setTitle("Event Booking System - Book Event");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Select Package:"), gbc);

        List<EventPackage> packages = EventPackageService.getAllEventPackages();
        String[] packageNames = new String[packages.size()];
        for (int i = 0; i < packages.size(); i++) {
            packageNames[i] = packages.get(i).getPackageName() + " ($" + packages.get(i).getPrice() + ")";
        }

        JComboBox<String> packageCombo = new JComboBox<>(packageNames);
        gbc.gridx = 1;
        panel.add(packageCombo, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Event Date (YYYY-MM-DD):"), gbc);

        JTextField dateField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(dateField, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Guest Count:"), gbc);

        JTextField guestField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(guestField, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Special Requests:"), gbc);

        JTextArea requestsArea = new JTextArea(3, 20);
        JScrollPane requestsScroll = new JScrollPane(requestsArea);
        gbc.gridx = 1;
        panel.add(requestsScroll, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton bookBtn = new JButton("Book Event");
        bookBtn.addActionListener(e -> {
            int selectedIndex = packageCombo.getSelectedIndex();
            if (selectedIndex < 0) {
                JOptionPane.showMessageDialog(panel, "Please select a package!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            EventPackage selectedPackage = packages.get(selectedIndex);
            String eventDate = dateField.getText();
            String guestCount = guestField.getText();
            String specialRequests = requestsArea.getText();

            String result = BookingService.createBooking(userId, selectedPackage.getPackageId(), eventDate, guestCount, specialRequests);

            if (result.contains("successfully")) {
                JOptionPane.showMessageDialog(panel, result, "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(panel, result, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(bookBtn, gbc);

        add(panel);
        setVisible(true);
    }
}
