package ibn.api.health.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class HistoricoInput implements Serializable {

    @NotNull
    @Schema(example = "Me sentindo bem")
    private String observacoes;

    @NotNull
    @Schema(example = "120")
    private Integer pressaoSistolica;

    @NotNull
    @Schema(example = "80")
    private Integer pressaoDiastolica;

    @NotNull
    @Schema(example = "95")
    private Double bpm;

    @NotNull
    @Schema(example = "98")
    private Double glicose;

    //EM METROS
    @NotNull
    @Schema(example = "1.83")
    private Double altura;

    //EM QUILOS
    @NotNull
    @Schema(example = "75")
    private Double peso;
}
