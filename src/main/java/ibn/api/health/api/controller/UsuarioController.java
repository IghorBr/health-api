package ibn.api.health.api.controller;

import ibn.api.health.api.mapper.UsuarioMapper;
import ibn.api.health.api.model.out.UsuarioDTO;
import ibn.api.health.core.security.HealthSecurity;
import ibn.api.health.domain.model.Usuario;
import ibn.api.health.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;
    private final HealthSecurity security;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll(
            @RequestParam(value = "showHistory", required = false, defaultValue = "false") Boolean showHistory
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
    public ResponseEntity<UsuarioDTO> findByCode(@PathVariable("code") String code) {
        security.verifyCode(code);

        Usuario usuario = usuarioService.findByCode(code);
        UsuarioDTO dto = usuarioMapper.domainToDTO(usuario);

        return ResponseEntity.ok(dto);
    }

}
