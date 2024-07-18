package com.saksham.quizapp.controller;

import com.saksham.quizservice.model.QuestionWrapper;
import com.saksham.quizservice.model.Quiz;
import com.saksham.quizservice.model.Response;
import com.saksham.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        return quizService.createQuiz(category,numQ,title);
    };

    @GetMapping("get/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int quizId) {
        return quizService.getQuizQuestions(quizId);
    };

    @PostMapping("submit/{quizId}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int quizId, @RequestBody List<Response> response) {
        return quizService.calculateResult(quizId, response);
    }
}
