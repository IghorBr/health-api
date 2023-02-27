package ibn.api.health.domain.service.impl;

import ibn.api.health.api.model.out.GraficoDTO;
import ibn.api.health.domain.service.GraficoService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GraficoServiceImpl implements GraficoService {

    private final EntityManager entityManager;

    @Override
    public GraficoDTO getGraficoInfo(String code, String valor) {
        GraficoDTO dto = new GraficoDTO();

        var stringBuilder = new StringBuilder("SELECT h.");

        stringBuilder
                .append(valor + ", ")
                .append("h.createdAt ")
                .append("FROM Historico h INNER JOIN h.usuario u WHERE u.code = :code")
        ;

        List<Object[]> resultList = entityManager.createQuery(stringBuilder.toString())
                .setParameter("code", code)
                .getResultList();

        for (Object[] obj : resultList) {
            OffsetDateTime date = (OffsetDateTime) obj[1];

            dto.getData().add(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            dto.getValores().add((Double) obj[0]);
        }

        return dto;
    }
}
