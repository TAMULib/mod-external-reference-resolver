#Prerequisites JDK
FROM maven:3.6.1-jdk-8-alpine

#Settings
ENV ARTIFACT_VERSION='1.3.0-SNAPSHOT'
ENV MODULE_VERSION='sprint4-staging'
ENV LOGGING_LEVEL_FOLIO='INFO'
ENV SERVER_PORT='8081'
ENV SPRING_DATASOURCE_PLATFORM='h2'
ENV SPRING_DATASOURCE_URL='jdbc:h2:./target/mod-external-reference-resolver;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE'
ENV SPRING_DATASOURCE_DRIVERCLASSNAME='org.h2.Driver'
ENV SPRING_DATASOURCE_USERNAME='folio'
ENV SPRING_DATASOURCE_PASSWORD='folio'
ENV SPRING_H2_CONSOLE_ENABLED='true'
ENV SPRING_JPA_DATABASE_PLATFORM='org.hibernate.dialect.H2Dialect'
ENV TENANT_DEFAULT_TENANT='tern'

#expose port
EXPOSE ${SERVER_PORT}

#Mvn
RUN apk add --no-cache curl git

#mod-external-reference-resolver clone and MVN build
RUN mkdir -p /usr/local/bin/folio/
WORKDIR /usr/local/bin/folio
RUN git clone -b ${MODULE_VERSION} https://github.com/TAMULib/mod-external-reference-resolver.git
WORKDIR /usr/local/bin/folio/mod-external-reference-resolver
RUN mvn package -DskipTests

#run java command
CMD java -jar /usr/local/bin/folio/mod-external-reference-resolver/target/mod-external-reference-resolver-${ARTIFACT_VERSION}.jar \
    --logging.level.org.folio=${LOGGING_LEVEL_FOLIO} --server.port=${SERVER_PORT} --spring.datasource.platform=${SPRING_DATASOURCE_PLATFORM} \
    --spring.datasource.url=${SPRING_DATASOURCE_URL} --spring.datasource.driverClassName=${SPRING_DATASOURCE_DRIVERCLASSNAME} \
    --spring.datasource.username=${SPRING_DATASOURCE_USERNAME} --spring.datasource.password=${SPRING_DATASOURCE_PASSWORD} \
    --spring.h2.console.enabled=${SPRING_H2_CONSOLE_ENABLED} --spring.jpa.database-platform=${SPRING_JPA_DATABASE_PLATFORM} \
    --tenant.default-tenant=${TENANT_DEFAULT_TENANT}