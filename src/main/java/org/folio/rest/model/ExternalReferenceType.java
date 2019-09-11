package org.folio.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ExternalReferenceType {

  @Id
  @NotNull
  @Column(unique = true, nullable = false)
  private String name;

  @ElementCollection
  private List<String> distinctives;

  public ExternalReferenceType() {
    distinctives = new ArrayList<String>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getDistinctives() {
    return distinctives;
  }

  public void setDistinctives(List<String> distinctives) {
    this.distinctives = distinctives;
  }

}