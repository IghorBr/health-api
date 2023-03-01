package ibn.api.health.api.model.input;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class HistoricoInput implements Serializable {

    private String observacoes;
    private Integer pressaoSistolica;
    private Integer pressaoDiastolica;
    private Double bpm;
    private Double glicose;

    //EM METROS
    private Double altura;

    //EM QUILOS
    private Double peso;
}
