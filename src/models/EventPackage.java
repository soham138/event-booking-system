package models;

public class EventPackage {
    private int packageId;
    private String packageName;
    private String description;
    private double price;
    private int capacity;
    private String createdAt;

    
    public EventPackage(int packageId, String packageName, String description, double price, int capacity, String createdAt) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.description = description;
        this.price = price;
        this.capacity = capacity;
        this.createdAt = createdAt;
    }

    
    public EventPackage(String packageName, String description, double price, int capacity) {
        this.packageName = packageName;
        this.description = description;
        this.price = price;
        this.capacity = capacity;
    }

    
    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "EventPackage{" +
                "packageId=" + packageId +
                ", packageName='" + packageName + '\'' +
                ", price=" + price +
                ", capacity=" + capacity +
                '}';
    }
}
