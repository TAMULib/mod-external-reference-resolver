package org.folio.rest.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotNull;

import org.folio.rest.domain.model.AbstractBaseEntity;
import org.folio.rest.model.listener.ExternalReferenceListener;
import org.springframework.data.annotation.Version;

@Entity
@EntityListeners(ExternalReferenceListener.class)
public class ExternalReference extends AbstractBaseEntity {

  @NotNull
  @Column(unique = true)
  private String internalReference;

  @NotNull
  @Column(unique = true)
  private String externalReference;

  @ElementCollection
  @Version
  private Map<String,String> distinctives;

  @NotNull
  private String type;

  public ExternalReference() {
    distinctives = new HashMap<String,String>();
  }

  public String getInternalReference() {
    return internalReference;
  }

  public void setInternalReference(String internalReference) {
    this.internalReference = internalReference;
  }

  public String getExternalReference() {
    return externalReference;
  }

  public void setExternalReference(String externalReference) {
    this.externalReference = externalReference;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

public Map<String, String> getDistinctives() {
	return distinctives;
}

public void setDistinctives(Map<String, String> distinctives) {
	this.distinctives = distinctives;
}

}
