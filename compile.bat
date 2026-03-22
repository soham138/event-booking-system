@echo off
REM Event Booking System - Batch Script to Compile Project (Windows)
REM This script compiles all Java files with MySQL JDBC driver in the classpath

echo.
echo ============================================
echo Event Booking System - Compilation Script
echo ============================================
echo.

REM Check if lib directory exists
if not exist "lib" (
    echo ERROR: lib directory not found!
    echo Please download mysql-connector-java jar and place it in the lib folder.
    pause
    exit /b 1
)

REM Create bin directory if it doesn't exist
if not exist "bin" (
    mkdir bin
    echo Created bin directory
)

REM Compile all Java files
echo Compiling Java files...
javac -cp lib\mysql-connector-j-9.6.0.jar;. -d bin src\Main.java src\models\*.java src\dao\*.java src\services\*.java src\ui\*.java src\utils\*.java

if %errorlevel% neq 0 (
    echo ERROR: Compilation failed!
    pause
    exit /b 1
)

echo.
echo ✓ Compilation successful!
echo.
echo To run the application, use: run.bat
pause
