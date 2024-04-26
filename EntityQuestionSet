package com.kahoot.kahoot.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "questionSet")
public class QuestionSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "questionSet_categories", joinColumns = @JoinColumn(name = "questionSet_id"))
    private List<String> categories;

    @ManyToOne(optional = true)
    @JoinColumn(name="quiz_id")
    private Quiz quiz;

    @OneToMany(mappedBy = "questionSet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();


}
