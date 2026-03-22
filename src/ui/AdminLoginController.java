package ui;

import models.Admin;
import services.AdminService;
import javax.swing.*;

public class AdminLoginController {
    public void handleLogin(String username, String password, JFrame loginFrame) {
        Object result = AdminService.loginAdmin(username, password);

        if (result instanceof Admin) {
            Admin admin = (Admin) result;
            JOptionPane.showMessageDialog(loginFrame, "Login successful! Welcome Admin " + admin.getAdminUsername(), 
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
            loginFrame.dispose();
            new AdminDashboard(admin);
        } else {
            String error = (String) result;
            JOptionPane.showMessageDialog(loginFrame, error, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
