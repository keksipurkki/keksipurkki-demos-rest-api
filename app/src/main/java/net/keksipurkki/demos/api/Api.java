package net.keksipurkki.demos.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.OpenAPI;
import net.keksipurkki.demos.api.schema.HelloResponse;
import net.keksipurkki.demos.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@RestController
public class Api implements ApiContract {

  private final Logger logger = LoggerFactory.getLogger(Api.class);
  private final OpenAPI openAPI;

  Api(OpenAPI openAPI) {
    this.openAPI = openAPI;
  }

  @GetMapping(value = "/api-docs.json", produces = APPLICATION_JSON)
  public String apiDocumentation() throws JsonProcessingException {
    return Json.mapper().writer().writeValueAsString(openAPI);
  }

  @Override
  @GetMapping(value = "/hello")
  public HelloResponse getHello(@RequestParam(name = "message", required = false) String message) {
    logger.info("Executing GET /hello. Message = {}", message);
    return new HelloResponse(String.format("Hello %s", Objects.requireNonNullElse(message, "World")));
  }
}
