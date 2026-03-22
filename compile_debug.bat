@echo off
cd /d "C:\Users\Soham\Desktop\Event Booking System"

REM Clean old compile
if exist bin\models (rmdir /s /q bin\models)
if exist bin\dao (rmdir /s /q bin\dao)
if exist bin\services (rmdir /s /q bin\services)
if exist bin\ui (rmdir /s /q bin\ui)
if exist bin\utils (rmdir /s /q bin\utils)

echo Compiling models...
javac -cp "lib\mysql-connector-j-9.6.0.jar" -d bin src\models\*.java 2>&1

if %ERRORLEVEL% neq 0 (
    echo ERROR in models!
    exit /b 1
)

echo Compiling utils...
javac -cp "lib\mysql-connector-j-9.6.0.jar" -d bin src\utils\*.java 2>&1

if %ERRORLEVEL% neq 0 (
    echo ERROR in utils!
    exit /b 1
)

echo Compiling dao...
javac -cp "lib\mysql-connector-j-9.6.0.jar;bin" -d bin src\dao\*.java 2>&1

if %ERRORLEVEL% neq 0 (
    echo ERROR in dao!
    exit /b 1
)

echo Compiling services...
javac -cp "lib\mysql-connector-j-9.6.0.jar;bin" -d bin src\services\*.java 2>&1

if %ERRORLEVEL% neq 0 (
    echo ERROR in services!
    exit /b 1
)

echo Compiling UI...
javac -cp "lib\mysql-connector-j-9.6.0.jar;bin" -d bin src\ui\*.java 2>&1

if %ERRORLEVEL% neq 0 (
    echo ERROR in UI!
    exit /b 1
)

echo Compiling Main...
javac -cp "lib\mysql-connector-j-9.6.0.jar;bin" -d bin src\Main.java 2>&1

if %ERRORLEVEL% neq 0 (
    echo ERROR in Main!
    exit /b 1
)

echo.
echo ✓ Compilation successful!
pause
