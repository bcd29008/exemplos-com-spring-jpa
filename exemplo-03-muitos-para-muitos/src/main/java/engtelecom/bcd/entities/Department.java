package engtelecom.bcd.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "employees")

@Entity
public class Department implements Serializable{

    @Id
    private Integer depno;

    private String dname;
    private String location;
    private Integer head;


    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;
    


    public Department(Integer depno, String dname, String location, Integer head) {
        this.depno = depno;
        this.dname = dname;
        this.location = location;
        this.head = head;
    }    
}
