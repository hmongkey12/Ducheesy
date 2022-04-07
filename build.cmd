@echo off

rem   Compile the application code [*not* the test code].
rem   Compiled .class files are placed in a new subdirectory called 'classes' [-d option].

rem   In this example, any third-party library JARs are located in 'lib' and added to the classpath [-classpath option].
rem   Remove this -classpath "lib\*" if you're not using any, which results in the following:
rem   javac -d classes src\com\games\blackjack\client\*.java src\com\games\blackjack\controller\*.java src\com\games\blackjack\domain\*.java

javac -d classes -classpath "lib\*" src\com\okieducky\client\*.java src\com\okieducky\games\*.java


rem   Build the application JAR.
rem   This example adds the directory tree of .class files starting at 'classes'.
rem   Note that any third-party library JARs are *not* included in your application JAR, 
rem   nor are any resource files, e.g., config files, data files, banner.txt, etc.

jar --create --file ducheesy-1.0.jar -C classes .


rem   Create the Javadoc.
rem   The '-package' flag will include non-public classes and all class members except for private ones.
rem   Again, any third-party library JARs are located in 'lib' and added to the classpath.
rem   And again, remove this -classpath "lib\*" if you're not using any.

rem   Running this only makes sense if you've taken the time to write API comments in your code.
rem   API (javadoc) comments are important when the "product" is a library for other developers to use.
rem   It's not as important when the "product" is a finished application, like with your project.
rem   Just leave this commented out if not using.

javadoc -d doc --source-path src -classpath "lib\*" -package -windowtitle Ducheesy com.okieducky.client com.okieducky.games