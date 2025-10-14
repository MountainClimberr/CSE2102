import unittest
import json
from flaskHttpServer import app, subscribers

class TestFlaskHttpServer(unittest.TestCase):
  def setUp(self):
    self.app = app.test_client()
    self.app.testing = True
    subscribers.clear()
    
  def test_add_subscriber(self):
    response = self.app.post('/add-subscriber',
      data=json.dumps({'name': 'Chris', 'URI': 'http://chris.site.com'}),
      content_type='application/json')
    self.assertEqual(response.status_code, 200)
  
  def test_list_subscribers(self):
    response = self.app.get('/list-subscribers')
    self.assertEqual(response.status_code, 200)
  
  def test_delete_subscriber(self):
    self.app.post('/add-subscriber',
      data=json.dumps({'name': 'Mark', 'URI': 'http://mark.site.com'}),
      content_type='application/json')
    response = self.app.delete('/delete-subscriber',
      data=json.dumps({'name': 'Mark'}),
      content_type='application/json')
    self.assertEqual(response.status_code, 200)
  
  def test_publish(self):
    response = self.app.post('/publish',
      data=json.dumps({'subject': 'Test Update'}),
      content_type='application/json')
    self.assertEqual(response.status_code, 200)

if __name__ == '__main__':
  unittest.main()