package com.saksham.questionservice.service;

import com.saksham.questionservice.dao.QuestionDao;
import com.saksham.questionservice.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    };

    public ResponseEntity<Question> getQuestionById(int questionId) {
        try {
            return new ResponseEntity<>(questionDao.findById(questionId).orElse(new Question()), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
    };

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findAllByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Question Added Successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Question Addition Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> updateQuestion(Question question) {
        try {
            questionDao.save(question);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getQuestionById(question.getId());
    }

    public ResponseEntity<Question> deleteQuestion(int questionId) {
        ResponseEntity<Question> question = null;
        try {
            question = getQuestionById(questionId);
            questionDao.deleteById(questionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return question;
    }
}
