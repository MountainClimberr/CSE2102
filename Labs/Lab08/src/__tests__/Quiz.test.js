import React from 'react';
import { render, screen } from '@testing-library/react';
import Quiz from '../components/Quiz';

test('Quiz renders without crashing', () => {
  render(<Quiz />);
  const heading = screen.getByText(/My Questions/i);
  expect(heading).toBeInTheDocument();
});

test('Quiz shows questions', () => {
  render(<Quiz />);
  const question1 = screen.getByText(/capital of Connecticut/i);
  expect(question1).toBeInTheDocument();
});
