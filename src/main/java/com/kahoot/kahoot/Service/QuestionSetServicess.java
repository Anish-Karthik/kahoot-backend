package com.kahoot.kahoot.Service;

import com.kahoot.kahoot.Entity.Question;
import com.kahoot.kahoot.Entity.QuestionSet;

import java.util.List;

public interface QuestionSetServicess {

    List<Question> getAllQuestions();

    Question getQuestionById(Long id);

    Question addQuestion(Question question);

    Question updateQuestion(Long id, Question question);

    boolean deleteQuestion(Long id);
}
