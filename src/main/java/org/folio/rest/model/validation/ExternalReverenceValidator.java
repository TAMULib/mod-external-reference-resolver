package org.folio.rest.model.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.folio.rest.model.ExternalReference;

public class ExternalReverenceValidator implements ConstraintValidator<ValidExternalReference, ExternalReference> {

  private static final String MESSAGE_TEMPLATE = "%s: %s";

  @Override
  public boolean isValid(ExternalReference externalReference, ConstraintValidatorContext constraintContext) {
    Map<String,String> violations = new HashMap<String, String>();
    validateValues(externalReference, violations);
    buildMessageTemplate(constraintContext, violations);
    return violations.isEmpty();
  }

  private void validateValues(ExternalReference externalReference, Map<String,String> violations) {
    List<String> allowedKeys = externalReference.getType().getAllowedKeys();
    List<String> usedKeys = externalReference.getDistinctives().stream().map(d -> d.getKey()).collect(Collectors.toList());
    usedKeys.removeAll(allowedKeys);
    if(!usedKeys.isEmpty()) {
      violations.put("values", String.format(MESSAGE_TEMPLATE, "Unrecognized Keys", usedKeys.stream().collect(Collectors.joining(","))));
    }
  }

  private void buildMessageTemplate(ConstraintValidatorContext constraintContext, Map<String,String> violations) {
    violations.forEach((node,violation)->{
      constraintContext.buildConstraintViolationWithTemplate(violation)
        .addPropertyNode(node)
        .addConstraintViolation();
    });
    constraintContext.disableDefaultConstraintViolation();
  };

}