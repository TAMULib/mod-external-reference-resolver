package org.folio.rest.controller;

import java.util.List;

import org.folio.rest.model.ReferenceLink;
import org.folio.rest.model.repo.ReferenceLinkRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RepositoryRestController
@RequestMapping("/referenceLinks")
public class ReferenceLinkController implements ResourceProcessor<Resource<ReferenceLink>> {

  private static final Logger logger = LoggerFactory.getLogger(ReferenceLinkController.class);

  @Autowired
  private ReferenceLinkRepo referenceLinkRepo;

  @GetMapping("/type/{id}")
  public @ResponseBody ResponseEntity<List<ReferenceLink>> findAllByTypeId(@PathVariable String id) {
    logger.info("Finding all ExternalReference by type id: {}", id);
    return ResponseEntity.ok(referenceLinkRepo.findAllByTypeId(id));
  }

  @GetMapping("/type/name/{name}")
  public @ResponseBody ResponseEntity<List<ReferenceLink>> findAllByTypeName(@PathVariable String name) {
    logger.info("Finding all ExternalReference by type name: {}", name);
    return ResponseEntity.ok(referenceLinkRepo.findAllByTypeName(name));
  }

  @DeleteMapping("/type/name/{name}")
  public @ResponseBody ResponseEntity<Long> deleteAllByTypeName(@PathVariable String name) {
    logger.info("Deleting all ExternalReference by type name: {}", name);
    return ResponseEntity.ok(referenceLinkRepo.deleteByTypeName(name));
  }

  @Override
  public Resource<ReferenceLink> process(Resource<ReferenceLink> resource) {
    resource.add(
      ControllerLinkBuilder.linkTo(
        ControllerLinkBuilder
          .methodOn(ReferenceLinkController.class)
          .findAllByTypeName(resource.getContent().getType().getName())
      ).withRel("findAllByTypeName"),
      ControllerLinkBuilder.linkTo(
        ControllerLinkBuilder
          .methodOn(ReferenceLinkController.class)
          .findAllByTypeId(resource.getContent().getType().getId())
      ).withRel("findAllByTypeId")
    );
    return resource;
  }

}