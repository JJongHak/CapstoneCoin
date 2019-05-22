@echo off
rem START or STOP Services
rem ----------------------------------
rem Check if argument is STOP or START

if not ""%1"" == ""START"" goto stop

if exist I:\CapstoneDesign\Server\xampp\hypersonic\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\server\hsql-sample-database\scripts\ctl.bat START)
if exist I:\CapstoneDesign\Server\xampp\ingres\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\ingres\scripts\ctl.bat START)
if exist I:\CapstoneDesign\Server\xampp\mysql\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\mysql\scripts\ctl.bat START)
if exist I:\CapstoneDesign\Server\xampp\postgresql\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\postgresql\scripts\ctl.bat START)
if exist I:\CapstoneDesign\Server\xampp\apache\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\apache\scripts\ctl.bat START)
if exist I:\CapstoneDesign\Server\xampp\openoffice\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\openoffice\scripts\ctl.bat START)
if exist I:\CapstoneDesign\Server\xampp\apache-tomcat\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\apache-tomcat\scripts\ctl.bat START)
if exist I:\CapstoneDesign\Server\xampp\resin\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\resin\scripts\ctl.bat START)
if exist I:\CapstoneDesign\Server\xampp\jboss\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\jboss\scripts\ctl.bat START)
if exist I:\CapstoneDesign\Server\xampp\jetty\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\jetty\scripts\ctl.bat START)
if exist I:\CapstoneDesign\Server\xampp\subversion\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\subversion\scripts\ctl.bat START)
rem RUBY_APPLICATION_START
if exist I:\CapstoneDesign\Server\xampp\lucene\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\lucene\scripts\ctl.bat START)
if exist I:\CapstoneDesign\Server\xampp\third_application\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\third_application\scripts\ctl.bat START)
goto end

:stop
echo "Stopping services ..."
if exist I:\CapstoneDesign\Server\xampp\third_application\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\third_application\scripts\ctl.bat STOP)
if exist I:\CapstoneDesign\Server\xampp\lucene\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\lucene\scripts\ctl.bat STOP)
rem RUBY_APPLICATION_STOP
if exist I:\CapstoneDesign\Server\xampp\subversion\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\subversion\scripts\ctl.bat STOP)
if exist I:\CapstoneDesign\Server\xampp\jetty\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\jetty\scripts\ctl.bat STOP)
if exist I:\CapstoneDesign\Server\xampp\hypersonic\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\server\hsql-sample-database\scripts\ctl.bat STOP)
if exist I:\CapstoneDesign\Server\xampp\jboss\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\jboss\scripts\ctl.bat STOP)
if exist I:\CapstoneDesign\Server\xampp\resin\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\resin\scripts\ctl.bat STOP)
if exist I:\CapstoneDesign\Server\xampp\apache-tomcat\scripts\ctl.bat (start /MIN /B /WAIT I:\CapstoneDesign\Server\xampp\apache-tomcat\scripts\ctl.bat STOP)
if exist I:\CapstoneDesign\Server\xampp\openoffice\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\openoffice\scripts\ctl.bat STOP)
if exist I:\CapstoneDesign\Server\xampp\apache\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\apache\scripts\ctl.bat STOP)
if exist I:\CapstoneDesign\Server\xampp\ingres\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\ingres\scripts\ctl.bat STOP)
if exist I:\CapstoneDesign\Server\xampp\mysql\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\mysql\scripts\ctl.bat STOP)
if exist I:\CapstoneDesign\Server\xampp\postgresql\scripts\ctl.bat (start /MIN /B I:\CapstoneDesign\Server\xampp\postgresql\scripts\ctl.bat STOP)

:end

