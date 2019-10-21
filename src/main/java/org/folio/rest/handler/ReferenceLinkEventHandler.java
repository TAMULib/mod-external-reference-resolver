package org.folio.rest.handler;

import java.util.Optional;

import org.folio.rest.exception.ReferenceLinkExistsException;
import org.folio.rest.model.ReferenceLink;
import org.folio.rest.model.repo.ReferenceLinkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

@RepositoryEventHandler(ReferenceLink.class)
public class ReferenceLinkEventHandler {

  @Autowired
  private ReferenceLinkRepo referenceLinkRepo;

  @HandleBeforeCreate
  public void handleReferenceLinkBeforeCreate(ReferenceLink referenceLink){
    Optional<ReferenceLink> existingReferenceLink = referenceLinkRepo.findByTypeIdAndExternalReference(referenceLink.getType().getId(), referenceLink.getExternalReference());

    if (existingReferenceLink.isPresent()) {
      throw new ReferenceLinkExistsException(existingReferenceLink.get());
    }
  }
}
