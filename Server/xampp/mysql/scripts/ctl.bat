@echo off
rem START or STOP Services
rem ----------------------------------
rem Check if argument is STOP or START

if not ""%1"" == ""START"" goto stop


"I:\CapstoneDesign\Server\xampp\mysql\bin\mysqld" --defaults-file="I:\CapstoneDesign\Server\xampp\mysql\bin\my.ini" --standalone --console
if errorlevel 1 goto error
goto finish

:stop
cmd.exe /C start "" /MIN call "I:\CapstoneDesign\Server\xampp\killprocess.bat" "mysqld.exe"

if not exist "I:\CapstoneDesign\Server\xampp\mysql\data\%computername%.pid" goto finish
echo Delete %computername%.pid ...
del "I:\CapstoneDesign\Server\xampp\mysql\data\%computername%.pid"
goto finish


:error
echo MySQL could not be started

:finish
exit
