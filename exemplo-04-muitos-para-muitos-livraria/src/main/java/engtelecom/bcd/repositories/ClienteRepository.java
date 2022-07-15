package engtelecom.bcd.repositories;

import org.springframework.data.repository.CrudRepository;

import engtelecom.bcd.entities.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer>{
    
}
