package org.folio.rest.model.repo;

import java.util.List;

import org.folio.rest.model.ExternalReference;
import org.folio.rest.model.ExternalReferenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ExternalReferenceRepo extends JpaRepository<ExternalReference, String> {

  public List<ExternalReference> findAllByType(ExternalReferenceType ert);

}