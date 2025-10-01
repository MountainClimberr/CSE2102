import unittest

def trial_division(n):
  factors = []
  # Handle 2 separately
  while n % 2 == 0:
    factors.append(2)
    n //= 2
  
  # Check odd numbers up to sqrt(n)
  p = 3
  while p * p <= n:
    while n % p == 0:
      factors.append(p)
      n //= p
    p += 2
  
  if n > 1:
    factors.append(n)
  return factors


class TestTrialDivision(unittest.TestCase):
  def test_factor_12(self):
    result = trial_division(12)
    self.assertEqual(result, [2, 2, 3])
  
  def test_factor_7(self):
    result = trial_division(7)
    self.assertEqual(result, [7])

  def test_factor_360(self):
    result = trial_division(360)
    self.assertEqual(result, [2, 2, 2, 3, 3, 5])

  def test_factor_1(self):
    result = trial_division(1)
    self.assertEqual(result, [])

  def test_factor_13(self):
    result = trial_division(13)
    self.assertEqual(result, [13])

  def test_factor_2(self):
    result = trial_division(2)
    self.assertEqual(result, [2])

  def test_factor_100(self):
    result = trial_division(100)
    self.assertEqual(result, [2, 2, 5, 5])


if __name__ == '__main__':
  unittest.main()