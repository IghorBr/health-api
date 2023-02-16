package ibn.api.health.domain.model;

import ibn.api.health.domain.base.BaseDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
}
