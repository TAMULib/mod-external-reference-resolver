package org.folio.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.folio.rest.domain.model.AbstractBaseEntity;

@Entity
public class ReferenceLink extends AbstractBaseEntity {

  @NotNull
  @Column(unique = true, nullable = false)
  private String folioReference;

  @NotNull
  @Column(nullable = false)
  private String externalReference;

  @NotNull
  @ManyToOne
  private ReferenceLinkType type;

  public ReferenceLink() {
    super();
  }

  public String getFolioReference() {
    return folioReference;
  }

  public void setFolioReference(String folioReference) {
    this.folioReference = folioReference;
  }

  public String getExternalReference() {
    return externalReference;
  }

  public void setExternalReference(String externalReference) {
    this.externalReference = externalReference;
  }

  public ReferenceLinkType getType() {
    return type;
  }

  public void setType(ReferenceLinkType type) {
    this.type = type;
  }

}
