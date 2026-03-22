-- Event Booking System Database Schema
-- MySQL Database Setup Script

-- Create Database
CREATE DATABASE IF NOT EXISTS event_booking_system;
USE event_booking_system;

-- ===========================
-- USERS TABLE
-- ===========================
CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    phone VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===========================
-- ADMINS TABLE
-- ===========================
CREATE TABLE IF NOT EXISTS admins (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    admin_username VARCHAR(50) NOT NULL UNIQUE,
    admin_password VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===========================
-- EVENT PACKAGES TABLE
-- ===========================
CREATE TABLE IF NOT EXISTS event_packages (
    package_id INT PRIMARY KEY AUTO_INCREMENT,
    package_name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    capacity INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===========================
-- BOOKINGS TABLE
-- ===========================
CREATE TABLE IF NOT EXISTS bookings (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    package_id INT NOT NULL,
    event_date DATE NOT NULL,
    guest_count INT NOT NULL,
    special_requests TEXT,
    booking_status VARCHAR(20) DEFAULT 'Pending', -- Pending, Confirmed, Completed, Cancelled
    payment_status VARCHAR(20) DEFAULT 'Unpaid', -- Paid, Unpaid
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (package_id) REFERENCES event_packages(package_id) ON DELETE CASCADE
);

-- ===========================
-- SAMPLE DATA
-- ===========================

-- Insert Sample Users
INSERT INTO users (username, email, password, phone) VALUES
('john_doe', 'john@example.com', 'password123', '9876543210'),
('jane_smith', 'jane@example.com', 'password123', '9876543211'),
('alice_wilson', 'alice@example.com', 'password123', '9876543212'),
('bob_jones', 'bob@example.com', 'password123', '9876543213'),
('carol_davis', 'carol@example.com', 'password123', '9876543214');

-- Insert Sample Admin
INSERT INTO admins (admin_username, admin_password, email) VALUES
('admin1', 'admin123', 'admin@example.com');

-- Insert Sample Event Packages
INSERT INTO event_packages (package_name, description, price, capacity) VALUES
('Birthday Celebration', 'Complete birthday party package with decorations, cake, and entertainment', 5000.00, 50),
('Wedding Deluxe', 'Premium wedding package including venue, catering, and decoration', 50000.00, 500),
('Corporate Event', 'Professional corporate event setup with meeting facilities', 15000.00, 100),
('Anniversary Special', 'Romantic anniversary celebration with special arrangements', 8000.00, 30),
('Family Reunion', 'Family gathering package with food and entertainment', 10000.00, 200),
('Graduation Party', 'Celebration package for graduations with theme decoration', 6000.00, 75),
('Engagement Ceremony', 'Elegant engagement celebration with traditional setup', 12000.00, 150),
('Conference Package', 'Full corporate conference setup with A/V equipment', 25000.00, 300);

-- Insert Sample Bookings
INSERT INTO bookings (user_id, package_id, event_date, guest_count, special_requests, booking_status, payment_status) VALUES
(1, 1, '2026-04-15', 30, 'Please arrange chocolate cake', 'Confirmed', 'Paid'),
(2, 2, '2026-05-20', 250, 'Vegetarian options required', 'Confirmed', 'Unpaid'),
(3, 3, '2026-04-22', 80, 'Setup projector and sound system', 'Pending', 'Unpaid'),
(4, 4, '2026-06-10', 25, 'Romantic music playlist', 'Pending', 'Unpaid'),
(5, 5, '2026-05-05', 150, 'family games and activities', 'Completed', 'Paid');

-- Create Indexes for Better Performance
CREATE INDEX idx_user_bookings ON bookings(user_id);
CREATE INDEX idx_package_bookings ON bookings(package_id);
CREATE INDEX idx_booking_status ON bookings(booking_status);
CREATE INDEX idx_booking_date ON bookings(event_date);

-- Display Tables to Verify
SELECT 'Users Table:' as info;
SELECT COUNT(*) as user_count FROM users;

SELECT 'Event Packages Table:' as info;
SELECT COUNT(*) as package_count FROM event_packages;

SELECT 'Bookings Table:' as info;
SELECT COUNT(*) as booking_count FROM bookings;

SELECT 'Admins Table:' as info;
SELECT COUNT(*) as admin_count FROM admins;
