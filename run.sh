#!/bin/bash
# Event Booking System - Shell Script to Run Project (Mac/Linux)
# This script runs the Event Booking System

echo ""
echo "============================================"
echo "Event Booking System - Startup"
echo "============================================"
echo ""

# Check if bin directory exists
if [ ! -d "bin" ]; then
    echo "ERROR: bin directory not found!"
    echo "Please run ./compile.sh first to compile the project."
    exit 1
fi

# Find the MySQL JAR file
MYSQL_JAR=$(find lib -name "mysql-connector-j*.jar" | head -1)

if [ -z "$MYSQL_JAR" ]; then
    echo "WARNING: MySQL JDBC driver not found in lib folder!"
    echo "Application will fail to connect to database."
    echo ""
fi

echo "Starting Event Booking System..."
echo ""
java -cp "$MYSQL_JAR:bin" Main

if [ $? -ne 0 ]; then
    echo "ERROR: Application failed to start!"
    exit 1
fi
