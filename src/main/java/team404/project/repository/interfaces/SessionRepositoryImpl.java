package team404.project.repository.interfaces;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team404.project.model.ChatMessage;
import team404.project.model.Session;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class SessionRepositoryImpl implements SessionRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Session getBySessionId(String sessionId) {
        try {
            Query query = entityManager.createQuery("from Session s where s.sessionId = :sessionId");
            query.setParameter("sessionId", sessionId);
            return (Session) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void deleteBySessionId(String sessionId) {
        Query query = entityManager.createQuery("delete Session s where s.sessionId = :sessionId");
        query.setParameter("sessionId", sessionId);
        query.executeUpdate();
    }

    @Override
    public void deleteByUsername(String username) {
        Query query = entityManager.createQuery("delete Session s where s.username = :username");
        query.setParameter("username", username);
        query.executeUpdate();
    }

    @Override
    public void save(Session var1) {
        entityManager.persist(var1);
    }

    @Override
    public void saveAll(List<Session> var1) {

    }

    @Override
    public Optional<Session> findById(Integer var1) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer var1) {
        return false;
    }

    @Override
    public Iterable<Session> findAll() {
        return null;
    }

    @Override
    public Iterable<Session> findAllById(Iterable<Integer> var1) {
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
    public void delete(Session var1) {
        entityManager.remove(var1);
    }

    @Override
    public void deleteAll(Iterable<? extends Session> var1) {

    }

    @Override
    public void deleteAll() {

    }
}
