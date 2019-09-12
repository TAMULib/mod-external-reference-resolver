package org.folio.rest.model.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = ExternalReverenceValidator.class)
public @interface ValidExternalReference {

    String message() default "ExternalReference";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}