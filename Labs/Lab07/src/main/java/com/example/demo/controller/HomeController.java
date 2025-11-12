package com.example.demo.controller;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.model.Count;
import com.example.demo.model.GetQuestion;
import com.example.demo.model.Greeting;
import com.example.demo.model.Score;
import com.example.demo.model.questions.QuestionTrueFalse;

@SessionAttributes({"count", "greeting", "score"})
@Controller 
public class HomeController { 

    @GetMapping("/") 
    public String home() { 
        return "home";
    } 

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        model.addAttribute("count", new Count());
        model.addAttribute("score", new Score());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, @ModelAttribute Count count, @ModelAttribute Score score, Model model) {
        count.count = 0;
        score.setCorrect(0);
        score.setTotal(0);
        
        model.addAttribute("greeting", greeting);
        model.addAttribute("count", count);
        model.addAttribute("score", score);

        return "redirect:/question";
    }

    @GetMapping("/question")
    public String showQuestion(@ModelAttribute("count") Count count, @ModelAttribute("greeting") Greeting greeting, @ModelAttribute("score") Score score, Model model) {       
        int totalQuestions = GetQuestion.getTotalQuestions();

        if (count.count >= totalQuestions) {
            return "redirect:/result";
        }
        GetQuestion getQuestion = new GetQuestion();
        QuestionTrueFalse currentQuestion = getQuestion.nextQuestion(count.count);
        
        model.addAttribute("question", currentQuestion);
        model.addAttribute("questionNumber", count.count + 1);
        model.addAttribute("totalQuestions", totalQuestions);
        
        return "question";
    }

    @PostMapping("/question")
    public String processAnswer(@ModelAttribute("count") Count count, @ModelAttribute("score") Score score, @RequestParam String answer, Model model) {
        GetQuestion getQuestion = new GetQuestion();
        QuestionTrueFalse currentQuestion = getQuestion.nextQuestion(count.count);
        Boolean answerBool = Boolean.valueOf(answer);
        score.incrementTotal();
        
        if (answerBool.equals(currentQuestion.getAnswer())) {
            score.incrementCorrect();
            System.out.println("Correct! Score: " + score.getCorrect() + "/" + score.getTotal());
        } else {
            System.out.println("Wrong! Score: " + score.getCorrect() + "/" + score.getTotal());
        }

        count.count++;
        
        model.addAttribute("count", count);
        model.addAttribute("score", score);
        
        return "redirect:/question";
    }

    @GetMapping("/result")
    public String showResult(@ModelAttribute("greeting") Greeting greeting, @ModelAttribute("score") Score score, Model model) {
        model.addAttribute("greeting", greeting);
        model.addAttribute("score", score);
        
        return "result";
    }
}