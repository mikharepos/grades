package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import model.Grades;

@Repository
public interface GradesRepository extends JpaRepository<Grades, Integer> {

}
