package org.folio.rest.model.repo;

import java.util.stream.Stream;

import org.folio.rest.model.ReferenceLink;

public interface ReferenceLinkRepoCustom {

  Stream<ReferenceLink> streamAllByType(String typeId);

}