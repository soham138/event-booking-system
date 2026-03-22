package ui;

import models.User;
import services.UserService;
import javax.swing.*;

public class UserLoginController {
    public void handleLogin(String username, String password, JFrame loginFrame) {
        Object result = UserService.loginUser(username, password);

        if (result instanceof User) {
            User user = (User) result;
            JOptionPane.showMessageDialog(loginFrame, "Login successful! Welcome " + user.getUsername(), 
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
            loginFrame.dispose();
            new UserDashboard(user);
        } else {
            String error = (String) result;
            JOptionPane.showMessageDialog(loginFrame, error, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
