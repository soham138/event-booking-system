@echo off
REM Event Booking System - Batch Script to Run Project (Windows)
REM This script runs the Event Booking System

echo.
echo ============================================
echo Event Booking System - Startup
echo ============================================
echo.

REM Check if bin directory exists
if not exist "bin" (
    echo ERROR: bin directory not found!
    echo Please run compile.bat first to compile the project.
    pause
    exit /b 1
)

REM Check if MySQL JDBC jar exists
if not exist "lib\mysql-connector-j-9.6.0.jar" (
    echo WARNING: MySQL JDBC driver not found in lib folder!
    echo Application will fail to connect to database.
    echo.
)

echo Starting Event Booking System...
echo.
java -cp lib\mysql-connector-j-9.6.0.jar;bin Main

if %errorlevel% neq 0 (
    echo ERROR: Application failed to start!
    pause
    exit /b 1
)

pause
