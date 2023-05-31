package engtelecom.bcd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import engtelecom.bcd.entities.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    // Lista de cursos que ocorreram em um determinado ano
    // Fazendo de parâmetro nomeados.
    @Query("SELECT c FROM Course c WHERE YEAR(c.cdate) = :ano")
    List<Course> findByCursosRealizadosEmUmAno(@Param("ano") int ano);

}
