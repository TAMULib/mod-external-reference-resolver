package org.folio.rest.model.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.folio.rest.model.ExternalReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExternalReferenceListener {

  private static final Logger logger = LoggerFactory.getLogger(ExternalReferenceListener.class);

  @PrePersist
  public void userPrePersist(ExternalReference er) {
    logger.info("PERSISTING" + er.toString());
    generateExternalReference(er);
  }
  
  @PreUpdate
  public void userPreUpdate(ExternalReference er) {
    logger.info("UPDATING" + er.toString());
    generateExternalReference(er);
  }

  private void generateExternalReference(ExternalReference er) {
    String erValue = String.join("_", er.getDistinctives().values());
    er.setExternalReference(erValue);
  }

}