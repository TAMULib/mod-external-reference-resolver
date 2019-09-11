package org.folio.rest.model.eventhandler;

import org.folio.rest.model.ExternalReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

@RepositoryEventHandler(ExternalReference.class)
public class ExternalReferenceEventHandler {

  private static final Logger logger = LoggerFactory.getLogger(ExternalReferenceEventHandler.class);

  @HandleBeforeSave
  @HandleBeforeCreate
  private void generateExternalReference(ExternalReference er) {
    logger.debug("Saving ExternalReference to {}", er.getInternalReference());
    String erValue = er.getValues().size() > 0 ? String.join("_", er.getValues().values()) : "";
    er.setExternalReference(erValue);
  }

}