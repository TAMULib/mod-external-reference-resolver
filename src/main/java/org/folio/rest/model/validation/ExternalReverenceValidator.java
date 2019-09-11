package org.folio.rest.model.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.folio.rest.model.ExternalReference;

public class ExternalReverenceValidator implements ConstraintValidator<ValidExternalReference, ExternalReference> {

  private static final String MESSAGE_TEMPLATE = "\n%s: %s";

  private static final StringBuilder sb = new StringBuilder();

  @Override
  public boolean isValid(ExternalReference externalReference, ConstraintValidatorContext constraintContext) {
    Boolean isValid = true; 
    isValid = validateValues(externalReference);
    if(!isValid) {
      buildMessageTemplate(constraintContext);
    }
    return isValid;
  }

  private boolean validateValues(ExternalReference externalReference) {
    List<String> allowedKeys = externalReference.getType().getDistinctives();
    List<String> usedKeys = new ArrayList<String>(externalReference.getValues().keySet());
    usedKeys.removeAll(allowedKeys);
    addViolationMessage("Unrecognized Keys", usedKeys.stream().collect(Collectors.joining(",")));
    return usedKeys.isEmpty();
  }

  private void addViolationMessage(String violation, String message) {
    sb.append(String.format(MESSAGE_TEMPLATE, violation, message));
  }

  private void buildMessageTemplate(ConstraintValidatorContext constraintContext) {
    String message = constraintContext.getDefaultConstraintMessageTemplate();
    constraintContext.disableDefaultConstraintViolation();
    constraintContext.buildConstraintViolationWithTemplate(String.format(message, sb.toString())).addConstraintViolation();
    sb.setLength(0);
  };

}