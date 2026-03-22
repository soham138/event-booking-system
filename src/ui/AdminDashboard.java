package ui;

import models.Admin;
import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {
    private Admin admin;

    public AdminDashboard(Admin admin) {
        this.admin = admin;
        setTitle("Event Booking System - Admin Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        
        JLabel welcomeLabel = new JLabel("Welcome Admin " + admin.getAdminUsername() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(25, 43, 86));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(welcomeLabel, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton managePackagesBtn = new JButton("Manage Event Packages");
        managePackagesBtn.setBackground(new Color(65, 105, 225));
        managePackagesBtn.setForeground(Color.WHITE);
        managePackagesBtn.setFont(new Font("Arial", Font.BOLD, 12));
        managePackagesBtn.setOpaque(true);
        managePackagesBtn.setBorderPainted(false);
        managePackagesBtn.setPreferredSize(new Dimension(250, 40));
        managePackagesBtn.addActionListener(e -> new ManagePackagesFrame());
        panel.add(managePackagesBtn, gbc);

        
        gbc.gridy = 2;
        JButton manageBookingsBtn = new JButton("Manage Bookings");
        manageBookingsBtn.setBackground(new Color(65, 105, 225));
        manageBookingsBtn.setForeground(Color.WHITE);
        manageBookingsBtn.setFont(new Font("Arial", Font.BOLD, 12));
        manageBookingsBtn.setOpaque(true);
        manageBookingsBtn.setBorderPainted(false);
        manageBookingsBtn.setPreferredSize(new Dimension(250, 40));
        manageBookingsBtn.addActionListener(e -> new ManageBookingsFrame());
        panel.add(manageBookingsBtn, gbc);

        
        gbc.gridy = 3;
        JButton viewStatsBtn = new JButton("View Bookings Summary");
        viewStatsBtn.setBackground(new Color(65, 105, 225));
        viewStatsBtn.setForeground(Color.WHITE);
        viewStatsBtn.setFont(new Font("Arial", Font.BOLD, 12));
        viewStatsBtn.setOpaque(true);
        viewStatsBtn.setBorderPainted(false);
        viewStatsBtn.setPreferredSize(new Dimension(250, 40));
        viewStatsBtn.addActionListener(e -> new ViewBookingsSummaryFrame());
        panel.add(viewStatsBtn, gbc);

        
        gbc.gridy = 4;
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(new Color(200, 50, 50));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 12));
        logoutBtn.setOpaque(true);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setPreferredSize(new Dimension(250, 40));
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });
        panel.add(logoutBtn, gbc);

        add(panel);
        setVisible(true);
    }
}
