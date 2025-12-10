package com.example.quizzApp.Repo;

import com.example.quizzApp.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question, Integer> {
    // You don't need to write code for save(), findAll(), findById(), or delete()
    // JpaRepository provides them automatically.

    // EXTRA: If you want to fetch questions by category (e.g., "Java" or "Python"),
    // you can simply declare this method:
    List<Question> findByCategory(String category);
    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
