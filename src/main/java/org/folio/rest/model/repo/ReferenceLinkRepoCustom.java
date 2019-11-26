package org.folio.rest.model.repo;

import java.util.stream.Stream;

import org.folio.rest.model.ReferenceLink;
import org.folio.rest.model.response.ReferenceLinkWithCollect;

public interface ReferenceLinkRepoCustom {

  public Stream<ReferenceLink> streamAllByTypeIdOrderByExternalReferenceAsc(String typeId);

  public Stream<ReferenceLinkWithCollect> streamAllByTypeIdCollectingTypeIdOrderByExternalReferenceAsc(String typeId,
      String collectTypeId);

}