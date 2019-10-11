package org.folio.rest.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.folio.rest.model.ReferenceLink;
import org.folio.rest.model.repo.ReferenceLinkRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/streaming/referenceLinks")
public class StreamingReferenceLinkController {

  private static final Logger logger = LoggerFactory.getLogger(ReferenceLinkController.class);

  @Autowired
  private ReferenceLinkRepo referenceLinkRepo;

  @Autowired
  private ObjectMapper objectMapper;

  @GetMapping(value = "/type/{id}")
  @Transactional(readOnly = true)
  @ResponseBody
  public ResponseEntity<StreamingResponseBody> findAllByTypeId(@PathVariable String id) {
    logger.info("Streaming all ExternalReference by type id: {}", id);
    try(Stream<ReferenceLink> stream = referenceLinkRepo.findAllByTypeId(id)) {
      return ResponseEntity.ok(out -> streamAll(out, stream));
    }
    
  }

  public void streamAll(final OutputStream outputStream, Stream<ReferenceLink> stream) {
    try { 
      objectMapper.writeValue(outputStream, stream);
      outputStream.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // @GetMapping(value = "/type/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
  // public ResponseEntity<StreamingResponseBody> findAllByTypeName(@PathVariable String name) {
  //   logger.info("Finding all ExternalReference by type name: {}", name);
  //   return ResponseEntity.ok(referenceLinkRepo.findAllByTypeName(name));
  // }

}