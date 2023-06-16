package engtelecom.bcd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

/**
 * POJO para representar a entidade Campus. 
 * 
 * As anotações Getter, Setter, EqualsAndHashCode, toString e NoArgsConstructor  são da biblioteca lombok que facilita a criação do POJO
 */

@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"cursos"})
@RequiredArgsConstructor

@Entity
public class Campus implements Serializable{

    /**
     * JPA exige construtor padrão
     */
    protected Campus(){}
    
    /**
     * A anotação Id indica que o atributo é a chave primária da entidade
     * 
     * A anotação GeneratedValue indica a estratégia para geração dos valores da chave primário ao criar uma nova entidade. No exemplo abaixo a chave primária será configurada no MySQL com AUTOINCREMENT
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCampus;

    /**
     * A anotação Column pode ser usada para especificar o nome da coluna
     * na tabela (name) e se não será permitido valor nulo na coluna
     */
    @Column(nullable = false)
    @NonNull
    private String nome;

    /**
     * Não pode existir mais de um campus com a mesma sigla. Será criada restrição UNIQUE no banco de dados
     */
    @Column(nullable = false, unique = true)
    @NonNull
    private String sigla;

    @NonNull
    private String endereco;

    @NonNull
    private String cidade;

    
    /**
     * A anotação OneToMany indica o relacionamento desta entidade com a entidade Curso. No caso, UM campus pode estar associado com MUITOS cursos.
     * 
     * Em relacionamento bidirecionais é necessário especificar esta anotação em ambas as entidades (como foi feito neste exemplo), porém somente uma das entidades será a dona da associação (indicada pela propriedade mappedBy). Em mappedBy coloca-se o identificador do mapeamento que deverá aparecer como atributo da classe Curso
     * 
     * Atributo cascade (boa prática de pai para filho apenas) pode assumir alguns valores (CascadeType.ALL ativa todos). Detalhes de alguns:
     * CascadeType.PERSIST - entidades filhas serão persistidas assim que a entidade pai for persistida no banco de dados
     * CascadeType.MERGE - quando a entidade pai sofrer alterações e essas forem mescladas no contexto de persistência, as entidades filhas também serão
     * CascadeType.REMOVE - ao excluir a entidade pai, a entidade filha também é excluída
     * CascadeType.REFRESH - ao atualizar a entidade pai a partir do banco de dados, a entidade filha também é atualizada a partir do banco
     * 
     * O valor em mappeBy deve ser exatamente igual ao nome do atributo da classe Curso que tem a anotação ManyToOne
     */
    @OneToMany(mappedBy = "campus", cascade = {CascadeType.ALL})
    @Autowired
    private List<Curso> cursos = new ArrayList<>();
}
