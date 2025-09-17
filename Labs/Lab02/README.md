# Commands to compile the code java files and the main file
cd /workspaces/CSE2102/Labs/Lab02/c && javac *.java
java PizzaShop

# Command to run the unit test:
cd /workspaces/CSE2102/Labs/Lab02 && java -cp .:c:junit-4.13.2.jar:hamcrest-core-1.3.jar:u org.junit.runner.JUnitCore PizzaFactoryTest