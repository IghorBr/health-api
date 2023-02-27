package ibn.api.health.domain.model;

import ibn.api.health.domain.base.BaseDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Usuario extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    private String nomeDoMeio;

    @Column(nullable = false)
    private String ultimoNome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "usuario")
    private List<Historico> historicos = new ArrayList<>();

    public boolean addHistorico(Historico... historicos) {
        return this.historicos.addAll(Arrays.asList(historicos));
    }

    public Usuario(String nome, String nomeDoMeio, String ultimoNome, String email, String senha, LocalDate dataNascimento, Role role) {
        this.nome = nome;
        this.nomeDoMeio = nomeDoMeio;
        this.ultimoNome = ultimoNome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.role = role;
    }

    private Historico getUltimoHistorico() {
        OffsetDateTime menor = OffsetDateTime.of(LocalDateTime.of(2000,1, 1, 0, 0), ZoneOffset.UTC);
        Historico ultimo = null;

        for (Historico h : this.historicos) {
            if (h.getCreatedAt().compareTo(menor) >= 0) {
                menor = h.getCreatedAt();
                ultimo = h;
            }
        }

        return ultimo;
    }

    public Double getAltura() {
        return getUltimoHistorico().getAltura();
    }

    public Double getPeso() {
        return getUltimoHistorico().getPeso();
    }

    public Double getMediaPressaoDiastolica() {
        int soma = 0;

        for (Historico h : this.historicos) {
            soma += h.getPressaoDiastolica();
        }

        return (double) (soma / this.historicos.size());
    }

    public Double getMediaPressaoSistolica() {
        int soma = 0;

        for (Historico h : this.historicos) {
            soma += h.getPressaoSistolica();
        }

        return (double) (soma / this.historicos.size());
    }

    public Double getMediaBpm() {
        int soma = 0;

        for (Historico h : this.historicos) {
            soma += h.getBpm();
        }

        return (double) (soma / this.historicos.size());
    }

    public Double getMediaGlicose() {
        int soma = 0;

        for (Historico h : this.historicos) {
            soma += h.getGlicose();
        }

        return (double) (soma / this.historicos.size());
    }
}
