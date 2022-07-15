package engtelecom.bcd.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import engtelecom.bcd.enums.Situacao;
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

    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    public Set<ItemDoPedido> itens = new HashSet<>();

}
