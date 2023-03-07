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

    HistoricoInput(@NotNull String observacoes, @NotNull Integer pressaoSistolica, @NotNull Integer pressaoDiastolica, @NotNull Double bpm, @NotNull Double glicose, @NotNull Double altura, @NotNull Double peso) {
        this.observacoes = observacoes;
        this.pressaoSistolica = pressaoSistolica;
        this.pressaoDiastolica = pressaoDiastolica;
        this.bpm = bpm;
        this.glicose = glicose;
        this.altura = altura;
        this.peso = peso;
    }

    public static HistoricoInputBuilder builder() {
        return new HistoricoInputBuilder();
    }

    public static class HistoricoInputBuilder {
        private @NotNull String observacoes;
        private @NotNull Integer pressaoSistolica;
        private @NotNull Integer pressaoDiastolica;
        private @NotNull Double bpm;
        private @NotNull Double glicose;
        private @NotNull Double altura;
        private @NotNull Double peso;

        HistoricoInputBuilder() {
        }

        public HistoricoInputBuilder observacoes(@NotNull String observacoes) {
            this.observacoes = observacoes;
            return this;
        }

        public HistoricoInputBuilder pressaoSistolica(@NotNull Integer pressaoSistolica) {
            this.pressaoSistolica = pressaoSistolica;
            return this;
        }

        public HistoricoInputBuilder pressaoDiastolica(@NotNull Integer pressaoDiastolica) {
            this.pressaoDiastolica = pressaoDiastolica;
            return this;
        }

        public HistoricoInputBuilder bpm(@NotNull Double bpm) {
            this.bpm = bpm;
            return this;
        }

        public HistoricoInputBuilder glicose(@NotNull Double glicose) {
            this.glicose = glicose;
            return this;
        }

        public HistoricoInputBuilder altura(@NotNull Double altura) {
            this.altura = altura;
            return this;
        }

        public HistoricoInputBuilder peso(@NotNull Double peso) {
            this.peso = peso;
            return this;
        }

        public HistoricoInput build() {
            return new HistoricoInput(observacoes, pressaoSistolica, pressaoDiastolica, bpm, glicose, altura, peso);
        }

        public String toString() {
            return "HistoricoInput.HistoricoInputBuilder(observacoes=" + this.observacoes + ", pressaoSistolica=" + this.pressaoSistolica + ", pressaoDiastolica=" + this.pressaoDiastolica + ", bpm=" + this.bpm + ", glicose=" + this.glicose + ", altura=" + this.altura + ", peso=" + this.peso + ")";
        }
    }
}
