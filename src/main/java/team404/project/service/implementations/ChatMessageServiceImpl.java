package team404.project.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team404.project.model.ChatMessage;
import team404.project.model.User;
import team404.project.repository.interfaces.ChatMessageRepository;
import team404.project.service.interfaces.ChatMessageService;

import java.util.List;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    ChatMessageRepository chatMessageRepository;

    @Override
    public void save(ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
    }

    @Override
    public List<ChatMessage> getByUser(User user) {
        return chatMessageRepository.getMessagesByUser(user);
    }
}
