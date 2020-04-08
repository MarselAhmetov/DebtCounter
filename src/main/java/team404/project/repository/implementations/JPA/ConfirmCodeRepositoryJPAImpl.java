package team404.project.repository.implementations.JPA;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team404.project.model.ConfirmCode;
import team404.project.repository.interfaces.ConfirmCodeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ConfirmCodeRepositoryJPAImpl implements ConfirmCodeRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ConfirmCode getByCode(String code) {
        Query query = entityManager.createQuery("from ConfirmCode c where c.code = :code", ConfirmCode.class);
        query.setParameter("code", code);
        return (ConfirmCode) query.getSingleResult();
    }

    @Override
    public void save(ConfirmCode code) {
        entityManager.persist(code);
    }

    @Override
    public void saveAll(List<ConfirmCode> var1) {

    }

    @Override
    public Optional<ConfirmCode> findById(Integer var1) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer var1) {
        return false;
    }

    @Override
    public Iterable<ConfirmCode> findAll() {
        return null;
    }

    @Override
    public Iterable<ConfirmCode> findAllById(Iterable<Integer> var1) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer var1) {

    }

    @Override
    public void delete(ConfirmCode code) {
        entityManager.remove(entityManager.contains(code) ? code : entityManager.merge(code));
    }

    @Override
    public void deleteAll(Iterable<? extends ConfirmCode> var1) {

    }

    @Override
    public void deleteAll() {

    }
}
