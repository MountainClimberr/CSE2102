package com.example.demo;

import com.example.demo.model.Greeting;
import com.example.demo.model.Count;
import com.example.demo.model.Score;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockHttpSession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WebsiteApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomePageLoads() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    public void testGreetingPageLoads() throws Exception {
        mockMvc.perform(get("/greeting"))
                .andExpect(status().isOk())
                .andExpect(view().name("greeting"))
                .andExpect(model().attributeExists("greeting"))
                .andExpect(model().attributeExists("count"))
                .andExpect(model().attributeExists("score"));
    }

    @Test
    public void testQuestionPageLoads() throws Exception {
        Greeting greeting = new Greeting();
        greeting.setName("TestUser");
        Count count = new Count();
        Score score = new Score();
        
        mockMvc.perform(get("/question")
                .sessionAttr("greeting", greeting)
                .sessionAttr("count", count)
                .sessionAttr("score", score))
                .andExpect(status().isOk())
                .andExpect(view().name("question"))
                .andExpect(model().attributeExists("question"));
    }

    @Test
    public void testQuestionSubmission() throws Exception {
        Count count = new Count();
        Score score = new Score();
        
        mockMvc.perform(post("/question")
                .param("answer", "true")
                .sessionAttr("count", count)
                .sessionAttr("score", score))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/question"));
    }

    @Test
    public void testResultPage() throws Exception {
        Greeting greeting = new Greeting();
        greeting.setName("TestUser");
        Score score = new Score();
        score.setCorrect(4);
        score.setTotal(5);
        
        mockMvc.perform(get("/result")
                .sessionAttr("greeting", greeting)
                .sessionAttr("score", score))
                .andExpect(status().isOk())
                .andExpect(view().name("result"));
    }

    @Test
    public void testCompleteQuizFlow() throws Exception {
        MockHttpSession session = new MockHttpSession();
        // Step 1: get greeting page
        mockMvc.perform(get("/greeting").session(session))
                .andExpect(status().isOk());
        
        // Step 2: submite name
        mockMvc.perform(post("/greeting")
                .param("name", "TestUser")
                .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/question"));
        
        // Step 3: view first question
        mockMvc.perform(get("/question").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("question"));
        
        // Step 4: answer the first question
        mockMvc.perform(post("/question")
                .param("answer", "true")
                .session(session))
                .andExpect(status().is3xxRedirection());
    }
}