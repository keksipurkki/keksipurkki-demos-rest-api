package net.keksipurkki.demos.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.core.util.Yaml;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import net.keksipurkki.demos.api.schema.HelloResponse;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@OpenAPIDefinition(
    info = @Info(
        title = "My little REST API",
        version = "1.0.0",
        description = """
                      An opinionated way of programming REST APIs
                      """
    ),
    servers = {
        @Server(url = "http://localhost:8080/api/v1")
    }
)
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public interface ApiContract {

    @GET
    @Path("hello")
    @Operation(
        operationId = "GET_GREETING",
        description = """
                      A description for this operation
                      """
    )
    HelloResponse getHello(@Parameter(in = ParameterIn.QUERY, required = false) String message);

    static OpenAPI openApi(String yaml) throws JsonProcessingException {
        return Yaml.mapper().readValue(yaml, OpenAPI.class);
    }

}
