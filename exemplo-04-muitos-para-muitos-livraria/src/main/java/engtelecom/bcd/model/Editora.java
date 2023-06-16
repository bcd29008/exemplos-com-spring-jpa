package engtelecom.bcd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@EqualsAndHashCode(exclude = { "livros" })
@ToString(exclude = { "livros" })
@Entity
public class Editora implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEditora;

    @NonNull
    @Column(nullable = false)
    private String nome;

    @NonNull
    private String cidade;

    /**
     * Fazendo o mapeamento bidirecional. Assim, a partir de uma editora é possível
     * ver todos livros que ela publicou.
     * 
     * O nome 'editora' deve ser exatamente igual ao nome do atributo na classe Edicao
     * que esteja anotado com ManyToOne
     */
    @Autowired
    @OneToMany(mappedBy = "editora")
    private List<Edicao> livros = new ArrayList<>();

}
