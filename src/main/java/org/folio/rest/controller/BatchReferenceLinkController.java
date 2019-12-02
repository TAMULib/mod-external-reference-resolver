package org.folio.rest.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.folio.rest.model.ReferenceLink;
import org.folio.rest.model.repo.ReferenceLinkRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
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

  @PersistenceContext
  private EntityManager entityManager;

  @Value("${spring.jpa.properties.hibernate.jdbc.batch_size:50}")
  public int batchSize;

  @PostMapping
  @Transactional
  public List<ReferenceLink> batchCreate(@RequestBody List<ReferenceLink> referenceLinks) throws IOException {
    long startTime = System.nanoTime();
    int i = 0;
    for (ReferenceLink referenceLink : referenceLinks) {
      referenceLink = referenceLinkRepo.save(referenceLink);
      if (++i == batchSize) {
        i = 0;
        entityManager.flush();
        entityManager.clear();
      }
    }
    entityManager.flush();
    long stopTime = System.nanoTime();
    double duration = (stopTime - startTime) / (double) 1000000;
    logger.info("Created {} ReferenceLinks in {} milliseconds", referenceLinks.size(), duration);
    return referenceLinks;
  }

}