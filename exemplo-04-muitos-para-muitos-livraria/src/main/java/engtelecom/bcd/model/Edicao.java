package engtelecom.bcd.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = { "capa", "pedidos" })
@ToString
@Entity
public class Edicao implements Serializable {

    /**
     * A chave primária é composta por duas colunas. Assim, foi criada uma classe EdicaoId
     * para representar a chave primária e aqui foi feito uso da anotação @EmbeddedId
     * 
     * @see engtelecom.bcd.repositories.EdicaoRepository, pois ao herdar a interface
     * CrudRepository<Edicao, EdicaoId> é necessário informar sempre o nome da entidade
     * e o tipo da chave primária, no caso como a chave é composta, esse tipo precisa ser
     * uma classe
     */
    @NonNull
    @EmbeddedId
    private EdicaoId idEdicao;

    @NonNull
    @Column(nullable = false)
    private String isbn13;

    @NonNull
    private Date dataPublicacao;

    @NonNull
    private Integer totalDePaginas;

    @NonNull
    private Double preco;

    @Lob
    private byte[] capa;

    /**
     * Anotação Embedded indica que a entidade Dimensão não terá uma tabela
     * específica. Seus atributos serão incluídos na tabela gerada para a entidade
     * Edicao
     */
    @NonNull
    @Embedded
    private Dimensao dimensoes;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "idEditora", nullable = false)
    private Editora editora;

    @NonNull
    @ManyToOne
    @MapsId("idLivro")
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

    /**
     * O valor 'edicao' em mappedBy deve ser exatamente o nome do atributo na classe ItemDoPedido que tenha
     * a anotação ManyToOne
     */
    @Autowired
    @OneToMany(mappedBy = "edicao", fetch = FetchType.EAGER)
    public List<ItemDoPedido> pedidos = new ArrayList<>();
}
