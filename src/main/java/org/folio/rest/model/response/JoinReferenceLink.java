package org.folio.rest.model.response;

import org.folio.rest.model.ReferenceLink;

public class JoinReferenceLink extends BaseReferenceLink {

  private final ReferenceLink reference;

  public JoinReferenceLink(String id, String typeId, String folioReference, String externalReference,
      ReferenceLink reference) {
    super(id, typeId, folioReference, externalReference);
    this.reference = reference;
  }

  public ReferenceLink getReference() {
    return reference;
  }

}
