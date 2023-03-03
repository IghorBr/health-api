package ibn.api.health.core.springdoc;

import ibn.api.health.api.exception_handler.Error;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
@SecurityScheme(
        name = "security",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(authorizationCode = @OAuthFlow(
                authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}",
                tokenUrl = "${springdoc.oAuthFlow.tokenUrl}",
                scopes = {
                        @OAuthScope(name = "READ", description = "read scope"),
                        @OAuthScope(name = "WRITE", description = "WRITE scope")
                }
        ))
)
public class SpringDocConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Health API")
                                .version("Alpha")
                                .description("REST API para diário de dados sobre saúde pessoal")
                                .license(
                                        new License()
                                                .name("Health")
                                )
                )
                .tags(Arrays.asList(
                        new Tag().name("Dashboard").description("Gerencia informações para geração de gráficos"),
                        new Tag().name("Históricos").description("Gerencia históricos"),
                        new Tag().name("Usuários").description("Gerencia usuários")

                ))
                .components(new Components()
                        .schemas(gerarSchemas())
                        .responses(gerarResponses())
                )

                ;
    }

    @Bean
    public OpenApiCustomizer openApiCustomizer() {
        return openApi -> {
            openApi.getPaths()
                    .values()
                    .forEach(pathItem -> pathItem.readOperationsMap()
                            .forEach(((httpMethod, operation) -> {
                                ApiResponses responses = operation.getResponses();

                                switch (httpMethod) {
                                    case GET:
                                        responses.addApiResponse("404", new ApiResponse().$ref("NotFoundResponse"));
                                        responses.addApiResponse("406", new ApiResponse().$ref("RepresentacaoInvalidaResponse"));
                                        responses.addApiResponse("500", new ApiResponse().$ref("InternalErrorResponse"));
                                        break;
                                    case POST:
                                        responses.addApiResponse("400", new ApiResponse().$ref("BadRequestResponse"));
                                        responses.addApiResponse("500", new ApiResponse().$ref("InternalErrorResponse"));
                                        break;
                                    case PUT:
                                        responses.addApiResponse("404", new ApiResponse().$ref("NotFoundResponse"));
                                        responses.addApiResponse("400", new ApiResponse().$ref("BadRequestResponse"));
                                        responses.addApiResponse("500", new ApiResponse().$ref("InternalErrorResponse"));
                                        break;
                                    case DELETE:
                                        responses.addApiResponse("404", new ApiResponse().$ref("NotFoundResponse"));
                                        responses.addApiResponse("500", new ApiResponse().$ref("InternalErrorResponse"));
                                        break;
                                    default:
                                        responses.addApiResponse("500", new ApiResponse().$ref("InternalErrorResponse"));
                                        break;
                                }
                            }))
                    );

        };
    }

    private Map<String, Schema> gerarSchemas() {
        final Map<String, Schema> schemaMap = new HashMap<>();
        Map<String, Schema> errorSchema = ModelConverters.getInstance().read(Error.class);

        schemaMap.putAll(errorSchema);
        return schemaMap;
    }

    private Map<String, ApiResponse> gerarResponses() {
        final Map<String, ApiResponse> responseMap = new HashMap<>();

        Content content = new Content()
                .addMediaType(APPLICATION_JSON_VALUE, new MediaType().schema(new Schema<Error>().$ref("Error")));

        responseMap.put("BadRequestResponse", new ApiResponse().description("Requisição inválida").content(content));
        responseMap.put("NotFoundResponse", new ApiResponse().description("Recurso não encontrado").content(content));
        responseMap.put("RepresentacaoInvalidaResponse", new ApiResponse().description("Recurso não possui representação").content(content));
        responseMap.put("InternalErrorResponse", new ApiResponse().description("Erro Interno").content(content));

        return responseMap;
    }
}
