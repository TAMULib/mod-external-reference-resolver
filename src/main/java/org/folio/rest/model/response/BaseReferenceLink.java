package org.folio.rest.model.response;

public class BaseReferenceLink {

  private final String id;

  private final String folioReference;

  private final String externalReference;

  private final String typeId;

  public BaseReferenceLink(String id, String typeId, String folioReference, String externalReference) {
    this.id = id;
    this.typeId = typeId;
    this.folioReference = folioReference;
    this.externalReference = externalReference;
  }

  public String getId() {
    return id;
  }

  public String getFolioReference() {
    return folioReference;
  }

  public String getExternalReference() {
    return externalReference;
  }

  public String getTypeId() {
    return typeId;
  }

}
