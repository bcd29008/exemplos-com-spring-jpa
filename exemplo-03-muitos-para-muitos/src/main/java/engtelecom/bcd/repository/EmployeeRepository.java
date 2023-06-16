package engtelecom.bcd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import engtelecom.bcd.model.Employee;
import engtelecom.bcd.model.JobHistory;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    // Lista de funcionários que fazem aniversário em um determinado mês
    @Query("SELECT e FROM Employee e WHERE MONTH(e.dob) = ?1")
    // A mesma query, porém usando consulta SQL nativa do MySQL
    // @Query(value = "SELECT * FROM employee e WHERE MONTH(e.dob) = ?1", nativeQuery = true)
    List<Employee> findByAniversariantesNoMes(int mes);

    // Lista de todos os cargos que um funcionário assumiu na empresa
    @Query("SELECT j FROM JobHistory j WHERE j.employee = ?1 ORDER BY j.startdate DESC")
    List<JobHistory> findByDeCargosNaEmpresa(Employee employee);

}
