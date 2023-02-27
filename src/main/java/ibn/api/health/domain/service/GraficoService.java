package ibn.api.health.domain.service;

import ibn.api.health.api.model.out.GraficoDTO;

public interface GraficoService {

    GraficoDTO getGraficoInfo(String code, String valor);
}
