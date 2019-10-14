package org.folio.rest.controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.folio.rest.model.ReferenceLink;
import org.folio.rest.model.repo.ReferenceLinkRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/streaming/referenceLinks")
public class StreamingReferenceLinkController {

  private static final Logger logger = LoggerFactory.getLogger(StreamingReferenceLinkController.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private ReferenceLinkRepo referenceLinkRepo;

  @GetMapping(value = "/type/{typeId}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Flux<ReferenceLink> streamAllByTypeId(@PathVariable String typeId) throws IOException {
    logger.info("Streaming all ExternalReference by type id: {}", typeId);
    return Flux.fromStream(referenceLinkRepo.streamAllByType(typeId));
  }

}