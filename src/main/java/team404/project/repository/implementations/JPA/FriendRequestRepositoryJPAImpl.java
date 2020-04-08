package team404.project.repository.implementations.JPA;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team404.project.model.FriendRequest;
import team404.project.model.User;
import team404.project.repository.interfaces.FriendRequestRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class FriendRequestRepositoryJPAImpl implements FriendRequestRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<FriendRequest> getByReceiver(User receiver) {
        Query query = entityManager.createQuery("from FriendRequest fr where fr.receiver = :receiver");
        query.setParameter("receiver", receiver);
        return query.getResultList();
    }

    @Override
    public void save(FriendRequest friendRequest) {
        entityManager.persist(friendRequest);
    }

    @Override
    public void saveAll(List<FriendRequest> var1) {

    }

    @Override
    public Optional<FriendRequest> findById(Integer var1) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer var1) {
        return false;
    }

    @Override
    public Iterable<FriendRequest> findAll() {
        return null;
    }

    @Override
    public Iterable<FriendRequest> findAllById(Iterable<Integer> var1) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer id) {
        Query query = entityManager.createQuery("delete from FriendRequest fr where fr.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void delete(FriendRequest var1) {

    }

    @Override
    public void deleteAll(Iterable<? extends FriendRequest> var1) {

    }

    @Override
    public void deleteAll() {

    }
}
