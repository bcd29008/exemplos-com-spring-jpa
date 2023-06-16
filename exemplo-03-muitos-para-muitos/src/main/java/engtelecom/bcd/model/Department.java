package engtelecom.bcd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = "employees")
@RequiredArgsConstructor
@Entity
public class Department implements Serializable {

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
    @Autowired
    private List<Employee> employees = new ArrayList<>();

    protected Department(){}
}
