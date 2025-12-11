import httpx

url = "http://localhost:5000/"

# gaenerate a token
print("Step 1: Generating a token...")
data = {"user_id": "peterparker@gmail.com"}
response = httpx.post(url + "generate_token", data=data)

print(f"Status Code: {response.status_code}")
print(f"Response: {response.text}\n")

if response.status_code == 200:
  token_data = response.json()
  token = token_data['token']
  print(f"Token generated successfully!")
  print(f"UUID: {token_data['uuid']}")
  print(f"User ID: {token_data['user_id']}\n")
  
  # verify the token
  print("Step 2: Verifying the token...")
  verify_data = {"token": token}
  verify_response = httpx.post(url + "verify_token", data=verify_data)
  
  print(f"Status Code: {verify_response.status_code}")
  print(f"Response: {verify_response.text}\n")
  
  if verify_response.status_code == 200:
    verify_result = verify_response.json()
    print(f"Token is valid: {verify_result['valid']}")
    print(f"UUID from token: {verify_result['uuid']}")
    print(f"User ID from token: {verify_result['user_id']}")
  else:
    print("Token verification failed!")
else:
  print("Failed to generate token!")