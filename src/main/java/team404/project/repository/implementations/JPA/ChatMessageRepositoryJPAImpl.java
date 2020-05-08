package team404.project.repository.implementations.JPA;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team404.project.model.ChatMessage;
import team404.project.model.User;
import team404.project.repository.interfaces.ChatMessageRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ChatMessageRepositoryJPAImpl implements ChatMessageRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ChatMessage> getMessagesByUser(User user) {
        Query query = entityManager.createQuery("from ChatMessage m where m.receiver = :user or m.sender = :user order by dateTime desc");
        query.setParameter("user", user);
        return (List<ChatMessage>) query.getResultList();
    }

    @Override
    public void save(ChatMessage chatMessage) {
        entityManager.persist(chatMessage);
    }

    @Override
    public void saveAll(List<ChatMessage> var1) {

    }

    @Override
    public Optional<ChatMessage> findById(Integer var1) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer var1) {
        return false;
    }

    @Override
    public Iterable<ChatMessage> findAll() {
        return null;
    }

    @Override
    public Iterable<ChatMessage> findAllById(Iterable<Integer> var1) {
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
    public void delete(ChatMessage var1) {

    }

    @Override
    public void deleteAll(Iterable<? extends ChatMessage> var1) {

    }

    @Override
    public void deleteAll() {

    }
}
