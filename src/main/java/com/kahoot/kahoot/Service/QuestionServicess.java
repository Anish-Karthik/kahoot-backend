package com.kahoot.kahoot.Service;

import java.util.List;

import com.kahoot.kahoot.Entity.Question;

public interface QuestionServicess {
  Question createQuestion(Question question);
  Question getQuestionById(Long questionId);
  Question updateQuestion(Long questionId, Question question);
  Question deleteQuestion(Long questionId);
  List<Question> getQuestionsByQuestionSet(Long questionSetId);

  Question getQuestionByQuestionSetAndQuestion(Long questionSetId, Long questionId);
}
