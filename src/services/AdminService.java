package services;

import dao.AdminDAO;
import models.Admin;
import utils.ValidationUtils;

public class AdminService {

    
    public static Object loginAdmin(String username, String password) {
        
        if (!ValidationUtils.isNotEmpty(username)) {
            return "Username cannot be empty!";
        }
        if (!ValidationUtils.isNotEmpty(password)) {
            return "Password cannot be empty!";
        }

        
        Admin admin = AdminDAO.authenticateAdmin(username, password);
        if (admin != null) {
            return admin;
        } else {
            return "Invalid username or password!";
        }
    }

    
    public static Admin getAdminById(int adminId) {
        return AdminDAO.getAdminById(adminId);
    }
}
