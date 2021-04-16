package service.grades;

import model.Grades;

public interface GradesInterface {

    void deleteGradeById(Integer id);

    Grades findGradeById(Integer id);

    void saveGrade(Grades grade);

}
