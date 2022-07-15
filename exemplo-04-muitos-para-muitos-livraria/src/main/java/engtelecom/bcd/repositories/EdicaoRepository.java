package engtelecom.bcd.repositories;

import org.springframework.data.repository.CrudRepository;

import engtelecom.bcd.entities.Edicao;
import engtelecom.bcd.entities.EdicaoId;

public interface EdicaoRepository extends CrudRepository<Edicao, EdicaoId> {


}
