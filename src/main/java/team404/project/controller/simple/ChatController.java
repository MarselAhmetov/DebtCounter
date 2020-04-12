package team404.project.controller.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import team404.project.model.ChatMessage;
import team404.project.model.details.UserDetailsImpl;
import team404.project.repository.interfaces.ChatMessageRepository;
import team404.project.service.interfaces.ChatMessageService;
import team404.project.service.interfaces.UserService;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    ChatMessageService chatMessageService;
    @Autowired
    UserService userService;

    @GetMapping("/chat")
    public String getChatPage(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<ChatMessage> messages = chatMessageService.getByUser(userDetails.getUser());
        model.addAttribute("messages", messages);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("currentUser", userDetails.getUser());
        return "chat";
    }
}
