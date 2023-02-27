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
    private Double bpm;
    private Double glicose;

    //EM METROS
    private Double altura;

    //EM QUILOS
    private Double peso;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Historico(String observacoes, Integer pressaoSistolica, Integer pressaoDiastolica, Double bpm, Double glicose, Double altura, Double peso, Usuario usuario) {
        this.observacoes = observacoes;
        this.pressaoSistolica = pressaoSistolica;
        this.pressaoDiastolica = pressaoDiastolica;
        this.bpm = bpm;
        this.glicose = glicose;
        this.altura = altura;
        this.peso = peso;
        this.usuario = usuario;
        usuario.addHistorico(this);
    }
}
