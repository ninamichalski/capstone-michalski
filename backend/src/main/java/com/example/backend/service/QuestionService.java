package com.example.backend.service;

import com.example.backend.model.Question;
import com.example.backend.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getRandomQuestion() {
        List<Question> random = questionRepository.findRandom();
        return random.get(0);
    }
}
