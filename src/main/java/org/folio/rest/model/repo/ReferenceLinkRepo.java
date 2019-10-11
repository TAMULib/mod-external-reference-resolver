package org.folio.rest.model.repo;

import java.util.List;
import java.util.stream.Stream;

import org.folio.rest.model.ReferenceLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource
public interface ReferenceLinkRepo extends JpaRepository<ReferenceLink, String> {

  public List<ReferenceLink> findAllByTypeName(@Param("typeName") String typeName);

  public List<ReferenceLink> findAllByTypeNameAndExternalReference(@Param("typeName") String typeName,
      @Param("externalReference") String externalReference);

  public List<ReferenceLink> findAllByTypeId(@Param("typeId") String typeId);

  public List<ReferenceLink> findAllByTypeIdAndExternalReference(@Param("typeId") String typeId,
      @Param("externalReference") String externalReference);

  public List<ReferenceLink> findAllByFolioReference(@Param("folioReference") String folioReference);

  @Query("SELECT rl from ReferenceLink AS rl WHERE rl.type.id = :typeId")
  public Stream<ReferenceLink> streamAllByTypeId(@Param("typeId") String typeId);

  @Transactional
  public long deleteByTypeName(@Param("typeName") String typeName);

}