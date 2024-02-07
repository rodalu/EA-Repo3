
package graphql.kickstart.autoconfigure.web.servlet.sample;

import graphql.Scalars;
import graphql.schema.DataFetcher;
import graphql.schema.FieldCoordinates;
import graphql.schema.GraphQLCodeRegistry;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;

/** @author Max Günther */
@SpringBootApplication
@EnableWebFlux
public class ApplicationWebfluxConfiguration {

  public static void main(String[] args) {
    SpringApplication.run(ApplicationWebfluxConfiguration.class, args);
  }

  @Bean
  GraphQLSchema schema() {
    DataFetcher<String> test = env -> "response";
    return GraphQLSchema.newSchema()
        .query(
            GraphQLObjectType.newObject()
                .name("query")
                .field(field -> field.name("test").type(Scalars.GraphQLString))
                .build())
        .codeRegistry(
            GraphQLCodeRegistry.newCodeRegistry()
                .dataFetcher(FieldCoordinates.coordinates("query", "test"), test)
                .build())
        .build();
  }
}