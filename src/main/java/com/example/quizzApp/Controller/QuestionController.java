package com.example.quizzApp.Controller;


import com.example.quizzApp.Entity.Question;
import com.example.quizzApp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    // 1. Fetch all questions
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    // 2. Fetch a question by its ID
    @GetMapping("{id}")
    public ResponseEntity<Optional<Question>> getQuestionById(@PathVariable Integer id) {
        return questionService.getQuestionById(id);
    }

    // 3. Add a new question
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    // 4. Delete a question by its ID
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        return questionService.deleteQuestion(id);
    }
}
