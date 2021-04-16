package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "grades")
public class Grades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gradeId;

    @Column(name = "grade")
    private double grade;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
