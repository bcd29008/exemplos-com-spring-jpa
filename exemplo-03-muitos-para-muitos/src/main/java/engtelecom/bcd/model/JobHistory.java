package engtelecom.bcd.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "employee" })

@Entity
@Table(name = "jobhistory")
public class JobHistory {

    @Id
    private String position;

    @Id
    @ManyToOne
    @JoinColumn(name = "empno", nullable = false)
    private Employee employee;

    private Date startdate;
    private Date enddate;

    @Column(columnDefinition = "Decimal(8,2)")
    private Double salary;

}
