package ibn.api.health.api.model.out;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class GraficoDTO {

    private List<String> data = new ArrayList<>();
    private List<Double> valores = new ArrayList<>();
}
