package org.folio.rest.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.folio.rest.model.ReferenceLink;
import org.folio.rest.model.repo.ReferenceLinkRepo;
import org.folio.rest.model.response.BatchReport;
import org.folio.rest.model.response.CollectorReferenceLink;
import org.folio.rest.model.response.JoinReferenceLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/referenceLinks")
public class ReferenceLinkController {

  private static final Logger logger = LoggerFactory.getLogger(ReferenceLinkController.class);

  @Autowired
  private ReferenceLinkRepo referenceLinkRepo;

  @Value("${data-extractor.batch.partition-size:10}")
  public int partitionSize;

  @PostMapping("/batch")
  @Transactional
  public BatchReport batchCreate(@RequestBody List<ReferenceLink> referenceLinks) throws IOException {
    long startTime = System.nanoTime();
    batches(referenceLinks, partitionSize).parallel().forEach(batch -> referenceLinkRepo.saveAll(batch));
    long stopTime = System.nanoTime();
    double duration = (stopTime - startTime) / (double) 1000000;
    logger.info("Created {} ReferenceLinks in {} milliseconds", referenceLinks.size(), duration);
    return new BatchReport(referenceLinks.size());
  }

  @PostMapping(value = "/list/{typeName}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ReferenceLink> lookupByTypeNameAndExternalReferences(@PathVariable String typeName,
      @RequestBody List<String> externalReferences) {
    logger.info("Lookup by type {} and external references", typeName);
    return referenceLinkRepo.findAllByTypeNameAndExternalReferenceIn(typeName, externalReferences);
  }

  @GetMapping(value = "/stream/{typeId}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Flux<ReferenceLink> streamAllByTypeId(@PathVariable String typeId,
      @RequestParam(defaultValue = "Integer") String orderClass) throws IOException, ClassNotFoundException {
    logger.info("Streaming all ExternalReference by type id {} order by class {}", typeId, orderClass);
    return Flux.fromStream(referenceLinkRepo.streamAllByTypeIdOrderByExternalReferenceAsc(typeId, orderClass));
  }

  @GetMapping(value = "/stream/{typeId}/collect/{collectTypeId}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Flux<CollectorReferenceLink> streamAllByTypeIdCollectingTypeId(@PathVariable String typeId,
      @PathVariable String collectTypeId, @RequestParam(defaultValue = "Integer") String orderClass)
      throws IOException, ClassNotFoundException {
    logger.info("Streaming all ExternalReference by type id {} collect type id {}", typeId, collectTypeId);
    return Flux.fromStream(referenceLinkRepo.streamAllByTypeIdCollectingTypeIdOrderByExternalReferenceAsc(typeId,
        collectTypeId, orderClass));
  }

  @GetMapping(value = "/stream/{typeId}/join/{joinTypeId}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Flux<JoinReferenceLink> streamAllByTypeIdJoiningTypeId(@PathVariable String typeId,
      @PathVariable String joinTypeId, @RequestParam(defaultValue = "Integer") String orderClass)
      throws IOException, ClassNotFoundException {
    logger.info("Streaming all ExternalReference by type id {} join type id {}", typeId, joinTypeId);
    return Flux.fromStream(
        referenceLinkRepo.streamAllByTypeIdJoiningTypeIdOrderByExternalReferenceAsc(typeId, joinTypeId, orderClass));
  }

  public static <T> Stream<List<T>> batches(List<T> source, int length) {
    if (length <= 0) {
      throw new IllegalArgumentException("length = " + length);
    }
    int size = source.size();
    if (size <= 0) {
      return Stream.empty();
    }
    int fullChunks = (size - 1) / length;
    return IntStream.range(0, fullChunks + 1)
        .mapToObj(n -> source.subList(n * length, n == fullChunks ? size : (n + 1) * length));
  }

}