package org.folio.rest.model.response;

public class JoinReferenceLink extends BaseReferenceLink {

  private final BaseReferenceLink reference;

  public JoinReferenceLink(String id, String typeId, String folioReference, String externalReference,
      String refId, String refTypeId, String refFolioReference, String refExternalReference) {
    super(id, typeId, folioReference, externalReference);
    this.reference = BaseReferenceLink.of(refId, refTypeId, refFolioReference, refExternalReference);
  }

  public BaseReferenceLink getReference() {
    return reference;
  }

}
