package ui;

import services.UserService;
import javax.swing.*;

public class UserRegistrationController {
    public void handleRegistration(String username, String email, String password, 
                                   String confirmPassword, String phone, JFrame frame) {
        String result = UserService.registerUser(username, email, password, confirmPassword, phone);
        
        if (result.contains("successful")) {
            JOptionPane.showMessageDialog(frame, result, "Success", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, result, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
