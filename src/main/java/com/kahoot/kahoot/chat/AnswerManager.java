package com.kahoot.kahoot.chat;

import java.util.Date;

import com.kahoot.kahoot.Entity.Question;
import com.kahoot.kahoot.users.Answer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerManager {
  private Question currentQuestion;
  private int currentQuestionIndex;
  private Date startTime;

  public Answer validateAnswer(int answerIndex, String username) {
    Date answerTime = new Date();
    double answeredInSeconds = (answerTime.getTime() - startTime.getTime()) / 1000;
    if (answeredInSeconds > currentQuestion.getTimeLimit() + 2) {
      return new Answer(answerIndex, false, currentQuestionIndex, answeredInSeconds, username, 0);
    }
    boolean correct = currentQuestion.getCorrectAnswerIndices().contains(answerIndex);
    double timeTaken = answeredInSeconds * 10.5;
    int maxScore = 1000;
    int score = (int) (maxScore - timeTaken);
    return new Answer(answerIndex, correct, currentQuestionIndex, answeredInSeconds, username, score);
  }

  // public void startTimer() {
  //   Thread timerThread = new Thread(() -> {
  //     try {
  //       Thread.sleep(currentQuestion.getDuration() * 1000);
  //       // Send a message to the chat that time is up
  //       System.out.println("Time is up!");
  //     } catch (InterruptedException e) {
  //       e.printStackTrace();
  //     }
  //   });
  //   timerThread.start();
  // }
}
