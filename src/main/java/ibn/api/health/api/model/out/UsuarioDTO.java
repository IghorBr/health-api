package ibn.api.health.api.model.out;

import ibn.api.health.api.model.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class UsuarioDTO extends BaseDTO {

    private String nome;
    private String nomeDoMeio;
    private String ultimoNome;
    private String email;
    private LocalDate dataNascimento;
    private String role;

    private List<HistoricoDTO> historicos = new ArrayList<>();
}
