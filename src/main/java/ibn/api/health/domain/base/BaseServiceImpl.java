package ibn.api.health.domain.base;

import ibn.api.health.core.security.HealthSecurity;
import ibn.api.health.domain.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class BaseServiceImpl<T extends BaseDomain> implements BaseService<T> {

    private final BaseRepository<T> baseRepository;
    private final Class<T> entity;
    private final HealthSecurity security;

    protected BaseServiceImpl(BaseRepository<T> baseRepository, Class<T> entity, HealthSecurity security) {
        this.baseRepository = baseRepository;
        this.entity = entity;
        this.security = security;
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public T findById(Long id) {
        return baseRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Entity %s of id %d not found!", entity.getSimpleName(), id)));
    }

    @Override
    public T findByCode(String code) {
        return baseRepository.findByCode(code)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Entity %s of code %s not found!", entity.getSimpleName(), code)));
    }

    @Override
    public T save(T entity) {
        return baseRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        baseRepository.deleteById(id);
    }

    @Override
    public void deleteByCode(String code) {
        T t = findByCode(code);
        baseRepository.delete(t);
    }
}
