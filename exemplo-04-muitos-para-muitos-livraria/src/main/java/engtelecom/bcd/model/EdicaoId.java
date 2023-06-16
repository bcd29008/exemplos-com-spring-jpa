package engtelecom.bcd.model;

import java.io.Serializable;

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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
/**
 * Classe criada para ser usada pela classe Edicao que necessita de uma chave primária composta por duas colunas
 */
public class EdicaoId implements Serializable {

    private Integer numero;
    private Integer idLivro;
}
