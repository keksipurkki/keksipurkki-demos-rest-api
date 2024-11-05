package net.keksipurkki.demos.api;

import io.swagger.v3.oas.models.OpenAPI;
import net.keksipurkki.demos.api.schema.HelloResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class Api implements ApiContract {

    private final OpenAPI openAPI;

    Api(OpenAPI openAPI) {
        this.openAPI = openAPI;
    }

    @GetMapping(value = "/api-docs.json")
    public OpenAPI apiDocumentation() {
        return openAPI;
    }

    @Override
    @GetMapping(value = "/hello")
    public HelloResponse getHello(@RequestParam(name = "message", required = false) String message) {
        return new HelloResponse(String.format("Hello %s", Objects.requireNonNullElse(message, "World")));
    }
}
