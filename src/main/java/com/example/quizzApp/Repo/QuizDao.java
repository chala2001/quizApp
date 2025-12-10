package com.example.quizzApp.Repo;

import com.example.quizzApp.Entity.Question;
import com.example.quizzApp.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}