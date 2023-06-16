package engtelecom.bcd.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
@ToString(exclude = { "employees" })
@Entity
public class Course implements Serializable {

    @Id
    @NonNull
    private Integer courseno;

    @NonNull
    private String cname;
    @NonNull
    private Date cdate;

    /**
     * A anotação ManyToMany ficará responsável por criar ou mapear a tabela no
     * banco de dados. A propriedade mappedBy é usada para indicar a entidade que é
     * a proprietária do relacionamento bidirecional. Neste exemplo é a entidade
     * Employee.
     */
    @ManyToMany(mappedBy = "courses")
    @Autowired
    private List<Employee> employees = new ArrayList<>();

    protected Course(){}
}
