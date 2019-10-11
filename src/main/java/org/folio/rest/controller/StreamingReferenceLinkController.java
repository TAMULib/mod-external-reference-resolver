package org.folio.rest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;

import org.folio.rest.model.ReferenceLink;
import org.folio.rest.model.repo.ReferenceLinkRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/streaming/referenceLinks")
public class StreamingReferenceLinkController {

  private static final Logger logger = LoggerFactory.getLogger(ReferenceLinkController.class);

  @Autowired
  private ReferenceLinkRepo referenceLinkRepo;

  @Autowired
  private ObjectMapper objectMapper;

  @PersistenceContext
  private EntityManager entityManager;

  @Transactional(readOnly = true)
  @GetMapping(value = "/type/{id}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public void findAllByTypeId(HttpServletResponse response, @PathVariable String id) throws IOException {
    logger.info("Streaming all ExternalReference by type id: {}", id);
    try (Stream<ReferenceLink> links = referenceLinkRepo.streamAllByTypeId(id)) {
      PrintWriter out = response.getWriter();
      links.forEach(link -> {
        try {
          out.write(objectMapper.writeValueAsString(link));
          out.write("\n");
        } catch (JsonProcessingException e) {
          e.printStackTrace();
        }
      });
    }
  }

}