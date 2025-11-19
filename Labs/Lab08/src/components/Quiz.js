import React from 'react';
import quizPageStyle from '../QuizPageStyle';
import ScoreController from '../controller/ScoreController';
import my_state from './my_state';
import my_questions from '../model/basic_questions.json';

class Quiz extends React.Component {

    constructor(props) {
        super(props);
        this.scoreController = new ScoreController();
        
        this.state = {
            score: 0,
            count: 0,
            showResults: false,
            answeredQuestions: []
        };
    }
    
    incrementScore = (questionId) => {
        // Check if this question was already answered
        if (this.state.answeredQuestions.includes(questionId)) {
            return;
        }
        
        this.scoreController.incrementScore();
        
        this.setState({
            score: this.scoreController.getScore(),
            count: this.scoreController.getCount(),
            answeredQuestions: [...this.state.answeredQuestions, questionId]
        });
        alert("You are correct!"); // could be executed before the setStates are done!
    };

    dontIncrementScore = (questionId) => {
        if (this.state.answeredQuestions.includes(questionId)) {
            return;
        }
        
        this.scoreController.dontIncrementScore();
        
        this.setState({
            score: this.scoreController.getScore(),
            count: this.scoreController.getCount(),
            answeredQuestions: [...this.state.answeredQuestions, questionId]
        });
        alert("Sorry - not correct");
    };

    handleSubmit = () => {
        my_state.my_score = this.state.score;
        my_state.my_count = this.state.count;
        
        alert("Total score: " + this.state.score + "/" + this.state.count);
        
        // Show the results page
        this.setState({ showResults: true });
    }

    handleRetake = () => {
        // Reset the controller
        this.scoreController.reset();
        
        // Reset state
        this.setState({
            score: 0,
            count: 0,
            showResults: false,
            answeredQuestions: []
        });
    }
    
    render() {
        // If showing results, show the Results component
        if (this.state.showResults) {
            return (
                <div style={quizPageStyle}>
                    <h1>Quiz Results</h1>
                    <h2>Your Score: {this.state.score} / {this.state.count}</h2>
                    <p>You got {this.state.score} questions correct out of {this.state.count} total questions.</p>
                    <button onClick={this.handleRetake}>Take Quiz Again</button>
                </div>
            );
        }

        return(
           <div style={quizPageStyle}>
            <h1>My Questions</h1>
                {my_questions.map((quest) => (
                <div id="outer-div" key={quest.id}> 
                    <h2>{quest["question"]}</h2>
                        {quest["answers"].map((ans, index) => (
                            <div id="mid-div" key={index}>
                                <label>
                                <input  
                                        type = "radio"
                                        name = { quest["id"] }
                                        onClick = { ans["isCorrect"] ? () => this.incrementScore(quest["id"]) : () => this.dontIncrementScore(quest["id"]) }
                                        value = { ans["isCorrect"] } /> 
                                    { ans["answer"] }
                                </label> 
                                <br />
                            </div>
                        ))}
                    
                </div>
                ))}
                 <br />
                <button onClick={this.handleSubmit} >Done</button>
        </div>
        );
    }
}

export default Quiz;