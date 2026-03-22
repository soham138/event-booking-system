package models;

public class Admin {
    private int adminId;
    private String adminUsername;
    private String adminPassword;
    private String email;
    private String createdAt;

    
    public Admin(int adminId, String adminUsername, String adminPassword, String email, String createdAt) {
        this.adminId = adminId;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.email = email;
        this.createdAt = createdAt;
    }

    
    public Admin(String adminUsername, String adminPassword, String email) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.email = email;
    }

    
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminUsername='" + adminUsername + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
