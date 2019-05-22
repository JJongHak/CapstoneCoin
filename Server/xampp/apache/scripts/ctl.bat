@echo off

if not ""%1"" == ""START"" goto stop

cmd.exe /C start /B /MIN "" "I:\CapstoneDesign\Server\xampp\apache\bin\httpd.exe"

if errorlevel 255 goto finish
if errorlevel 1 goto error
goto finish

:stop
cmd.exe /C start "" /MIN call "I:\CapstoneDesign\Server\xampp\killprocess.bat" "httpd.exe"

if not exist "I:\CapstoneDesign\Server\xampp\apache\logs\httpd.pid" GOTO finish
del "I:\CapstoneDesign\Server\xampp\apache\logs\httpd.pid"
goto finish

:error
echo Error starting Apache

:finish
exit
