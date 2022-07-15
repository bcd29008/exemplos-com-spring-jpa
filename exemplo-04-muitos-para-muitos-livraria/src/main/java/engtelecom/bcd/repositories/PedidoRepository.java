package engtelecom.bcd.repositories;

import org.springframework.data.repository.CrudRepository;

import engtelecom.bcd.entities.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {
    
}
