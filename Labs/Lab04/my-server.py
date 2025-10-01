from flask import Flask, request

app = Flask(__name__)

@app.route("/")
def hello():
   return " you called \n"

# curl -d "text=Hello!&param2=value2" -X POST http://localhost:5000/echo
@app.route("/echo", methods=['POST'])
def echo():
   return "You said: " + request.form['text']

# AI Generated Code Below
def trial_division(n):
  factors = [] 
  # Handle 2 separately
  while n%2 == 0:
    factors.append(2)
    n //= 2

  # Check odd numbers up to sqrt(n)
  p = 3
  while p * p <= n:
    while n % p == 0:
      factors.append(p)
      n //= p
    p+=2

  if n>1:
    factors.append(n)
  return factors 

@app.route("/factor", methods=['POST'])
def factor():
  inINT = int(request.form['inINT'])
  factors = trial_division(inINT)
  return str(factors)

if __name__ == "__main__":
   app.run(host='0.0.0.0')