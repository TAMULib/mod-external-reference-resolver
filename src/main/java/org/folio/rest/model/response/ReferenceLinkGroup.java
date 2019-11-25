package org.folio.rest.model.response;

import java.io.Serializable;
import java.util.List;

import org.folio.rest.model.ReferenceLink;
import org.folio.rest.model.ReferenceLinkType;

public class ReferenceLinkGroup implements Serializable {

  private static final long serialVersionUID = 6005019427278268696L;

  private ReferenceLink link;

  private ReferenceLinkType groupBy;

  private List<ReferenceLink> references;

  public ReferenceLinkGroup() {
    super();
  }

  public ReferenceLinkGroup(ReferenceLink link, ReferenceLinkType groupBy, List<ReferenceLink> references) {
    super();
    this.link = link;
    this.groupBy = groupBy;
    this.references = references;
  }

  public ReferenceLink getLink() {
    return link;
  }

  public void setLink(ReferenceLink link) {
    this.link = link;
  }

  public ReferenceLinkType getGroupBy() {
    return groupBy;
  }

  public void setGroupBy(ReferenceLinkType groupBy) {
    this.groupBy = groupBy;
  }

  public List<ReferenceLink> getReferences() {
    return references;
  }

  public void setReferences(List<ReferenceLink> references) {
    this.references = references;
  }

  public static ReferenceLinkGroup of(ReferenceLink link, ReferenceLinkType groupBy, List<ReferenceLink> references) {
    return new ReferenceLinkGroup(link, groupBy, references);
  }

}
