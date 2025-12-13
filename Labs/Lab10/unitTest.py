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

def test_protected_with_token(token):
  print("\n3. Testing protected endpoint WITH valid token")
  
  headers = {"Authorization": f"Bearer {token}"}
  response = httpx.get(url + "protected_data", headers=headers)
  
  if response.status_code == 200:
    print("Access granted with valid token")
  else:
    print("Test failed!")

def test_protected_without_token():
  print("\n2. Testing protected endpoint WITHOUT token")
  
  response = httpx.get(url + "protected_data")
  
  if response.status_code == 401:
    print("Access correctly denied without token")
  else:
    print("Test failed!")

def test_stats():
  print("\n4. Testing stats endpoint")
  
  response = httpx.get(url + "stats")
  
  if response.status_code == 200:
    stats = response.json()
    print("Stats retrieved successfully")
    print(f"Tokens generated: {stats['tokens_generated']}")
    print(f"Protected accesses: {stats['protected_accesses']}")
  else:
    print("Test failed!")

if __name__ == "__main__":
  print("Testing JWT Protected Application")
  print("-" * 30)
  
  # run tests
  token = test_generate_token()
  if token:
    test_protected_without_token()
    test_protected_with_token(token)
    test_stats()
  
  print("\n" + "-" * 30)
  print("Tests complete!")