#!/bin/bash
# Event Booking System - Shell Script to Compile Project (Mac/Linux)
# This script compiles all Java files with MySQL JDBC driver in the classpath

echo ""
echo "============================================"
echo "Event Booking System - Compilation Script"
echo "============================================"
echo ""

# Check if lib directory exists
if [ ! -d "lib" ]; then
    echo "ERROR: lib directory not found!"
    echo "Please download mysql-connector-java jar and place it in the lib folder."
    exit 1
fi

# Create bin directory if it doesn't exist
if [ ! -d "bin" ]; then
    mkdir bin
    echo "Created bin directory"
fi

# Compile all Java files
echo "Compiling Java files with:" $MYSQL_JAR
javac -cp "$MYSQL_JAR:." -d bin src/Main.java src/models/*.java src/dao/*.java src/services/*.java src/ui/*.java src/utils/*.java

if [ $? -ne 0 ]; then
    echo "ERROR: Compilation failed!"
    exit 1
fi

echo ""
echo "✓ Compilation successful!"
echo ""
echo "To run the application, use: ./run.sh"
