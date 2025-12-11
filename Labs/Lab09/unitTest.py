import httpx

# Update this to match your server URL
url = "http://localhost:5000/"

def test_generate_token():
  print("\n1. Testing token generation")
  
  data = {"user_id": "test@example.com"}
  response = httpx.post(url + "generate_token", data=data)
  
  if response.status_code == 200:
    print("Token generated successfully")
    return response.json()["token"]
  else:
    print("Test failed!")
    return None

def test_verify_token(token):
    print("\n2. Testing token verification")
    
    data = {"token": token}
    response = httpx.post(url + "verify_token", data=data)
    
    if response.status_code == 200 and response.json()["valid"]:
      print("Token verified successfully")
    else:
      print("Test failed!")

if __name__ == "__main__":
    print("Testing Token Service")
    print("-" * 30)
    
    # Run tests
    token = test_generate_token()
    if token:
      test_verify_token(token)
    
    print("\n" + "-" * 30)
    print("Tests complete!")