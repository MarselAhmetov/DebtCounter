package team404.project.service.interfaces;

import team404.project.model.ChatMessage;
import team404.project.model.User;

import java.util.List;

public interface ChatMessageService {
    void save(ChatMessage chatMessage);
    List<ChatMessage> getByUser(User user);
}
