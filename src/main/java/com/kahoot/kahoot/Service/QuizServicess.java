package com.kahoot.kahoot.Service;

import com.kahoot.kahoot.Entity.Quiz;

public interface QuizServicess {
    Quiz createQuiz(Quiz quiz);
    Quiz getQuizById(Long id);
    boolean updateQuiz(Long id, Quiz quiz);
    boolean deleteQuiz(Long id);
}
