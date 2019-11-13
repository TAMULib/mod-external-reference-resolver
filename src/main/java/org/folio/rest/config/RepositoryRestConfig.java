package org.folio.rest.config;

import org.folio.rest.handler.ReferenceLinkEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryRestConfig {

  @Bean
  public ReferenceLinkEventHandler referenceLinkEventHandler() {
    return new ReferenceLinkEventHandler();
  }

}
