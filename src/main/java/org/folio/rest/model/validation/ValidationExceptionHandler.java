package org.folio.rest.model.validation;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ValidationExceptionHandler {
  
  @Autowired
  ObjectMapper objectMapper;
  static final String MESSAGE_TEMPLATE = "%s: %s";
  
  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler(TransactionSystemException.class)
  public @ResponseBody String handleValidationException(TransactionSystemException exception) {
    try {
      ConstraintViolationException cve = (ConstraintViolationException)exception.getCause().getCause();
      Map<String, Object> validationExceptionResponse = new HashMap<String,Object>();
      Map<String, String> violations = new HashMap<String,String>();
      for (ConstraintViolation<?> violation : cve.getConstraintViolations()) {
        if(violation.getPropertyPath().toString().isEmpty()) {
          validationExceptionResponse.put("type", violation.getMessageTemplate());
        } else {
          violations.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
      }
      validationExceptionResponse.put("violations", violations);
      return objectMapper.writeValueAsString(validationExceptionResponse);
    } catch(Exception e) {
      return exception.getMessage();
    }
  }
}