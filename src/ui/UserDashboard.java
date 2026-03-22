package ui;

import models.User;
import javax.swing.*;
import java.awt.*;

public class UserDashboard extends JFrame {
    private User user;

    public UserDashboard(User user) {
        this.user = user;
        setTitle("Event Booking System - User Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        
        JLabel welcomeLabel = new JLabel("Welcome " + user.getUsername() + "!");
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
        JButton viewPackagesBtn = new JButton("View Available Packages");
        viewPackagesBtn.setBackground(new Color(65, 105, 225));
        viewPackagesBtn.setForeground(Color.WHITE);
        viewPackagesBtn.setFont(new Font("Arial", Font.BOLD, 12));
        viewPackagesBtn.setOpaque(true);
        viewPackagesBtn.setBorderPainted(false);
        viewPackagesBtn.setPreferredSize(new Dimension(250, 40));
        viewPackagesBtn.addActionListener(e -> new ViewPackagesFrame());
        panel.add(viewPackagesBtn, gbc);

        
        gbc.gridy = 2;
        JButton bookEventBtn = new JButton("Book an Event");
        bookEventBtn.setBackground(new Color(65, 105, 225));
        bookEventBtn.setForeground(Color.WHITE);
        bookEventBtn.setFont(new Font("Arial", Font.BOLD, 12));
        bookEventBtn.setOpaque(true);
        bookEventBtn.setBorderPainted(false);
        bookEventBtn.setPreferredSize(new Dimension(250, 40));
        bookEventBtn.addActionListener(e -> new BookEventFrame(user.getUserId()));
        panel.add(bookEventBtn, gbc);

        
        gbc.gridy = 3;
        JButton viewBookingsBtn = new JButton("View My Bookings");
        viewBookingsBtn.setBackground(new Color(65, 105, 225));
        viewBookingsBtn.setForeground(Color.WHITE);
        viewBookingsBtn.setFont(new Font("Arial", Font.BOLD, 12));
        viewBookingsBtn.setOpaque(true);
        viewBookingsBtn.setBorderPainted(false);
        viewBookingsBtn.setPreferredSize(new Dimension(250, 40));
        viewBookingsBtn.addActionListener(e -> new ViewUserBookingsFrame(user.getUserId()));
        panel.add(viewBookingsBtn, gbc);

        
        gbc.gridy = 4;
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFont(new Font("Arial", Font.PLAIN, 12));
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
