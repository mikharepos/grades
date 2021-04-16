package service.subjects;

import model.Grades;
import model.Subject;
import org.springframework.stereotype.Service;
import repository.GradesRepository;
import repository.SubjectRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SubjectImpl implements SubjectInterface {

    private final SubjectRepository subjectRepository;
    private final GradesRepository gradesRepository;

    public SubjectImpl(SubjectRepository subjectRepository, GradesRepository gradesRepository) {
        this.subjectRepository = subjectRepository;
        this.gradesRepository = gradesRepository;
    }

    @Override
    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(Integer id) {
        return subjectRepository.getOne(id);
    }

    @Override
    public void saveSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public void deleteSubject(Integer id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public void addToSubjectGrade(Integer id, Grades grade) {
        Subject subject = subjectRepository.getOne(id);
        subject.getGrades().add(grade);
        grade.setSubject(subject);
        gradesRepository.save(grade);
        subjectRepository.save(subject);
    }

    @Override
    public void averageGrade(List<Subject> subjects) {

        for (Subject subject:subjects) {
            double sum = 0;
            int count = 0;

            //получаем все оценки по каждому предмету
            List<Double> grades = new ArrayList<>();
            subject.getGrades().forEach(grade -> grades.add(grade.getGrade()));

            //получаем среднюю оценку
            for (Double integer:grades) {
                sum +=integer;
                count++;
            }
                subject.setAverageGrade((float) sum/count);
                subjectRepository.save(subject);
        }
    }
}
