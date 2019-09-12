package org.folio.rest.model.repo;

import java.util.List;

import org.folio.rest.model.ExternalReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ExternalReferenceRepo extends JpaRepository<ExternalReference, String> {

  public List<ExternalReference> findAllByTypeName(@Param("typeName") String typeName);

  public List<ExternalReference> findAllByDistinctivesKey(@Param("key") String key);

  public List<ExternalReference> findAllByDistinctivesKeyIn(@Param("key") List<String> keys);

  public List<ExternalReference> findAllByDistinctivesValue(@Param("value") String value);

  public List<ExternalReference> findAllByDistinctivesValueIn(@Param("values") List<String> values);

  public List<ExternalReference> findAllByDistinctivesKeyAndDistinctivesValue(@Param("key") String key, @Param("value") String value);

  public List<ExternalReference> findAllByTypeNameAndDistinctivesKeyAndDistinctivesValue(@Param("typeName") String typeName, @Param("key") String key, @Param("value") String value);

  public List<ExternalReference> findAllByDistinctivesKeyAndDistinctivesValueIn(@Param("key") String key, @Param("values") List<String> values);

  public List<ExternalReference> findAllByTypeNameAndDistinctivesKeyAndDistinctivesValueIn(@Param("typeName") String typeName, @Param("key") String key, @Param("values") List<String> values);

  @Transactional
  public long deleteByTypeName(@Param("typeName") String typeName);

}