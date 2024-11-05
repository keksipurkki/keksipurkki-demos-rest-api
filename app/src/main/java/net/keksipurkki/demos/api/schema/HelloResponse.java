package net.keksipurkki.demos.api.schema;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "An example description for an API response")
public record HelloResponse(String message) {
}
