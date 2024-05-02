package com.kahoot.kahoot.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kahoot.kahoot.Entity.Question;
@Service
public class QuestionService implements QuestionServicess {

  @Override
  public Question createQuestion(Question question) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createQuestion'");
  }

  @Override
  public Question getQuestionById(Long questionId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getQuestionById'");
  }

  @Override
  public Question updateQuestion(Long questionId, Question question) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateQuestion'");
  }

  @Override
  public Question deleteQuestion(Long questionId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteQuestion'");
  }

  @Override
  public List<Question> getQuestionsByQuestionSet(Long questionSetId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getQuestionsByQuestionSet'");
  }

  @Override
  public Question getQuestionByQuestionSetAndQuestion(Long questionSetId, Long questionId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getQuestionByQuestionSetAndQuestion'");
  }
  
}
