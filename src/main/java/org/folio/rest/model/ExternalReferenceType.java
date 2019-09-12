package org.folio.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.folio.rest.domain.model.AbstractBaseEntity;

@Entity
public class ExternalReferenceType extends AbstractBaseEntity {

  @NotNull
  @Column(unique = true, nullable = false)
  private String name;

  @ElementCollection
  private List<String> allowedKeys;

  public ExternalReferenceType() {
    allowedKeys = new ArrayList<String>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getAllowedKeys() {
    return allowedKeys;
  }

  public void setAllowedKeys(List<String> allowedKeys) {
    this.allowedKeys = allowedKeys;
  }

}