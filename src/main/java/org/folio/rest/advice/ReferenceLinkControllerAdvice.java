package org.folio.rest.advice;

import org.folio.rest.exception.ReferenceLinkExistsException;
import org.folio.rest.model.ReferenceLink;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ReferenceLinkControllerAdvice {

  @ExceptionHandler(value = ReferenceLinkExistsException.class)
  public @ResponseBody ResponseEntity<ReferenceLink> handleInvalidValuePathException(ReferenceLinkExistsException exception) {
    return ResponseEntity.accepted().body(exception.getReferenceLink());
  }

}
