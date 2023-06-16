package engtelecom.bcd.repositories;

import org.springframework.data.repository.CrudRepository;

import engtelecom.bcd.model.Livro;

public interface LivroRepository extends CrudRepository<Livro, Integer> {
    
}
