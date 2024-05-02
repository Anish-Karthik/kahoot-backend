package com.kahoot.kahoot.Service;

import java.util.List;

import com.kahoot.kahoot.Entity.Question;

public interface QuestionSetServicess {

    List<Question> getAllQuestions();

    Question getQuestionById(Long id);

    Question addQuestion(Question question);

    Question updateQuestion(Long id, Question question);

    boolean deleteQuestion(Long id);
}
