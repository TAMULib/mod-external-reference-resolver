package org.folio.rest.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.folio.rest.model.validation.ValidExternalReference;

@Entity
@ValidExternalReference
public class ExternalReference {

  @Id
  @NotNull
  @Column(unique = true, nullable = false)
  private String internalReference;

  @Column(unique = true)
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  @NotNull
  private String externalReference;

  @ElementCollection
  @MapKeyColumn(name="distinctive")
  @Column(name="value")
  private Map<String,String> values;

  @NotNull
  @ManyToOne
  private ExternalReferenceType type;

  public ExternalReference() {
    values = new HashMap<String,String>();
  }

  public ExternalReferenceType getType() {
    return type;
  }

  public void setType(ExternalReferenceType type) {
    this.type = type;
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

  public Map<String, String> getValues() {
    return values;
  }

  public void setValues(Map<String, String> values) {
    this.values = values;
  }

}
