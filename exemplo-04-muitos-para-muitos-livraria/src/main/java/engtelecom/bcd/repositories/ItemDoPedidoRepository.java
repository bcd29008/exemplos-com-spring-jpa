package engtelecom.bcd.repositories;

import org.springframework.data.repository.CrudRepository;

import engtelecom.bcd.model.ItemDoPedido;
import engtelecom.bcd.model.ItemDoPedidoId;

public interface ItemDoPedidoRepository extends CrudRepository<ItemDoPedido, ItemDoPedidoId> {

}
