from flask import Flask, request, jsonify
import jwt
import uuid
from datetime import datetime, timedelta

app = Flask(__name__)

SECRET_KEY = "secret"

app = Flask(__name__)

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
    "exp": expiration_time
  }
  token = jwt.encode(payload, SECRET_KEY, algorithm="HS256")
    
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
    
    return jsonify({
      "valid": True,
      "uuid": decoded.get("jti"),
      "user_id": decoded.get("user_id"),
      "message": "Token is valid"
    })
  except jwt.ExpiredSignatureError:
    return jsonify({
      "valid": False,
      "error": "Token has expired"
    }), 401
  except jwt.InvalidTokenError:
    return jsonify({
      "valid": False,
      "error": "Invalid token"
    }), 401

if __name__ == "__main__":
   app.run(host='0.0.0.0')