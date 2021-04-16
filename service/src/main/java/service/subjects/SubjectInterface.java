package service.subjects;

import model.Grades;
import model.Subject;

import java.util.List;

public interface SubjectInterface {

    List<Subject> getSubjects();

    Subject getSubjectById(Integer id);

    void saveSubject(Subject subject);

    void deleteSubject(Integer id);

    void addToSubjectGrade(Integer id, Grades grade);

    void averageGrade(List<Subject>subjects);

}
