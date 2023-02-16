package ibn.api.health.domain.model;

import ibn.api.health.domain.base.BaseDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Historico extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String observacoes;
    private Integer pressaoSistolica;
    private Integer pressaoDiastolica;
    private Integer bpm;
    private Double glicose;

    //EM METROS
    private Double altura;

    //EM QUILOS
    private Double peso;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
