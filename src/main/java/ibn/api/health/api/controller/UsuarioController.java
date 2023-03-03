package ibn.api.health.api.controller;

import ibn.api.health.api.mapper.UsuarioMapper;
import ibn.api.health.api.model.out.UsuarioDTO;
import ibn.api.health.api.model.out.UsuarioInfoDTO;
import ibn.api.health.core.security.HealthSecurity;
import ibn.api.health.domain.model.Usuario;
import ibn.api.health.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@SecurityRequirement(name = "security")
@Tag(name = "Usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;
    private final HealthSecurity security;

    @GetMapping
    @PreAuthorize("@healthSecurity.isAuthenticated()")
    @Operation(summary = "Retorna todos os usuários")
    public ResponseEntity<List<UsuarioDTO>> findAll(
            @Parameter(description = "Valor que define se o histórico será mostrado ou não", example = "true") @RequestParam(value = "showHistory", required = false, defaultValue = "false") Boolean showHistory
    ) {
        List<Usuario> usuarios = null;

        if (!security.isUser())
            usuarios = usuarioService.findAll();
        else
            usuarios = Arrays.asList(usuarioService.findByCode(security.getCode()));

        List<UsuarioDTO> dtos = usuarioMapper.domainListToDTO(usuarios);

        if (!showHistory)
            dtos.forEach(d -> d.setHistoricos(null));

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{code}")
    @PreAuthorize("@healthSecurity.isAuthenticated()")
    @Operation(summary = "Retorna um usuário por code")
    public ResponseEntity<UsuarioDTO> findByCode(@Parameter(description = "Código de um usuário", required = true) @PathVariable("code") String code) {
        security.verifyCode(code);

        Usuario usuario = usuarioService.findByCode(code);
        UsuarioDTO dto = usuarioMapper.domainToDTO(usuario);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/info")
    @PreAuthorize("@healthSecurity.isAuthenticated()")
    @Operation(summary = "Retorna informações sobre o usuário logado")
    public ResponseEntity<UsuarioInfoDTO> getUsuarioInfo() {
        String code = security.getCode();

        Usuario usuario = usuarioService.findByCode(code);
        UsuarioInfoDTO dto = usuarioMapper.createUsuarioInfo(usuario);

        return ResponseEntity.ok(dto);
    }

}
