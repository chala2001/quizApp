package com.example.quizzApp.Service;


import com.example.quizzApp.Entity.Question;
import com.example.quizzApp.Entity.Quiz;
import com.example.quizzApp.Repo.QuestionDao;
import com.example.quizzApp.Repo.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    // 1. Create a Quiz with random questions
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        // Fetch random questions using the DAO method we created
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        // Create the Quiz object
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        // Save to Database
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    // 2. Get a Quiz by ID
    public ResponseEntity<List<Question>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);

        // If the quiz exists, return its questions
        if(quiz.isPresent()){
            return new ResponseEntity<>(quiz.get().getQuestions(), HttpStatus.OK);
        }

        return new ResponseEntity<>(new java.util.ArrayList<>(), HttpStatus.NOT_FOUND);
    }
}
