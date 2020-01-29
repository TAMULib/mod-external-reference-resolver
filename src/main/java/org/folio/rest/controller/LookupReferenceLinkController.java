package org.folio.rest.controller;

import java.util.List;

import org.folio.rest.model.ReferenceLink;
import org.folio.rest.model.repo.ReferenceLinkRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lookup/referenceLinks")
public class LookupReferenceLinkController {

  private static final Logger logger = LoggerFactory.getLogger(LookupReferenceLinkController.class);

  @Autowired
  private ReferenceLinkRepo referenceLinkRepo;

  @PostMapping(value = "/{typeName}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ReferenceLink> lookupByTypeNameAndExternalReferences(@PathVariable String typeName,
      @RequestBody List<String> externalReferences) {
    logger.info("Lookup by type {} and external references", typeName);
    return referenceLinkRepo.findAllByTypeNameAndExternalReferenceIn(typeName, externalReferences);
  }

}