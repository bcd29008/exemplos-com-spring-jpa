package engtelecom.bcd.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

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
@EqualsAndHashCode(exclude = { "capa", "pedidos" })
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
public class Edicao implements Serializable {

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

    @OneToMany(mappedBy = "edicao", fetch = FetchType.EAGER)
    public Set<ItemDoPedido> pedidos = new HashSet<>();;
}
