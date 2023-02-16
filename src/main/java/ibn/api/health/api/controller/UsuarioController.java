package ibn.api.health.api.controller;

import ibn.api.health.api.mapper.UsuarioMapper;
import ibn.api.health.api.model.out.UsuarioDTO;
import ibn.api.health.domain.model.Usuario;
import ibn.api.health.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll(
            @RequestParam(value = "showHistory", required = false, defaultValue = "false") Boolean showHistory
    ) {
        List<Usuario> usuarios = usuarioService.findAll();
        List<UsuarioDTO> dtos = usuarioMapper.domainListToDTO(usuarios);

        if (!showHistory)
            dtos.forEach(d -> d.setHistoricos(null));

        return ResponseEntity.ok(dtos);
    }
}
