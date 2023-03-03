package ibn.api.health.api.exception_handler;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.OffsetDateTime;

@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Schema(name = "Error")
public class Error {

    @Schema(example = "/usuarios/{code}")
    private String path;

    @Schema(example = "400")
    private Integer status;

    @Schema(example = "Erro ao recuperar usuário")
    private String description;
    private OffsetDateTime timestamp;
}
