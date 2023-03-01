package ibn.api.health.api.controller;

import ibn.api.health.api.mapper.HistoricoMapper;
import ibn.api.health.api.model.input.HistoricoInput;
import ibn.api.health.api.model.out.HistoricoDTO;
import ibn.api.health.core.security.HealthSecurity;
import ibn.api.health.domain.model.Historico;
import ibn.api.health.domain.model.Usuario;
import ibn.api.health.domain.service.HistoricoService;
import ibn.api.health.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historicos")
@RequiredArgsConstructor
public class HistoricoController {

    private final HistoricoService historicoService;
    private final HistoricoMapper historicoMapper;
    private final HealthSecurity healthSecurity;

    @GetMapping("/{code}")
    public ResponseEntity<HistoricoDTO> findHistoricoByCode(@PathVariable("code") String code) {
        Historico historico = historicoService.findByCode(code);
        HistoricoDTO dto = historicoMapper.domainToDTO(historico);


        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> novoHistorico(@RequestBody HistoricoInput input) {
        Usuario usuarioLogado = healthSecurity.getUsuarioLogado();

        Historico historico = historicoMapper.inputToDomain(usuarioLogado, input);
        historico = historicoService.save(historico);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
