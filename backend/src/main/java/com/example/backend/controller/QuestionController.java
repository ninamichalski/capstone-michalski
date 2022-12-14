package com.example.backend.controller;

import com.example.backend.model.Question;
import com.example.backend.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/random")
    public Question getRandomQuestion(){
        return questionService.getRandomQuestion();
    }

}
