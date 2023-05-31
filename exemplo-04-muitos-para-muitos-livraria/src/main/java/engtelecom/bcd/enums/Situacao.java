package engtelecom.bcd.enums;

import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Situacao {

    ANALISE(1, "Em análise"),
    APROVADO(2, "Aprovado"),
    TRANSITO(3, "Em trânsito"),
    ENTREGUE(4, "Entregue");

    private int codigo;
    private String nome;

    /**
     * Obtendo o Enum associado ao código
     * 
     * @param codSituacao código informado
     * @return Enum
     */
    public static Situacao of(int codSituacao) {
        // Poderia fazer um foreach no lugar do código abaixo
        return Stream.of(Situacao.values()).filter(s -> s.getCodigo() == codSituacao).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
