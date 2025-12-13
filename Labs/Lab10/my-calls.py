import httpx

url = "http://localhost:5000/"

# generate a token
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
  
  # Step 2: trying to access protected data without token
  print("Step 2: Trying to access protected data without token...")
  response = httpx.get(url + "protected_data")
  
  print(f"Status Code: {response.status_code}")
  print(f"Response: {response.text}\n")
  
  # Step 3: accessing protected data with valid token
  print("Step 3: Accessing protected data with valid token...")
  headers = {"Authorization": f"Bearer {token}"}
  response = httpx.get(url + "protected_data", headers=headers)
  
  print(f"Status Code: {response.status_code}")
  print(f"Response: {response.text}\n")
  
  if response.status_code == 200:
    result = response.json()
    print(f"Access granted!")
    print(f"Message: {result['message']}")
    print(f"Protected data: {result['data']}\n")
  else:
    print("Access denied!\n")
  
  # Step 4: viewing usage statistics
  print("Step 4: Viewing usage statistics...")
  response = httpx.get(url + "stats")
  
  print(f"Status Code: {response.status_code}")
  if response.status_code == 200:
    stats_data = response.json()
    print("\nUsage Statistics:")
    print(f"  Tokens Generated: {stats_data['tokens_generated']}")
    print(f"  Successful Verifications: {stats_data['successful_verifications']}")
    print(f"  Failed Attempts: {stats_data['failed_attempts']}")
    print(f"  Protected Accesses: {stats_data['protected_accesses']}")
else:
  print("Failed to generate token!")