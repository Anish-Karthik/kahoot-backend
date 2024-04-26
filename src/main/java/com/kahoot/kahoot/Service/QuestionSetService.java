package com.kahoot.kahoot.Service;

import com.kahoot.kahoot.Entity.Question;
import com.kahoot.kahoot.Repository.QuestionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionSetService implements QuestionSetServicess {

    private Map<Long, Question> questionMap; // Store questions using their IDs
    private QuestionRepository questionRepository;

    public QuestionSetService(QuestionRepository questionRepository) {
        this.questionMap = new HashMap<>();
        this.questionRepository = questionRepository;
    }


    @Override
    public List<Question> getAllQuestions() {
        // Assuming the repository has a method to fetch all questions
        List<Question> questions = questionRepository.findAll();
        return questions;
    }


    public Question getQuestionById(Long id) {
        // Retrieve the question from the question map using the provided ID
        return questionMap.get(id);
    }


    public Question addQuestion(Question question) {
        // Generate a new ID for the question
        Long newId = generateNewId();

        // Set the ID of the question
        question.setId(newId);

        // Add the question to the question map
        questionMap.put(newId, question);

        // Return the added question
        return question;
    }

    // Method to generate a new ID for the question
    private Long generateNewId() {
        // Increment the ID counter and return the new value
        int idCounter= 0;
        return (long) ++idCounter;
    }

    public Question updateQuestion(Long id, Question question) {
        // Check if the question with the given id exists
        if (questionMap.containsKey(id)) {
            // If it exists, update the question in the question map
            question.setId(id); // Ensure the question ID matches the provided ID
            questionMap.put(id, question);
            return question; // Return the updated question
        } else {
            return null; // Return null indicating update failed because the question was not found
        }
    }


    public boolean deleteQuestion(Long id) {
        // Check if the question with the given id exists
        if (questionMap.containsKey(id)) {
            // If it exists, remove it from the question map
            questionMap.remove(id);
            return true; // Return true indicating deletion was successful
        } else {
            return false; // Return false indicating deletion failed because the question was not found
        }
    }
}