package org.folio.rest.controller;

import java.io.IOException;
import java.util.List;

import org.folio.rest.model.ReferenceLink;
import org.folio.rest.model.repo.ReferenceLinkRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch/referenceLinks")
public class BatchReferenceLinkController {

  private static final Logger logger = LoggerFactory.getLogger(BatchReferenceLinkController.class);

  @Autowired
  private ReferenceLinkRepo referenceLinkRepo;

  @PostMapping
  public List<ReferenceLink> batchCreate(@RequestBody List<ReferenceLink> referenceLinks) throws IOException {
    long startTime = System.nanoTime();
    referenceLinks = referenceLinkRepo.saveAll(referenceLinks);
    long stopTime = System.nanoTime();
    double duration = (stopTime - startTime) / (double) 1000000;
    logger.info("Created {} ReferenceLinks in {} milliseconds", referenceLinks.size(), duration);
    return referenceLinks;
  }

}