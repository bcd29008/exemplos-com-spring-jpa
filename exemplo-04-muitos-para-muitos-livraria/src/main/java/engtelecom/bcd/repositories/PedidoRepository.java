package engtelecom.bcd.repositories;

import org.springframework.data.repository.CrudRepository;

import engtelecom.bcd.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {
    
}
