package org.folio.rest.model.repo;

import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.folio.rest.model.ReferenceLink;

public class ReferenceLinkRepoImpl implements ReferenceLinkRepoCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Stream<ReferenceLink> streamAllByType(String typeId) {
    String query = String.format("SELECT r FROM ReferenceLink r WHERE r.type.id = '%s'", typeId);
    return entityManager.createQuery(query, ReferenceLink.class).getResultStream();
  }

}