package team404.project.controller.rest;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team404.project.model.dto.MessageDto;
import team404.project.service.interfaces.ChatMessageHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class MessagesRestController {

    @Autowired
    ChatMessageHandler chatMessageHandler;
    @Autowired
    Map<String, List<MessageDto>> messages;

    @PostMapping("/messages")
    public ResponseEntity<Object> receiveMessage(@RequestBody MessageDto message) {
        chatMessageHandler.handleMessage(message);
        return ResponseEntity.ok().build();
    }

    @SneakyThrows
    @GetMapping("/messages")
    public ResponseEntity<List<MessageDto>> getMessagesForPage(@RequestParam("username") String username) {
        if (!messages.containsKey(username)) {
            messages.put(username, new ArrayList<>());
        }
        synchronized (messages.get(username)) {
            if (messages.get(username).isEmpty()) {
                messages.get(username).wait();
            }
        }
        List<MessageDto> response = new ArrayList<>(messages.get(username));
        messages.get(username).clear();
        return ResponseEntity.ok(response);
    }
}
