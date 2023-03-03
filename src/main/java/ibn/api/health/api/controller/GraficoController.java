package ibn.api.health.api.controller;

import ibn.api.health.api.model.out.GraficoDTO;
import ibn.api.health.core.security.HealthSecurity;
import ibn.api.health.domain.service.GraficoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/graficos")
@RequiredArgsConstructor
@SecurityRequirement(name = "security")
@Tag(name = "Dashboard")
public class GraficoController {

    private final GraficoService graficoService;
    private final HealthSecurity healthSecurity;

    @GetMapping
    @PreAuthorize("@healthSecurity.isAuthenticated()")
    @Operation(summary = "Retorna informações para montar um gráfico", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "valor inválido",
                content = @Content(schema = @Schema(ref = "Error"))
            )
    })
    public ResponseEntity<GraficoDTO> getGraficoData(@RequestParam("valor") final String valor) {
        String code = healthSecurity.getCode();
        GraficoDTO graficoInfo = graficoService.getGraficoInfo(code, valor);

        return ResponseEntity.ok(graficoInfo);
    }
}
