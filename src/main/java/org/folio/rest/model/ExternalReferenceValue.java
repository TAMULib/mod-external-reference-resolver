package org.folio.rest.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ExternalReferenceValue {

  @NotNull
  @Column(nullable = false)
  private String key;

  @NotNull
  @Column(nullable = false)
  private String value;

  public ExternalReferenceValue() {
    
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}