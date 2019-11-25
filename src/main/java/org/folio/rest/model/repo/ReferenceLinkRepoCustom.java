package org.folio.rest.model.repo;

import java.util.stream.Stream;

import org.folio.rest.model.ReferenceLink;
import org.folio.rest.model.response.ReferenceLinkGroup;

public interface ReferenceLinkRepoCustom {

  public Stream<ReferenceLink> streamAllByTypeIdOrderByExternalReferenceAsc(String typeId);

  public Stream<ReferenceLinkGroup> streamAllByTypeIdGroupByTypeIdOrderByExternalReferenceAsc(String typeId,
      String groupByTypeId);

}