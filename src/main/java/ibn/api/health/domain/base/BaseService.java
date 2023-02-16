package ibn.api.health.domain.base;

import java.util.List;

public interface BaseService<T extends BaseDomain> {

    List<T> findAll();
    T findById(Long id);
    T findByCode(String code);
    T save(T entity);
    void deleteById(Long id);
    void deleteByCode(String code);
}
