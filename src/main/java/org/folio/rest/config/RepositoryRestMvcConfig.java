package org.folio.rest.config;

import org.folio.rest.model.eventhandler.ExternalReferenceEventHandler;
import org.folio.rest.model.eventhandler.ExternalReferenceTypeEventHandler;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
public class RepositoryRestMvcConfig extends RepositoryRestMvcConfiguration {

  public RepositoryRestMvcConfig(ApplicationContext context, ObjectFactory<ConversionService> conversionService) {
    super(context, conversionService);
  }

    @Bean
    public ExternalReferenceEventHandler externalReferenceListener() {
        return new ExternalReferenceEventHandler();
    }

    @Bean
    public ExternalReferenceTypeEventHandler externalReferenceTypeListener() {
        return new ExternalReferenceTypeEventHandler();
    }

}
