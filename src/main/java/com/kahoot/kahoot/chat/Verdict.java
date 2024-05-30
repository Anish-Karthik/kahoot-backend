package com.kahoot.kahoot.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Verdict {
  private boolean correct;
  private int correctAnswerIndex;
}
