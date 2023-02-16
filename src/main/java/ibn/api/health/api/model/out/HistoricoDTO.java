package ibn.api.health.api.model.out;

import ibn.api.health.api.model.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class HistoricoDTO extends BaseDTO {

    private String observacoes;
    private Integer pressaoSistolica;
    private Integer pressaoDiastolica;
    private Integer bpm;
    private Double glicose;

    //EM METROS
    private Double altura;

    //EM QUILOS
    private Double peso;
}
