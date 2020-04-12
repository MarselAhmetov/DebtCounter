package team404.project.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team404.project.model.ChatMessage;
import team404.project.model.dto.MessageDto;
import team404.project.service.interfaces.ChatMessageHandler;
import team404.project.service.interfaces.ChatMessageService;
import team404.project.service.interfaces.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ChatMessageHandlerImpl implements ChatMessageHandler {

    @Autowired
    Map<String, List<MessageDto>> messages;
    @Autowired
    ChatMessageService chatMessageService;
    @Autowired
    UserService userService;

    @Override
    public void handleMessage(MessageDto message) {

        chatMessageService.save(ChatMessage.builder()
                .text(message.getText())
                .dateTime(LocalDateTime.now())
                .receiver(userService.getByUsername(message.getReceiver()))
                .sender(userService.getByUsername(message.getSender()))
                .build());

        synchronized (messages.get(message.getReceiver())) {
            messages.get(message.getReceiver()).add(message);
            messages.get(message.getReceiver()).notifyAll();
        }
        synchronized (messages.get(message.getSender())) {
            messages.get(message.getSender()).add(message);
            messages.get(message.getSender()).notifyAll();
        }
    }
}
