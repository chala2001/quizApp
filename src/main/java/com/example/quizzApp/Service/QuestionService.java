package com.example.quizzApp.Service;


import com.example.quizzApp.Entity.Question;
import com.example.quizzApp.Repo.QuestionDao;
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

    // 1. Fetch all questions
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    // 2. Fetch a question by its ID
    public ResponseEntity<Optional<Question>> getQuestionById(Integer id) {
        try {
            return new ResponseEntity<>(questionDao.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 3. Add a new question
    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    // 4. Delete a question by its ID
    public ResponseEntity<String> deleteQuestion(Integer id) {
        questionDao.deleteById(id);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
}
