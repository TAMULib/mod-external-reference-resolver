package org.folio.rest.model.repo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.folio.rest.model.ReferenceLink;
import org.folio.rest.model.ReferenceLinkType;
import org.folio.rest.model.response.ReferenceLinkGroup;
import org.springframework.beans.factory.annotation.Autowired;

public class ReferenceLinkRepoImpl implements ReferenceLinkRepoCustom {

  private static final String ID = "id";
  private static final String TYPE = "type";
  private static final String EXTERNAL_REFERENCE = "externalReference";

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private ReferenceLinkRepo referenceLinkRepo;

  @Autowired
  private ReferenceLinkTypeRepo referenceLinkTypeRepo;

  @Override
  public Stream<ReferenceLink> streamAllByTypeIdOrderByExternalReferenceAsc(String typeId) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<ReferenceLink> cq = cb.createQuery(ReferenceLink.class);
    Root<ReferenceLink> link = cq.from(ReferenceLink.class);
    cq.where(cb.equal(link.get(TYPE).get(ID), typeId));
    cq.orderBy(cb.asc(link.get(EXTERNAL_REFERENCE).as(String.class)));
    return entityManager.createQuery(cq).getResultStream();
  }

  @Override
  public Stream<ReferenceLinkGroup> streamAllByTypeIdGroupByTypeIdOrderByExternalReferenceAsc(String typeId, String groupByTypeId) {
    Optional<ReferenceLinkType> groupBy = referenceLinkTypeRepo.findById(groupByTypeId);
    if (!groupBy.isPresent()) {

    }
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<ReferenceLink> cq = cb.createQuery(ReferenceLink.class);
    Root<ReferenceLink> link = cq.from(ReferenceLink.class);
    cq.where(cb.equal(link.get(TYPE).get(ID), typeId));
    cq.orderBy(cb.asc(link.get(EXTERNAL_REFERENCE).as(String.class)));
    return entityManager.createQuery(cq).getResultStream().map(rl -> {
      List<ReferenceLink> references = referenceLinkRepo.findByTypeIdAndExternalReference(typeId, rl.getId());
      return ReferenceLinkGroup.of(rl, groupBy.get(), references);
    });
  }

}