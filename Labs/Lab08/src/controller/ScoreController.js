class ScoreController {
    constructor() {
        this.score = 0;
        this.count = 0;
    }

    // this is called when correct answer is clicked
    incrementScore() {
        this.score = this.score + 1;
        this.count = this.count + 1;
    }

    // this is called when wrong answer is clicked
    dontIncrementScore() {
        this.count = this.count + 1;
    }

    // this is called to get the current score
    getScore() {
        return this.score;
    }

    // this is called to get the current count
    getCount() {
        return this.count;
    }

    // this is called to reset everything
    reset() {
        this.score = 0;
        this.count = 0;
    }
}

export default ScoreController;
