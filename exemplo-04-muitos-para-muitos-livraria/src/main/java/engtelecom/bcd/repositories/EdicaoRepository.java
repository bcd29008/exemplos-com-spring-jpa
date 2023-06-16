package engtelecom.bcd.repositories;

import org.springframework.data.repository.CrudRepository;

import engtelecom.bcd.model.Edicao;
import engtelecom.bcd.model.EdicaoId;

public interface EdicaoRepository extends CrudRepository<Edicao, EdicaoId> {


}
