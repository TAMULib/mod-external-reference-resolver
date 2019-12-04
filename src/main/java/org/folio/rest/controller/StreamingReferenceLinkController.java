package org.folio.rest.controller;

import java.io.IOException;

import org.folio.rest.model.ReferenceLink;
import org.folio.rest.model.repo.ReferenceLinkRepo;
import org.folio.rest.model.response.CollectorReferenceLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/streaming/referenceLinks")
public class StreamingReferenceLinkController {

  private static final Logger logger = LoggerFactory.getLogger(StreamingReferenceLinkController.class);

  @Autowired
  private ReferenceLinkRepo referenceLinkRepo;

  @GetMapping(value = "/type/{typeId}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Flux<ReferenceLink> streamAllByTypeId(@PathVariable String typeId,
      @RequestParam(defaultValue = "Integer") String orderClass) throws IOException, ClassNotFoundException {
    logger.info("Streaming all ExternalReference by type id {} order by class {}", typeId, orderClass);
    return Flux.fromStream(referenceLinkRepo.streamAllByTypeIdOrderByExternalReferenceAsc(typeId, orderClass));
  }

  @GetMapping(value = "/type/{typeId}/collect/{collectTypeId}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Flux<CollectorReferenceLink> streamAllByTypeIdCollectingTypeId(@PathVariable String typeId,
      @PathVariable String collectTypeId) throws IOException {
    logger.info("Streaming all ExternalReference by type id {} collect type id {}", typeId, collectTypeId);
    return Flux.fromStream(
        referenceLinkRepo.streamAllByTypeIdCollectingTypeIdOrderByExternalReferenceAsc(typeId, collectTypeId));
  }

}