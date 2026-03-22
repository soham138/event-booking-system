package services;

import dao.EventPackageDAO;
import models.EventPackage;
import utils.ValidationUtils;

import java.util.List;

public class EventPackageService {

    
    public static String addEventPackage(String packageName, String description, String price, String capacity) {
        
        if (!ValidationUtils.isNotEmpty(packageName)) {
            return "Package name cannot be empty!";
        }
        if (!ValidationUtils.isNotEmpty(description)) {
            return "Description cannot be empty!";
        }
        if (!ValidationUtils.isNotEmpty(price)) {
            return "Price cannot be empty!";
        }
        if (!ValidationUtils.isNotEmpty(capacity)) {
            return "Capacity cannot be empty!";
        }

        try {
            double priceValue = Double.parseDouble(price);
            int capacityValue = Integer.parseInt(capacity);

            if (priceValue <= 0) {
                return "Price must be greater than 0!";
            }
            if (capacityValue <= 0) {
                return "Capacity must be greater than 0!";
            }

            EventPackage pkg = new EventPackage(packageName, description, priceValue, capacityValue);
            if (EventPackageDAO.addEventPackage(pkg)) {
                return "Event package added successfully!";
            } else {
                return "Failed to add event package!";
            }
        } catch (NumberFormatException e) {
            return "Invalid price or capacity format!";
        }
    }

    
    public static List<EventPackage> getAllEventPackages() {
        return EventPackageDAO.getAllEventPackages();
    }

    
    public static EventPackage getEventPackageById(int packageId) {
        return EventPackageDAO.getEventPackageById(packageId);
    }

    
    public static List<EventPackage> searchEventPackages(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return getAllEventPackages();
        }
        return EventPackageDAO.searchEventPackages(searchTerm);
    }

    
    public static String updateEventPackage(int packageId, String packageName, String description, String price, String capacity) {
        
        if (!ValidationUtils.isNotEmpty(packageName)) {
            return "Package name cannot be empty!";
        }
        if (!ValidationUtils.isNotEmpty(description)) {
            return "Description cannot be empty!";
        }
        if (!ValidationUtils.isNotEmpty(price)) {
            return "Price cannot be empty!";
        }
        if (!ValidationUtils.isNotEmpty(capacity)) {
            return "Capacity cannot be empty!";
        }

        try {
            double priceValue = Double.parseDouble(price);
            int capacityValue = Integer.parseInt(capacity);

            if (priceValue <= 0) {
                return "Price must be greater than 0!";
            }
            if (capacityValue <= 0) {
                return "Capacity must be greater than 0!";
            }

            EventPackage pkg = new EventPackage(packageId, packageName, description, priceValue, capacityValue, "");
            if (EventPackageDAO.updateEventPackage(pkg)) {
                return "Event package updated successfully!";
            } else {
                return "Failed to update event package!";
            }
        } catch (NumberFormatException e) {
            return "Invalid price or capacity format!";
        }
    }

    
    public static String deleteEventPackage(int packageId) {
        if (EventPackageDAO.deleteEventPackage(packageId)) {
            return "Event package deleted successfully!";
        } else {
            return "Failed to delete event package!";
        }
    }
}
