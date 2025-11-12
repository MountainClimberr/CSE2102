package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.model.questions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class ModelTests {

    @Test
    public void testQuestionTrueFalseCreation() {
        QuestionTrueFalse q = new QuestionTrueFalse("Test question?", true);
        assertEquals("Test question?", q.getQuestion());
        assertEquals(true, q.getAnswer());
    }

    @Test
    public void testArrayHasFiveQuestions() {
        ArrayQuestionsTrueFalse array = new ArrayQuestionsTrueFalse();
        assertNotNull(array.nextQuestion(0));
        assertNotNull(array.nextQuestion(4));
    }

    @Test
    public void testGetQuestionReturnsQuestion() {
        GetQuestion gq = new GetQuestion();
        QuestionTrueFalse q = gq.nextQuestion(0);
        assertNotNull(q);
        assertNotNull(q.getQuestion());
    }

    @Test
    public void testGetTotalQuestions() {
        assertEquals(5, GetQuestion.getTotalQuestions());
    }

    @Test
    public void testCountInitialization() {
        Count count = new Count();
        assertEquals(0, count.count);
    }

    @Test
    public void testGreetingName() {
        Greeting greeting = new Greeting();
        greeting.setName("TestUser");
        assertEquals("TestUser", greeting.getName());
    }

    @Test
    public void testScoreInitialization() {
        Score score = new Score();
        assertEquals(0, score.getCorrect());
        assertEquals(0, score.getTotal());
    }

    @Test
    public void testScoreIncrement() {
        Score score = new Score();
        score.incrementCorrect();
        score.incrementTotal();
        assertEquals(1, score.getCorrect());
        assertEquals(1, score.getTotal());
    }

    @Test
    public void testScorePercentage() {
        Score score = new Score();
        score.setCorrect(4);
        score.setTotal(5);
        assertEquals(80.0, score.getPercentage(), 0.01);
    }

    @Test
    public void testAllQuestionsHaveText() {
        GetQuestion gq = new GetQuestion();
        for (int i = 0; i < GetQuestion.getTotalQuestions(); i++) {
            QuestionTrueFalse q = gq.nextQuestion(i);
            assertNotNull(q.getQuestion());
            assertFalse(q.getQuestion().isEmpty());
        }
    }

    @Test
    public void testAllQuestionsHaveAnswers() {
        GetQuestion gq = new GetQuestion();
        for (int i = 0; i < GetQuestion.getTotalQuestions(); i++) {
            QuestionTrueFalse q = gq.nextQuestion(i);
            assertNotNull(q.getAnswer());
        }
    }
}
