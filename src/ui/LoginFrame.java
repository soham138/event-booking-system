package ui;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel userLoginPanel;
    private JPanel adminLoginPanel;

    public LoginFrame() {
        setTitle("Event Booking System - Login");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        tabbedPane = new JTabbedPane();

        
        userLoginPanel = createUserLoginPanel();
        tabbedPane.addTab("User Login", userLoginPanel);

        
        adminLoginPanel = createAdminLoginPanel();
        tabbedPane.addTab("Admin Login", adminLoginPanel);

        add(tabbedPane);
        setVisible(true);
    }

    
    private JPanel createUserLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);

        JTextField usernameField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);

        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(65, 105, 225));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 12));
        loginBtn.setOpaque(true);
        loginBtn.setBorderPainted(false);
        loginBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter username and password!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                new UserLoginController().handleLogin(username, password, this);
            }
        });
        panel.add(loginBtn, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 3;
        JButton registerBtn = new JButton("Register");
        registerBtn.setBackground(new Color(65, 105, 225));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFont(new Font("Arial", Font.BOLD, 12));
        registerBtn.setOpaque(true);
        registerBtn.setBorderPainted(false);
        registerBtn.addActionListener(e -> new UserRegistrationFrame());
        panel.add(registerBtn, gbc);

        return panel;
    }

    
    private JPanel createAdminLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Admin Username:"), gbc);

        JTextField usernameField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Admin Password:"), gbc);

        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(65, 105, 225));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 12));
        loginBtn.setOpaque(true);
        loginBtn.setBorderPainted(false);
        loginBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter username and password!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                new AdminLoginController().handleLogin(username, password, this);
            }
        });
        panel.add(loginBtn, gbc);

        return panel;
    }
}
