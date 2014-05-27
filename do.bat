setlocal
set PATH=%PATH%;C:\Program Files (x86)\Arduino\
"C:\Program Files (x86)\Java\jdk1.7.0_25\bin\javac" -cp "C:\Program Files (x86)\Arduino\lib\RXTXcomm.jar" SerialTest.java
"C:\Program Files (x86)\Java\jdk1.7.0_25\bin\java" -cp "C:\Program Files (x86)\Arduino\lib\RXTXcomm.jar;." SerialTest