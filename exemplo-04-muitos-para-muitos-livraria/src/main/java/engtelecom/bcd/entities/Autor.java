package engtelecom.bcd.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude = { "livros" })
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = { "livros" })
@Entity
public class Autor implements Serializable {

    /**
     * A anotação NonNull é usada pela biblioteca lombok para determinar quais
     * atributos deverão fazer parte do construtor que será gerado com a anotação
     * RequiredArgsConstructor
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAutor;

    /**
     * Anotação column indica que a coluna no banco de dados não permite valores
     * nulos
     */
    @NonNull
    @Column(nullable = false)
    private String nome;

    @NonNull
    @Column(nullable = false)
    private String sobrenome;

    /**
     * Fazendo o mapeamento bidirecional. Assim, a partir de um autor é possível ver
     * os livros que publicou
     */
    @ManyToMany(mappedBy = "autores")
    private Set<Livro> livros = new HashSet<>();

    public boolean adicionarLivro(Livro livro) {
        return livros.add(livro);
    }
}
