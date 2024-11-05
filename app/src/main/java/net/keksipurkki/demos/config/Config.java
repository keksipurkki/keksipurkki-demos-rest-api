package net.keksipurkki.demos.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.OpenAPI;
import net.keksipurkki.demos.api.ApiContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.TimeZone;

@Configuration
public class Config {

  private static final int JSON_PARSE_MAX_STRING_LENGTH = 1_000_000; // Harden from 20 MB default
  private static final int JSON_PARSE_MAX_NUMBER_LENGTH = StreamReadConstraints.DEFAULT_MAX_NUM_LEN;
  private static final int JSON_PARSE_MAX_DEPTH = StreamReadConstraints.DEFAULT_MAX_DEPTH;

  private final Environment env;

  private final Logger logger = LoggerFactory.getLogger(Config.class);

  public Config(Environment env) {
    this.env = env;
  }

  @Bean
  public FileSystemResource openApiYaml() {
    return new FileSystemResource("api.yaml");
  }

  @Bean
  public OpenAPI openAPI(FileSystemResource openApiYaml) {
    try {
      var yaml = openApiYaml.getContentAsString(StandardCharsets.UTF_8);
      var docs = ApiContract.openApi(yaml);
      var version = this.env.getProperty("maven.project.version", docs.getInfo().getVersion());
      logger.info("Read OpenAPI specification from {}. Version = {}", openApiYaml.getPath(), version);

      docs.getInfo().setVersion(version);
      return docs;

    } catch (IOException cause) {
      throw new IllegalStateException("Failed to read API docs from " + openApiYaml.getPath(), cause);
    }
  }

  @Bean
  public MappingJackson2HttpMessageConverter httpMessageConverter(ObjectMapper mapper) {
    var factory = mapper.getFactory().copy();

    var constraints = StreamReadConstraints.builder()
      .maxStringLength(JSON_PARSE_MAX_STRING_LENGTH)
      .maxNumberLength(JSON_PARSE_MAX_NUMBER_LENGTH)
      .maxNestingDepth(JSON_PARSE_MAX_DEPTH);

    factory.setStreamReadConstraints(constraints.build());

    var builder = new Jackson2ObjectMapperBuilder();

    builder.failOnUnknownProperties(false)
      .serializationInclusion(JsonInclude.Include.NON_NULL)
      .timeZone(TimeZone.getTimeZone("Europe/Helsinki")) // Always report time in Helsinki time in API responses
      .factory(factory);

    return new MappingJackson2HttpMessageConverter(builder.build());
  }

}
