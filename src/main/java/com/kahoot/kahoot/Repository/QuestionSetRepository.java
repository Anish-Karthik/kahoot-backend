package com.kahoot.kahoot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kahoot.kahoot.Entity.QuestionSet;

public interface QuestionSetRepository extends JpaRepository<QuestionSet, Long> {
  
  // get question set by quizId along with questions
  @Query("SELECT qs FROM QuestionSet qs JOIN FETCH qs.questions WHERE qs.quiz.id = ?1")
  QuestionSet findByQuizIdWithQuestions(Long quizId);

  // get question set by quizId along without questions
  QuestionSet findByQuizId(Long quizId);
}
