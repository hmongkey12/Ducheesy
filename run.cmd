@echo off

rem   In this example, any third-party library JARs are located in 'lib' and added to the classpath [-classpath option].
rem   Remove this "lib\*" entry from the classpath if you're not using any, which results in the following:
rem   java -classpath blackjack-1.0.jar com.games.blackjack.client.Main

rem   Note that your application JAR stays on the classpath, regardless of whether you're using additional libraries or not.

reg add HKEY_CURRENT_USER\Console /v VirtualTerminalLevel /t REG_DWORD /d 0x00000001 /f
java -classpath out\production\Ducheesy;lib\app-utils-1.1.jar com.okieducky.client.Main