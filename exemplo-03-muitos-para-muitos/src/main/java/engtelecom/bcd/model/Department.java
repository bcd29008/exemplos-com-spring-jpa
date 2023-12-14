package engtelecom.bcd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = "employees")
@RequiredArgsConstructor
@Entity
public class Department {

    @Id
    @NonNull
    private Integer depno;

    @NonNull
    private String dname;
    @NonNull
    private String location;
    @NonNull
    private Integer head;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees = new HashSet<>();

    protected Department(){}
}
