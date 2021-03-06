package engtelecom.bcd.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"livros"})
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {"livros"})
@Entity
public class Editora implements Serializable{

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEditora;

    @NonNull
    @Column(nullable = false)
    private String nome;

    @NonNull
    private String cidade;

    /**
     * Fazendo o mapeamento bidirecional. Assim, a partir de uma editora é possível ver todos livros que ela publicou
     */
    @OneToMany(mappedBy = "editora")
    private Set<Edicao> livros;
    
}
