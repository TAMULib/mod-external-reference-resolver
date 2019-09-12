package org.folio.rest.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.folio.rest.domain.model.AbstractBaseEntity;
import org.folio.rest.model.validation.ValidExternalReference;

@Entity
@ValidExternalReference
public class ExternalReference extends AbstractBaseEntity {

  @NotNull
  @Column(unique = true, nullable = false)
  private String folioReference;

  @NotNull
  @Column(unique = true)
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String externalReference;

  @ElementCollection
  private Set<ExternalReferenceValue> distinctives;

  @NotNull
  @ManyToOne
  private ExternalReferenceType type;

  public ExternalReference() {
    distinctives = new HashSet<ExternalReferenceValue>();
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

  public Set<ExternalReferenceValue> getDistinctives() {
    return distinctives;
  }

  public void setDistinctives(Set<ExternalReferenceValue> distinctives) {
    this.distinctives = distinctives;
  }

  public ExternalReferenceType getType() {
    return type;
  }

  public void setType(ExternalReferenceType type) {
    this.type = type;
  }

}
