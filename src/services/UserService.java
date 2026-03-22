package services;

import dao.UserDAO;
import models.User;
import utils.ValidationUtils;

public class UserService {

    
    public static String registerUser(String username, String email, String password, String confirmPassword, String phone) {
        
        if (!ValidationUtils.isNotEmpty(username)) {
            return "Username cannot be empty!";
        }
        if (!ValidationUtils.isValidUsername(username)) {
            return "Username must be 3-20 characters long!";
        }
        if (!ValidationUtils.isValidEmail(email)) {
            return "Invalid email format!";
        }
        if (!ValidationUtils.isValidPassword(password)) {
            return "Password must be at least 6 characters long!";
        }
        if (!password.equals(confirmPassword)) {
            return "Passwords do not match!";
        }
        if (!ValidationUtils.isValidPhone(phone)) {
            return "Phone number must be 10 digits!";
        }

        
        if (UserDAO.userExists(username, email)) {
            return "Username or email already exists!";
        }

        
        User user = new User(username, email, password, phone);
        if (UserDAO.registerUser(user)) {
            return "Registration successful! You can now login.";
        } else {
            return "Registration failed! Please try again.";
        }
    }

    
    public static Object loginUser(String username, String password) {
        
        if (!ValidationUtils.isNotEmpty(username)) {
            return "Username cannot be empty!";
        }
        if (!ValidationUtils.isNotEmpty(password)) {
            return "Password cannot be empty!";
        }

        
        User user = UserDAO.authenticateUser(username, password);
        if (user != null) {
            return user;
        } else {
            return "Invalid username or password!";
        }
    }

    
    public static User getUserById(int userId) {
        return UserDAO.getUserById(userId);
    }

    
    public static String updateUserProfile(User user, String email, String phone) {
        if (!ValidationUtils.isValidEmail(email)) {
            return "Invalid email format!";
        }
        if (!ValidationUtils.isValidPhone(phone)) {
            return "Phone number must be 10 digits!";
        }

        user.setEmail(email);
        user.setPhone(phone);

        if (UserDAO.updateUser(user)) {
            return "Profile updated successfully!";
        } else {
            return "Failed to update profile!";
        }
    }

    
    public static boolean checkUserExists(String username) {
        return UserDAO.userExists(username, "");
    }
}
