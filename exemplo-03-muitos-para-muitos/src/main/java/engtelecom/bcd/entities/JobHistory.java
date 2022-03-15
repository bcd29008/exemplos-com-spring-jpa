package engtelecom.bcd.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"employee"})

@Entity
@Table(name = "jobhistory")
public class JobHistory implements Serializable{
    
    @Id
    private String position;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "empno", nullable =  false)
    private Employee employee;

    
    private Date startdate;
    private Date enddate;

    @Column(columnDefinition = "Decimal(8,2)")
    private Double salary;


}
