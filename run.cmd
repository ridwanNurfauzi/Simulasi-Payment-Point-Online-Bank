@ECHO OFF

@REM light theme
@REM color f0

cd src
javac -d ../build app/App.java

cd ../build
java app.App
