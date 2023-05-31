package engtelecom.bcd.repository;

import org.springframework.data.repository.CrudRepository;

import engtelecom.bcd.entities.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {

}
