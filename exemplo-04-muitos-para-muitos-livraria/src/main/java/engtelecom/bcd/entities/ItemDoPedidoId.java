package engtelecom.bcd.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe necessária quando se deseja um relacionamento muitos-para-muitos com chave estrangeira composta e quando se deseja ter colunas adicionais neste relacionamento. Essa classe não criará uma tabela no banco de dados
 */
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ItemDoPedidoId implements Serializable{

    private Integer pedido;
    private EdicaoId edicao;
}

