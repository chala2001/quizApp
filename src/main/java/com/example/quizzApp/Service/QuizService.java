package com.example.quizzApp.Service;


import com.example.quizzApp.Dto.QuestionWrapper;
import com.example.quizzApp.Entity.Question;
import com.example.quizzApp.Entity.Quiz;
import com.example.quizzApp.Entity.Response;
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
    // Change return type to List<QuestionWrapper>
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);

        if (quiz.isPresent()) {
            // Get the questions from the DB
            List<Question> questionsFromDB = quiz.get().getQuestions();

            // Create a new empty list for the user
            List<QuestionWrapper> questionsForUser = new java.util.ArrayList<>();

            // Convert each Question -> QuestionWrapper (hiding the answer)
            for(Question q : questionsFromDB){
                QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                questionsForUser.add(qw);
            }

            return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
        }

        return new ResponseEntity<>(new java.util.ArrayList<>(), HttpStatus.NOT_FOUND);
    }

    // 3. Calculate Score
    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        // 1. Fetch the Quiz from DB
        Optional<Quiz> quiz = quizDao.findById(id);

        // 2. Retrieve the questions from that quiz
        List<Question> questions = quiz.get().getQuestions();

        int right = 0; // Initialize score
        int i = 0; // Index for iterating

        // 3. Compare User's Response with Correct Answer
        for(Response response : responses){
            // Ensure we are checking the same question (Optional check if order is guaranteed)
            // String userAns = response.getResponse();
            // String correctAns = questions.get(i).getRightAnswer();

            if(response.getResponse().equals(questions.get(i).getRightAnswer())) {
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
