package com.saksham.questionservice.service;

import com.saksham.questionservice.dao.QuestionDao;
import com.saksham.questionservice.model.Question;
import com.saksham.questionservice.model.QuestionWrapper;
import com.saksham.questionservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numberOfQuestions) {
        List<Integer> questions = questionDao.findRandomQuestionsByCategory(category, numberOfQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();

        List<Question> questions = new ArrayList<>();
        for(Integer questionId : questionIds) {
            questions.add(questionDao.findById(questionId).get());
        };

        for(Question question : questions) {
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int rightAnswerCount = 0;
        for( Response response: responses) {
            Question question = questionDao.findById(response.getId()).get();
            if(response.getResponse().equals( question.getRightAnswer())){
                rightAnswerCount++;
            }
        }
        return new ResponseEntity<>(rightAnswerCount, HttpStatus.OK);
    }
}
