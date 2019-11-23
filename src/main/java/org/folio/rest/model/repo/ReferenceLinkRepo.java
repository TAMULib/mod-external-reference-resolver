package org.folio.rest.model.repo;

import java.util.List;
import java.util.Optional;

import org.folio.rest.model.ReferenceLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource
public interface ReferenceLinkRepo extends JpaRepository<ReferenceLink, String>, ReferenceLinkRepoCustom {

  public List<ReferenceLink> findAllByTypeName(String typeName);

  public List<ReferenceLink> findAllByTypeNameAndExternalReference(String typeName, String externalReference);

  public List<ReferenceLink> findAllByTypeId(String typeId);

  public List<ReferenceLink> findAllByTypeIdAndExternalReference(String typeId, String externalReference);

  public List<ReferenceLink> findAllByFolioReference(String folioReference);

  public List<ReferenceLink> findByTypeIdAndExternalReference(String typeId, String externalReference);

  public List<ReferenceLink> findByTypeIdAndFolioReference(String typeId, String folioReference);

  public Optional<ReferenceLink> findByTypeIdAndExternalReferenceAndFolioReference(String typeId,
      String externalReference, String folioReference);

  @Transactional
  public long deleteByTypeName(String typeName);

}