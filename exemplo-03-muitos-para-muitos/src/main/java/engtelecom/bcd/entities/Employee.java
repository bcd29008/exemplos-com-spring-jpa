package engtelecom.bcd.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString(exclude = {"courses", "jobHistory"})

@Entity
public class Employee implements Serializable{

    @Id
    private Integer empno;

    private String surname;
    private String forenames;
    private Date dob;
    private String address;
    private String telno;

    @ManyToOne
    @JoinColumn(name = "depno", nullable =  false)
    private Department department;

    @OneToMany(mappedBy = "employee")
    private Set<JobHistory> jobHistory;

    /**
     * A anotação ManyToMany ficará responsável por criar ou mapear a tabela no banco de dados. A propriedade mappedBy é usada para indicar a entidade que é a proprietária do relacionamento bidirecional. Neste exemplo é a entidade Employee.
     * 
     * A anotação JoinTable define detalhes da tabela que será criada (mapeada) no banco de dados para representar o relacionamento muitos-para-muitos. Neste exemplo será criada/mapeada a tabela chamada 'empcourse' a qual irá conter duas colunas: 
     * - empno que referencia coluna de mesmo nome da entidade Employee e 
     * - coursno que referencia coluna de mesmo nome da entidade Course (com a anotação inverseJoinColumns)
     * 
     * Se na tabela do relacionamento muitos-para-muitos tiver colunas além das duas que fazem parte da chave, então precisará seguir outra abordagem e criar uma entidade dedicada para isso. Usará as anotações OneToMany, ManyToOne, Embeddable e EmbeddedId. Veja exemplo em:
     * 
     * https://docs.jboss.org/hibernate/annotations/3.5/reference/en/html/entity.html#entity-mapping-association
     * 
     */
    @ManyToMany
    @JoinTable(name = "empcourse",
                joinColumns = {
                    @JoinColumn(name="empno",
                    referencedColumnName = "empno", 
                    nullable = false, updatable = false),
                },
                inverseJoinColumns = {
                    @JoinColumn(name="courseno",
                    referencedColumnName = "courseno", 
                    nullable = false, updatable = false)
                })
    private Set<Course> courses = new HashSet<>();



    public Employee(Integer empno, String surname, String forenames, Date dob, String address, String telno,
            Department department) {
        this.empno = empno;
        this.surname = surname;
        this.forenames = forenames;
        this.dob = dob;
        this.address = address;
        this.telno = telno;
        this.department = department;
    }
}
