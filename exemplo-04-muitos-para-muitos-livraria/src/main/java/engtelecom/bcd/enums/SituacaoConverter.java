package engtelecom.bcd.enums;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class SituacaoConverter implements AttributeConverter<Situacao, Integer>{

    @Override
    public Integer convertToDatabaseColumn(Situacao situacao) {
        if (situacao == null){
            return null;
        }

        return situacao.getCodigo();
        
    }

    @Override
    public Situacao convertToEntityAttribute(Integer codigo) {
        if (codigo == null){
            return null;
        }

        return Stream.of(Situacao.values()).filter(s->s.getCodigo() == codigo).findFirst().orElseThrow(IllegalArgumentException::new);
    }

}
