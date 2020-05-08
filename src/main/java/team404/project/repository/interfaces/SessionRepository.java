package team404.project.repository.interfaces;

import team404.project.model.Session;

public interface SessionRepository extends CrudRepository<Session, Integer> {
    Session getBySessionId(String sessionId);
    void deleteBySessionId(String sessionId);
    void deleteByUsername(String username);
}
