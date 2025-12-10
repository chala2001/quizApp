package com.example.quizzApp.Controller;

import com.example.quizzApp.Dto.QuizDto;
import com.example.quizzApp.Entity.Question;
import com.example.quizzApp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    // UPDATED: Now accepts a JSON Body using @RequestBody
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        // We extract data from the DTO and pass it to the service
        return quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNumQuestions(), quizDto.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<Question>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }
}
