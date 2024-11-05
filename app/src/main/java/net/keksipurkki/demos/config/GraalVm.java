package net.keksipurkki.demos.config;

import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.QueryParameter;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@RegisterReflectionForBinding({
  QueryParameter.class,
  StringSchema.class,
  MediaType.class,
  ObjectSchema.class})
public class GraalVm {
  // Hack to workaround Swagger / GraalVM limitations
}
