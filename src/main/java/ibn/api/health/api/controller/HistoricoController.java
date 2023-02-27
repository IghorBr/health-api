package ibn.api.health.api.controller;

import ibn.api.health.api.mapper.HistoricoMapper;
import ibn.api.health.api.model.out.HistoricoDTO;
import ibn.api.health.core.security.HealthSecurity;
import ibn.api.health.domain.model.Historico;
import ibn.api.health.domain.service.HistoricoService;
import ibn.api.health.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
