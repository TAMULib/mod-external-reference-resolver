package org.folio.rest.model.repo;

import java.util.stream.Stream;

import org.folio.rest.model.ReferenceLink;
import org.folio.rest.model.response.CollectorReferenceLink;

public interface ReferenceLinkRepoCustom {

  public Stream<ReferenceLink> streamAllByTypeIdOrderByExternalReferenceAsc(String typeId);

  public Stream<CollectorReferenceLink> streamAllByTypeIdCollectingTypeIdOrderByExternalReferenceAsc(String typeId,
      String collectTypeId);

}