package engtelecom.bcd.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString(exclude = { "employees" })
@Entity
public class Course implements Serializable {

    @Id
    private Integer courseno;

    private String cname;
    private Date cdate;

    /**
     * A anotação ManyToMany ficará responsável por criar ou mapear a tabela no
     * banco de dados. A propriedade mappedBy é usada para indicar a entidade que é
     * a proprietária do relacionamento bidirecional. Neste exemplo é a entidade
     * Employee.
     */
    @ManyToMany(mappedBy = "courses")
    private Set<Employee> employees = new HashSet<>();

    public Course(Integer courseno, String cname, Date cdate) {
        this.courseno = courseno;
        this.cname = cname;
        this.cdate = cdate;
    }
}
