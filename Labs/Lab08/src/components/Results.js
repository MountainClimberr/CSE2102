import React from 'react';
import quizPageStyle from '../QuizPageStyle';
import my_state from './my_state';

class Results extends React.Component {
    
    render() {
        return(
            <div style={quizPageStyle}>
                <h1>Quiz Results</h1>
                <h2>Your Score: {my_state.my_score} / {my_state.my_count}</h2>
                <p>You got {my_state.my_score} questions correct out of {my_state.my_count} total questions.</p>
                <button onClick={() => window.location.href = '/'}>Take Quiz Again</button>
            </div>
        );
    }
}

export default Results;
