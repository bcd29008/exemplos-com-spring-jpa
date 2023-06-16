package engtelecom.bcd.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
@ToString
@AllArgsConstructor
@Embeddable
/**
 * A anotação @Embeddable indica que essa classe é para ser embarcada em outra e assim, não deverá criar
 * uma tabela no banco. Os atributos dessa classe serão mesclados com os atributos da classe (entidade) que a contiver
 */
public class Dimensao implements Serializable {

    @Column(nullable = false)
    private Double largura;
    @Column(nullable = false)
    private Double altura;
    @Column(nullable = false)
    private Double profundidade;
}
