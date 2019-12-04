package org.folio.rest.model.repo;

import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.folio.rest.model.ReferenceLink;
import org.folio.rest.model.response.CollectorReferenceLink;

public class ReferenceLinkRepoImpl implements ReferenceLinkRepoCustom {

  private static final String ID = "id";
  private static final String TYPE = "type";
  private static final String FOLIO_REFERENCE = "folioReference";
  private static final String EXTERNAL_REFERENCE = "externalReference";

  private static final String ARRAY_AGG = "array_agg";

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Stream<ReferenceLink> streamAllByTypeIdOrderByExternalReferenceAsc(String typeId, String orderClass)
      throws ClassNotFoundException {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<ReferenceLink> cq = cb.createQuery(ReferenceLink.class);
    Root<ReferenceLink> link = cq.from(ReferenceLink.class);
    cq.where(cb.equal(link.get(TYPE).get(ID), typeId));
    cq.orderBy(cb.asc(link.get(EXTERNAL_REFERENCE).as(Class.forName(orderClass))));
    return entityManager.createQuery(cq).getResultStream();
  }

  @Override
  public Stream<CollectorReferenceLink> streamAllByTypeIdCollectingTypeIdOrderByExternalReferenceAsc(String typeId,
      String collectTypeId) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<CollectorReferenceLink> cq = cb.createQuery(CollectorReferenceLink.class);

    Root<ReferenceLink> link = cq.from(ReferenceLink.class);
    Root<ReferenceLink> references = cq.from(ReferenceLink.class);

    Expression<String[]> stringAgg = cb.function(ARRAY_AGG, String[].class, references.get(FOLIO_REFERENCE));

    // @formatter:off
    cq.select(cb.construct(CollectorReferenceLink.class,
        link.get(ID),
        link.get(TYPE).get(ID),
        link.get(FOLIO_REFERENCE),
        link.get(EXTERNAL_REFERENCE),
        stringAgg));

    cq.where(
      cb.and(cb.equal(link.get(ID), references.get(EXTERNAL_REFERENCE)),
      cb.equal(link.get(TYPE).get(ID), typeId),
      cb.equal(references.get(TYPE).get(ID), collectTypeId))
    );
    // @formatter:on

    cq.groupBy(link.get(ID));
    cq.orderBy(cb.asc(link.get(EXTERNAL_REFERENCE).as(String.class)));
    return entityManager.createQuery(cq).getResultStream();
  }

}