package team404.project.repository.interfaces;

import team404.project.model.ChatMessage;
import team404.project.model.User;

import java.util.List;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, Integer> {
    List<ChatMessage> getMessagesByUser(User user);
}
