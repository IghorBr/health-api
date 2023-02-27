package ibn.api.health.api.mapper;

import ibn.api.health.api.model.out.UsuarioDTO;
import ibn.api.health.api.model.out.UsuarioInfoDTO;
import ibn.api.health.api.model.out.UsuarioInfoDTO.HistoricoInfo;
import ibn.api.health.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioMapper extends BaseMapper<Usuario, UsuarioDTO> {

    protected UsuarioMapper(ModelMapper mapper) {
        super(Usuario.class, UsuarioDTO.class, mapper);
    }

    public UsuarioInfoDTO createUsuarioInfo(Usuario usuario) {
        var altura = usuario.getAltura();
        var peso = usuario.getPeso();

        List<HistoricoInfo> historicoInfo = new ArrayList<>();

        usuario.getHistoricos().forEach(uh -> {
            historicoInfo.add(new HistoricoInfo(uh.getCode(), uh.getCreatedAt()));
        });

        UsuarioInfoDTO info = UsuarioInfoDTO.builder()
                .nome(usuario.getNome())
                .nomeDoMeio(usuario.getNomeDoMeio())
                .ultimoNome(usuario.getUltimoNome())
                .email(usuario.getEmail())
                .dataNascimento(usuario.getDataNascimento())
                .pressaoSistolica(usuario.getMediaPressaoSistolica())
                .pressaoDiastolica(usuario.getMediaPressaoDiastolica())
                .bpm(usuario.getMediaBpm())
                .glicose(usuario.getMediaGlicose())
                .altura(altura)
                .peso(peso)
                .historicos(historicoInfo)
                .build();

        info.setCode(usuario.getCode());
        info.setCreatedAt(usuario.getCreatedAt());

        return info;
    }

}
