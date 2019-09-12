package org.folio.rest.model.eventhandler;

import java.util.stream.Collectors;

import org.folio.rest.model.ExternalReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

@RepositoryEventHandler(ExternalReference.class)
public class ExternalReferenceEventHandler {

  private static final Logger logger = LoggerFactory.getLogger(ExternalReferenceEventHandler.class);

  @HandleBeforeCreate
  private void generateExternalReference(ExternalReference er) {
    logger.debug("Saving ExternalReference to {}", er.getFolioReference());
    String erValue =  String.join("_", er.getDistinctives().stream().map(r -> r.getValue()).collect(Collectors.toList()).toArray(new String[0]));
    er.setExternalReference(erValue);
  }

}