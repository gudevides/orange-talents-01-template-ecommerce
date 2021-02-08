package com.br.zup.mercadolivre.config.validacao;

import com.br.zup.mercadolivre.produto.cadastraprodutoeimagem.CaracteristicaRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;

public class UniqueValueInListValidator implements ConstraintValidator<UniqueValueInList, Object> {

    @Override
    public void initialize(UniqueValueInList params) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        List<CaracteristicaRequest> listaCaracteristicas = (List<CaracteristicaRequest>) value;
        return listaCaracteristicas.equals(listaCaracteristicas.stream().distinct().collect(Collectors.toList()));
//      return listaCaracteristicas.size() == listaCaracteristicas.stream().distinct().count();
    }
}
