# Commands to compile the code java files and the main file
javac c/*.java
java c.carRunner

# Commands to run the unit test:
javac -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar c/*.java u/HybridTest.java
java -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore u.HybridTest