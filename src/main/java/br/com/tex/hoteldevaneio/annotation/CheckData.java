package br.com.tex.hoteldevaneio.annotation;

import br.com.tex.hoteldevaneio.utils.CheckDataValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckDataValidator.class)
public @interface CheckData {
    String message() default "Data de check-in deve ser anterior à data de check-out";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
