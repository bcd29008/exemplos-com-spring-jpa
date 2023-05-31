package engtelecom.bcd.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * POJO para representar a entidade Curso
 * 
 * É necessário que a classe tem getter/setter, construtor padrão, construtor com parâmetros, toString, hashCode() e equals(). Use a IDE para gerar isso para você ou faça uso do projeto Lombok (https://projectlombok.org).
 * 
 * A anotação Entity indica que isso será uma entidade no mapeamento ORM
 * 
 * A anotação Table pode ser usada para indicar o nome da tabela que será criada. Se não for informada, então o nome da classe será usado como o nome da Tabela. Neste exemplo, a anotação Table poderia ser suprimida
 * 
 * As anotações Getter, Setter, EqualsAndHashCode, toString e NoArgsConstructor  são da biblioteca lombok que facilita a criação do POJO
 * 
 */

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor

@Entity
@Table(name = "Curso")
public class Curso implements Serializable{

    /**
     * A anotação Id indica que o atributo é a chave primária da entidade
     * 
     * A anotação GeneratedValue indica a estratégia para geração dos valores da chave primário ao criar uma nova entidade. No exemplo abaixo a chave primária será configurada no MySQL com AUTOINCREMENT
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCurso;

    private String nome;
    private int    cargaHoraria;


    /**
     * A anotação ManyToOne indica o relacionamento desta entidade com a entidade Campus. No caso, MUITOS Cursos poderão estar associados com UM ÚNICO Campus.
     * 
     * A anotação JoinColumn é usada para especificar o nome da chave estrangeira na entidade dona do relacionamento (no caso, esta entidade). No lado inverso (na entidade Campus) é feito uso da propriedade mappedBy para indicar que o relacionamento é mapeado por outra entidade.
     * 
     */
    @ManyToOne
    @JoinColumn(name = "idCampus", nullable = false)
    private Campus campus;


    public Curso(String nome, int cargaHoraria, Campus campus) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.campus = campus;
    }

    

}
