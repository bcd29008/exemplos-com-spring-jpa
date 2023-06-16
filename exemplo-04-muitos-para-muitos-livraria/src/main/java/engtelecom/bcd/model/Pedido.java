package engtelecom.bcd.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import engtelecom.bcd.enums.Situacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"itens"})
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
public class Pedido implements Serializable{

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

    @NonNull
    @Column(nullable = false)
    private Date data;

    @NonNull
    private Situacao situacao;

    /**
     * O valor 'pedido' em mappedBy deve ser exatamente o nome do atributo na classe ItemDoPedido que tenha
     * a anotação ManyToOne
     */
    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    @Autowired
    public List<ItemDoPedido> itens = new ArrayList<>();

}
