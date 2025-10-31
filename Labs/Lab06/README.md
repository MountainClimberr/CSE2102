# Open the Lab6 folder in two separate terminals
cd Labs/Lab06

# Command to run REST API
mvn spring-boot:run

# Command to add passwords and check quality 
curl -X POST -H "Content-Type: application/json" -d '{"password":"password123"}' http://localhost:8080/api/password-quality

# command to validate email
curl -X POST -H "Content-Type: application/json" -d '{"email":"test@email.com"}' http://localhost:8080/api/email-address-valid

# Command to run the unit test:
mvn test