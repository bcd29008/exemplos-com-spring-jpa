package engtelecom.bcd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

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
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = { "livros" })
@EqualsAndHashCode(exclude = { "livros" })
@Entity
public class Autor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAutor;

    /**
     * 
     * A anotação NonNull é usada pela biblioteca lombok para determinar quais
     * atributos deverão fazer parte do construtor que será gerado com a anotação
     * RequiredArgsConstructor
     * 
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
     * 
     * O valor na propriedade mappedBy deve ser exatamente o nome do atributo da
     * classe Livro
     * que possui a anotação ManyToMany
     * 
     */
    @ManyToMany(mappedBy = "autores")
    private Set<Livro> livros = new HashSet<>();

    public boolean adicionarLivro(Livro livro) {
        return livros.add(livro);
    }
}
