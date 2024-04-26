package com.kahoot.kahoot.Service;

import org.springframework.http.ResponseEntity;

public interface Question {
    ResponseEntity<Void> submitAnswer(Long questionId, String answer);
    ResponseEntity<String> getAnswer(Long questionId);
}
