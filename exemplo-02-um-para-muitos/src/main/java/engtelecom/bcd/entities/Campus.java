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
import lombok.Setter;
import lombok.ToString;

/**
 * POJO para representar a entidade Campus. 
 * 
 * As anotações Getter, Setter, EqualsAndHashCode, toString e NoArgsConstructor  são da biblioteca lombok que facilita a criação do POJO
 */

@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"cursos"})
@NoArgsConstructor

@Entity
public class Campus implements Serializable{
    
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
    private String nome;
    
    @Column(nullable = false)
    private String sigla;

    private String endereco;

    private String cidade;

    
    /**
     * A anotação OneToMany indica o relacionamento desta entidade com a entidade Curso. No caso, UM campus pode estar associado com MUITOS cursos.
     * 
     * Em relacionamento bidirecionais é necessário especificar esta anotação em ambas as entidades (como foi feito neste exemplo), porém somente uma das entidades será a dona da associação (indicada pela propriedade mappedBy). Em mappedBy coloca-se o identificador do mapeamento que deverá aparecer como atributo
     * da classe Curso
     */
    @OneToMany(mappedBy = "campus")
    private Set<Curso> cursos;

    /**
     * Construtor para criar uma entidade Campus. Não é necessário informar o id, pois este será 
     * gerado pelo AUTOINCREMENT
     * @param nome nome do campus
     * @param sigla sigla do campus
     * @param endereco nome da rua e número
     * @param cidade nome da cidade
     */
    public Campus(String nome, String sigla, String endereco, String cidade) {
        this.nome = nome;
        this.sigla = sigla;
        this.endereco = endereco;
        this.cidade = cidade;
    }
}
