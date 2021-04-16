package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "average_grade")
    private float averageGrade = 0.0f;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Grades> grades = new ArrayList<>(0);

    @ManyToMany(mappedBy = "subjects", fetch = FetchType.LAZY)
    private List<Account> accounts = new ArrayList<>();

    public Subject(String subject) {
    }
}
