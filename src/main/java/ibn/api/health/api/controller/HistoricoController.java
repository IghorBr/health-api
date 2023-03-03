package ibn.api.health.api.controller;

import ibn.api.health.api.mapper.HistoricoMapper;
import ibn.api.health.api.model.input.HistoricoInput;
import ibn.api.health.api.model.out.HistoricoDTO;
import ibn.api.health.core.security.HealthSecurity;
import ibn.api.health.domain.model.Historico;
import ibn.api.health.domain.model.Usuario;
import ibn.api.health.domain.service.HistoricoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/historicos")
@RequiredArgsConstructor
@SecurityRequirement(name = "security")
@Tag(name = "Históricos")
public class HistoricoController {

    private final HistoricoService historicoService;
    private final HistoricoMapper historicoMapper;
    private final HealthSecurity healthSecurity;

    @GetMapping("/{code}")
    @PreAuthorize("@healthSecurity.isAuthenticated()")
    @Operation(summary = "Retorna um único histórico por code")
    public ResponseEntity<HistoricoDTO> findHistoricoByCode(@PathVariable("code") String code) {
        Historico historico = historicoService.findByCode(code);
        HistoricoDTO dto = historicoMapper.domainToDTO(historico);


        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @PreAuthorize("@healthSecurity.isAuthenticated()")
    @Operation(summary = "Cria um novo histórico", description = "Cria um novo lançamento de histórico para o usuário logado")
    public ResponseEntity<Void> novoHistorico(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Representação de um novo histórico", required = true) @RequestBody HistoricoInput input
    ) {
        Usuario usuarioLogado = healthSecurity.getUsuarioLogado();

        Historico historico = historicoMapper.inputToDomain(usuarioLogado, input);
        historico = historicoService.save(historico);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
