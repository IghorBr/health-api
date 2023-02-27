package ibn.api.health.core.dev;

import ibn.api.health.domain.model.Historico;
import ibn.api.health.domain.model.Role;
import ibn.api.health.domain.model.Usuario;
import ibn.api.health.domain.service.HistoricoService;
import ibn.api.health.domain.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class DevConfiguration {

//    Usuario(String nome, String nomeDoMeio, String ultimoNome, String email, String senha, LocalDate dataNascimento, Role role)
//    Historico(String observacoes, Integer pressaoSistolica, Integer pressaoDiastolica, Integer bpm, Double glicose, Double altura, Double peso, Usuario usuario)

    @Bean
    public boolean initDatabase(PasswordEncoder passwordEncoder, UsuarioService usuarioService, HistoricoService historicoService) {
        Usuario u1 = new Usuario("Ighor Bruno", "Nascimento", "de Brito", "ighorbruno@email.com",
                passwordEncoder.encode("123"), LocalDate.of(2000, 5, 20), Role.GOD_LIKE);

        Usuario u2 = new Usuario("Ighor Bruno", "Nascimento", "de Brito", "ighor@email.com",
                passwordEncoder.encode("123"), LocalDate.of(2000, 5, 20), Role.ADMIN);

        Usuario u3 = new Usuario("Ighor Bruno", "Nascimento", "de Brito", "bruno@email.com",
                passwordEncoder.encode("123"), LocalDate.of(2000, 5, 20), Role.USER);


        u1 = usuarioService.save(u1);
        u2 = usuarioService.save(u2);
        u3 = usuarioService.save(u3);

        Historico h1 = new Historico("", 120, 80, 55., 14., 1.83, 74.4, u1);
        Historico h2 = new Historico("", 120, 80, 95., 213., 1.83, 84., u1);
        Historico h3 = new Historico("", 120, 80, 44., 31., 1.83, 66., u1);
        Historico h4 = new Historico("Observações 1", 130, 90, 95., 200., 1.83, 77., u2);
        Historico h5 = new Historico("Obs 2", 120, 80, 75., 143., 1.83, 74., u2);
        Historico h6 = new Historico("", 120, 80, 75., 143., 1.83, 74., u3);
        Historico h7 = new Historico("", 120, 80, 75., 143., 1.83, 74., u3);
        Historico h8 = new Historico("", 120, 80, 75., 143., 1.83, 74., u3);

        historicoService.save(h1);
        historicoService.save(h2);
        historicoService.save(h3);
        historicoService.save(h4);
        historicoService.save(h5);
        historicoService.save(h6);
        historicoService.save(h7);
        historicoService.save(h8);

        return true;
    }
}
