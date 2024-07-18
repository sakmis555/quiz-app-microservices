package com.saksham.questionservice.controller;

import com.saksham.questionservice.model.Question;
import com.saksham.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    };

    @GetMapping("{questionId}")
    public ResponseEntity<Question> getQuestion(@PathVariable int questionId) {
        return questionService.getQuestionById(questionId);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    };

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @PutMapping("update")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion(question);
    };

    @DeleteMapping("delete/{questionId}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable int questionId) {
        return questionService.deleteQuestion(questionId);
    }
}
