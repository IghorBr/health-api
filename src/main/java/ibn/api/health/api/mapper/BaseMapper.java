package ibn.api.health.api.mapper;

import ibn.api.health.api.model.BaseDTO;
import ibn.api.health.domain.base.BaseDomain;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class BaseMapper<T extends BaseDomain, K extends BaseDTO> {

    private final Class<T> entity;
    private final Class<K> dto;
    private final ModelMapper mapper;

    protected BaseMapper(Class<T> entity, Class<K> dto, ModelMapper mapper) {
        this.entity = entity;
        this.dto = dto;
        this.mapper = mapper;
    }

    public K domainToDTO(T entity) {
        return mapper.map(entity, dto);
    }

    public List<K> domainListToDTO(List<T> entities) {
        return entities.stream().map(e -> this.domainToDTO(e)).toList();
    }
}
