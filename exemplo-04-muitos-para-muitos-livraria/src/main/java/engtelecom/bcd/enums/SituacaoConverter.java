package engtelecom.bcd.enums;

import java.util.stream.Stream;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SituacaoConverter implements AttributeConverter<Situacao, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Situacao situacao) {
        if (situacao == null) {
            return null;
        }

        return situacao.getCodigo();

    }

    @Override
    public Situacao convertToEntityAttribute(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        return Stream.of(Situacao.values()).filter(s -> s.getCodigo() == codigo).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
