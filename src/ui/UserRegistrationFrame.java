package ui;

import javax.swing.*;
import java.awt.*;

public class UserRegistrationFrame extends JFrame {
    public UserRegistrationFrame() {
        setTitle("Event Booking System - Register");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);

        JTextField usernameField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Email:"), gbc);

        JTextField emailField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Confirm Password:"), gbc);

        JPasswordField confirmPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(confirmPasswordField, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Phone (10 digits):"), gbc);

        JTextField phoneField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(phoneField, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        JButton registerBtn = new JButton("Register");
        registerBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String phone = phoneField.getText();

            new UserRegistrationController().handleRegistration(
                username, email, password, confirmPassword, phone, this);
        });
        panel.add(registerBtn, gbc);

        add(panel);
        setVisible(true);
    }
}
