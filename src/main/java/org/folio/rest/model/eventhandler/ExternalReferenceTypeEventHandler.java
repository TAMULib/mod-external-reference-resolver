package org.folio.rest.model.eventhandler;

import java.util.List;

import org.folio.rest.model.ExternalReference;
import org.folio.rest.model.ExternalReferenceType;
import org.folio.rest.model.repo.ExternalReferenceRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

@RepositoryEventHandler(ExternalReference.class)
public class ExternalReferenceTypeEventHandler {

  private static final Logger logger = LoggerFactory.getLogger(ExternalReferenceTypeEventHandler.class);

  @Autowired
  private ExternalReferenceRepo erRepo;

  @HandleAfterSave
  private void updateExternalReferences(ExternalReferenceType ert) {
    logger.debug("Saving ExternalReferenceType: {}", ert.getName());
    List<ExternalReference> ers = erRepo.findAllByType(ert);
    ers.forEach(er->{
      String erValue = er.getValues().size() > 0 ? String.join("_", er.getValues().values()) : "";
      er.setExternalReference(erValue);
    });
  }

}