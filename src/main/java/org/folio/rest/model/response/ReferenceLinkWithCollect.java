package org.folio.rest.model.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.folio.rest.model.ReferenceLink;
import org.springframework.beans.BeanUtils;

public class ReferenceLinkWithCollect extends ReferenceLink {

  private List<String> references;

  public ReferenceLinkWithCollect() {
    super();
    this.references = new ArrayList<String>();
  }

  public ReferenceLinkWithCollect(ReferenceLink referenceLink, String[] references) {
    this();
    BeanUtils.copyProperties(referenceLink, this);
    this.references = Arrays.asList(references);
  }

  public List<String> getReferences() {
    return references;
  }

  public void setReferences(List<String> references) {
    this.references = references;
  }

}
