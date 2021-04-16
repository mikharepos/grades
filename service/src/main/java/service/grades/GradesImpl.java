package service.grades;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import repository.GradesRepository;
import model.Grades;

import javax.transaction.Transactional;

@Service
@Transactional
public class GradesImpl implements GradesInterface {

    Logger logger = LoggerFactory.getLogger(GradesImpl.class);

    private final GradesRepository gradesRepository;

    public GradesImpl(GradesRepository gradesRepository) {
        this.gradesRepository = gradesRepository;
    }

    @Override
    public void deleteGradeById(Integer id) {
        gradesRepository.deleteById(id);
        logger.info("Оценка удалена!");
    }

    @Override
    public Grades findGradeById(Integer id) {
        return gradesRepository.getOne(id);
    }

    @Override
    public void saveGrade(Grades grade) {
        gradesRepository.save(grade);
        logger.info("Оценка сохранена!");
    }

}
