package utils;

public class ValidationUtils {

    
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }
        return phone.matches("^[0-9]{10}$");
    }

    
    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }

    
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 6) {
            return false;
        }
        return true; 
    }

    
    public static boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        return username.length() >= 3 && username.length() <= 20;
    }

    
    public static boolean isPositiveNumber(String number) {
        try {
            int num = Integer.parseInt(number);
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    
    public static boolean isValidDate(String date) {
        if (date == null || !date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return false;
        }
        return true;
    }
}
