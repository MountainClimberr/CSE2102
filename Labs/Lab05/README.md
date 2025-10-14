# Open the Lab4 folder in two separate terminals
cd Labs/Lab05

# Command to run flashHttpServer
python3 flaskHttpServer.py

# Command to add subrscirbers 
curl -X POST -H "Content-Type: application/json" -d "{\"name\":\"Marlon\",\"URI\":\"http://marlon.site.com\"}" http://localhost:5000/add-subscriber

curl -X POST -H "Content-Type: application/json" -d "{\"name\":\"Chris\",\"URI\":\"http://chris.site.com\"}" http://localhost:5000/add-subscriber

curl -X POST -H "Content-Type: application/json" -d "{\"name\":\"Cena\",\"URI\":\"http://cena.site.com\"}" http://localhost:5000/add-subscriber

# command to delete subscriber 
curl -X DELETE -H "Content-Type: application/json" -d "{\"name\":\"Chris\"}" http://localhost:5000/delete-subscriber

# command to list subscribers 
curl http://localhost:5000/list-subscribers

# command to publish an update
curl -X POST -H "Content-Type: application/json" -d "{\"subject\":\"New Update Available\"}" http://localhost:5000/publish

# Command to run the unit test:
python3 testflask.py