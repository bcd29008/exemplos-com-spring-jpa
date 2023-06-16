package engtelecom.bcd.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = { "courses", "jobHistory" })
@RequiredArgsConstructor

@Entity
public class Employee implements Serializable {

    @Id
    @NonNull
    private Integer empno;

    @NonNull
    private String surname;
    @NonNull
    private String forenames;
    @NonNull
    private Date dob;
    @NonNull
    private String address;
    @NonNull
    private String telno;

    @ManyToOne
    @JoinColumn(name = "depno", nullable = false)
    @NonNull
    private Department department;

    @OneToMany(mappedBy = "employee")
    @Autowired
    private List<JobHistory> jobHistory = new ArrayList<>();

    /**
     * A anotação ManyToMany ficará responsável por criar ou mapear a tabela no
     * banco de dados. A propriedade mappedBy é usada para indicar a entidade que é
     * a proprietária do relacionamento bidirecional. Neste exemplo é a entidade
     * Employee.
     * 
     * A anotação JoinTable define detalhes da tabela que será criada (mapeada) no
     * banco de dados para representar o relacionamento muitos-para-muitos. Neste
     * exemplo será criada/mapeada a tabela chamada 'empcourse' a qual irá conter
     * duas colunas:
     * - empno que referencia coluna de mesmo nome da entidade Employee e
     * - coursno que referencia coluna de mesmo nome da entidade Course (com a
     * anotação inverseJoinColumns)
     * 
     * Se na tabela do relacionamento muitos-para-muitos tiver colunas além das duas
     * que fazem parte da chave, então precisará seguir outra abordagem e criar uma
     * entidade dedicada para isso. Usará as anotações OneToMany, ManyToOne,
     * Embeddable e EmbeddedId. Veja exemplo em:
     * 
     * https://docs.jboss.org/hibernate/annotations/3.5/reference/en/html/entity.html#entity-mapping-association
     * 
     */
    @ManyToMany
    @JoinTable(name = "empcourse", joinColumns = {
            @JoinColumn(name = "empno", referencedColumnName = "empno", nullable = false, updatable = false),
    }, inverseJoinColumns = {
            @JoinColumn(name = "courseno", referencedColumnName = "courseno", nullable = false, updatable = false)
    })
    @Autowired
    private List<Course> courses = new ArrayList<>();

    protected Employee(){}
}
