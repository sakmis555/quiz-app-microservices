package com.saksham.questionservice.controller;

import com.saksham.questionservice.model.Question;
import com.saksham.questionservice.model.QuestionWrapper;
import com.saksham.questionservice.model.Response;
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
    };

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (@RequestParam String category, @RequestParam Integer numberOfQuestions) {
        return questionService.getQuestionsForQuiz(category, numberOfQuestions);
    };

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds) {
        return questionService.getQuestionsFromId(questionIds);
    };

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        return questionService.getScore(responses);
    }

}
