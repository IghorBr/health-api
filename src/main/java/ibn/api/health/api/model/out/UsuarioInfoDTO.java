package ibn.api.health.api.model.out;

import ibn.api.health.api.model.BaseDTO;
import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class UsuarioInfoDTO extends BaseDTO {

    private String nome;
    private String nomeDoMeio;
    private String ultimoNome;
    private String email;
    private LocalDate dataNascimento;

    private Double pressaoSistolica;
    private Double pressaoDiastolica;
    private Double bpm;
    private Double glicose;
    private Double peso;
    private Double altura;
    private List<HistoricoInfo> historicos = new ArrayList<>();


    @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class HistoricoInfo {
        private String code;
        private OffsetDateTime createdAt;
    }
}
