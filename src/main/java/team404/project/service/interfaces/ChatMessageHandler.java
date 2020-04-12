package team404.project.service.interfaces;

import team404.project.model.ChatMessage;
import team404.project.model.dto.MessageDto;

public interface ChatMessageHandler {
    void handleMessage(MessageDto message);
}
