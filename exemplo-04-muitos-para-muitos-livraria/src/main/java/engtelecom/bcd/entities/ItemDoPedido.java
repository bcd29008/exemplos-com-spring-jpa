package engtelecom.bcd.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A anotação IdClass indica qual classe deverá ser usada para construir a chave primária. Esta situação só é necessário em relacionamentos muitos-para-muitos, com chave primária composta e quando se deseja ter atributos adicionais nesta entidade. Existem outras maneiras para fazer isso, por exemplo, criar um atributo específico para ser chave primária e evitar que as chaves estrangeiras façam parte da chave primária
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@ToString(exclude = {"pedido", "edicao"})
@IdClass(ItemDoPedidoId.class)
public class ItemDoPedido implements Serializable{
    
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    private Pedido pedido;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    private Edicao edicao;

    private Double preco;

    private Integer quantidade;
}
