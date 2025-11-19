import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import App from './App';

test('renders My Questions', () => {
  render(<App />);
  const linkElement = screen.getByText(/My Questions/i);
  expect(linkElement).toBeInTheDocument();
});


test('renders Capital question', () => {
  render(<App />);
  const linkElement = screen.getByText(/What is the capital of Connecticut?/i);
  expect(linkElement).toBeInTheDocument();
});

test('selects the "True" radio button', async () => {
  render(<App />);

  //const trueRadio = screen.getByRole('radio', { name: /true/i });
  const hartfordRadio = screen.getByRole('radio', { name: /Hartford/i });
  await userEvent.click(hartfordRadio);

  expect(hartfordRadio).toBeChecked();
});

test('What type of number is 101?', async () => {
  render(<App />);

  //const trueRadio = screen.getByRole('radio', { name: /true/i });
  const primeRadio = screen.getByRole('radio', { name: /Prime/i });
  await userEvent.click(primeRadio);

  expect(primeRadio).toBeChecked();
});



test('shows alert when the <4> radio button is clicked', async () => {
  
  // Mock window.alert
  const alertMock = jest.spyOn(window, 'alert').mockImplementation(() => {});

  render(<App />);

  const fourRadioButton = screen.getByRole('radio', { name: /4/i });
  await userEvent.click(fourRadioButton);

  expect(alertMock).toHaveBeenCalledTimes(1);
  expect(alertMock).toHaveBeenCalledWith('You are correct!');

  alertMock.mockRestore(); // clean up
});
