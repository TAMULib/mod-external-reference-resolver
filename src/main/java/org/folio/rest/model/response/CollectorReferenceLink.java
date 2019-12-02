package org.folio.rest.model.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectorReferenceLink {

  private String id;

  private String folioReference;

  private String externalReference;

  private String typeId;

  private List<String> references;

  public CollectorReferenceLink() {
    super();
    this.references = new ArrayList<String>();
  }

  public CollectorReferenceLink(String id, String typeId, String folioReference, String externalReference,
      String[] references) {
    this();
    this.id = id;
    this.typeId = typeId;
    this.folioReference = folioReference;
    this.externalReference = externalReference;
    this.references = Arrays.asList(references);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getTypeId() {
    return typeId;
  }

  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }

  public List<String> getReferences() {
    return references;
  }

  public void setReferences(List<String> references) {
    this.references = references;
  }

}
