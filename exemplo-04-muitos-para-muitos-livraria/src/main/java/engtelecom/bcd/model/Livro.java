package engtelecom.bcd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import engtelecom.bcd.enums.Idiomas;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
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
@EqualsAndHashCode(exclude = { "autores", "edicoes" })
@ToString(exclude = { "edicoes" })
@Entity
public class Livro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLivro;

    @NonNull
    @Column(nullable = false)
    private String titulo;

    /**
     * Anotação Lob para indicar que deverá ser criada uma coluna no banco de dados
     * para um CLOB (Character Large Object)
     */
    @NonNull
    @Lob
    private String descricao;

    /**
     * Na enumeração optou-se por guardar a String no banco com a opção
     * EnumType.STRING.
     */
    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Idiomas idioma;

    @NonNull
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Autor> autores = new HashSet<>();

    /**
     * Fazendo o mapeamento bidirecional. Assim, a partir de um livro é possível ver
     * todas as edições
     * 
     * O valor 'livro' em mappedBy deve ser exatamente o nome do atributo na classe Edicao que tenha
     * a anotação ManyToOne
     */
    @OneToMany(mappedBy = "livro")
    private Set<Edicao> edicoes = new HashSet<>();

    public boolean adicionarEdicao(Edicao edicao) {
        return this.edicoes.add(edicao);
    }

    public boolean adicionarAutor(Autor autor) {
        return this.autores.add(autor);
    }

}
