package com.br.zup.mercadolivre.config.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueValueInListValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValueInList {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
