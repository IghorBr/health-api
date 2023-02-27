package ibn.api.health.api.controller;

import ibn.api.health.api.model.out.GraficoDTO;
import ibn.api.health.core.security.HealthSecurity;
import ibn.api.health.domain.service.GraficoService;
import ibn.api.health.domain.service.HistoricoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/graficos")
@RequiredArgsConstructor
public class GraficoController {

    private final GraficoService graficoService;
    private final HealthSecurity healthSecurity;

    @GetMapping
    public ResponseEntity<GraficoDTO> getGraficoData(@RequestParam("valor") final String valor) {
        String code = healthSecurity.getCode();
        GraficoDTO graficoInfo = graficoService.getGraficoInfo(code, valor);

        return ResponseEntity.ok(graficoInfo);
    }
}
