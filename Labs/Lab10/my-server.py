from flask import Flask, request, jsonify
import jwt
import uuid
from datetime import datetime, timedelta

app = Flask(__name__)

SECRET_KEY = "secret"

# a token stats tracker
class TokenStats:
  def __init__(self):
    self.total_generated = 0
    self.total_verified = 0
    self.failed_attempts = 0
    self.protected_accesses = 0
  
  def record_generation(self):
    self.total_generated += 1
  
  def record_verification(self, success):
    if success:
      self.total_verified += 1
    else:
      self.failed_attempts += 1
  
  def record_protected_access(self):
    self.protected_accesses += 1
  
  def get_stats(self):
    return {
      "tokens_generated": self.total_generated,
      "successful_verifications": self.total_verified,
      "failed_attempts": self.failed_attempts,
      "protected_accesses": self.protected_accesses
    }
    
stats = TokenStats()    

@app.route("/")
def hello():
  return " you called \n"

# curl -d "text=Hello!&param2=value2" -X POST http://localhost:5000/echo
@app.route("/echo", methods=['POST'])
def echo():
  return "You said: " + request.form['text']

# endpoint to generate a token
@app.route("/generate_token", methods=['POST'])
def generate_token():
  user_id = request.form.get('user_id', 'default_user')
  token_uuid = str(uuid.uuid1())

  expiration_time = datetime.now() + timedelta(hours=9)
    
  payload = {
    "jti": token_uuid,
    "user_id": user_id,
    "exp": int(expiration_time.timestamp())
  }
  token = jwt.encode(payload, SECRET_KEY, algorithm="HS256")

  stats.record_generation()
    
  return jsonify({
    "token": token,
    "uuid": token_uuid,
    "user_id": user_id
  })

# endpoint to verify a token
@app.route("/verify_token", methods=['POST'])
def verify_token():
  token = request.form.get('token')
  
  if not token:
    return jsonify({"error": "No token provided"}), 400
  try:
    decoded = jwt.decode(token, SECRET_KEY, algorithms=["HS256"])
    
    stats.record_verification(success=True)
    
    return jsonify({
      "valid": True,
      "uuid": decoded.get("jti"),
      "user_id": decoded.get("user_id"),
      "message": "Token is valid"
    })
  except jwt.ExpiredSignatureError:
    stats.record_verification(success=False)
    return jsonify({
      "valid": False,
      "error": "Token has expired"
    }), 401
  except jwt.InvalidTokenError:
    stats.record_verification(success=False)
    return jsonify({
      "valid": False,
      "error": "Invalid token"
    }), 401
  
# protected endpoint
@app.route("/protected_data", methods=['GET'])
def protected_data():
  auth_header = request.headers.get('Authorization')
  
  if not auth_header:
    return jsonify({"error": "No token provided"}), 401
  try:
    token = auth_header.split(" ")[1]
  except:
    return jsonify({"error": "Invalid token format"}), 401
  try:
    decoded = jwt.decode(token, SECRET_KEY, algorithms=["HS256"])
    
    stats.record_protected_access()
    
    return jsonify({
      "message": "Access granted!",
      "user_id": decoded.get("user_id"),
      "data": "This is protected information only visible with a valid token"
    })
  except jwt.ExpiredSignatureError:
    return jsonify({"error": "Token has expired"}), 401
  except jwt.InvalidTokenError:
    return jsonify({"error": "Invalid token"}), 401
  
# stats endpoint 
@app.route("/stats", methods=['GET'])
def get_stats():
  return jsonify(stats.get_stats())

if __name__ == "__main__":
  app.run(host='0.0.0.0')