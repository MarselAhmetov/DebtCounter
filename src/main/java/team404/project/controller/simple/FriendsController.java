package team404.project.controller.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.FriendRequest;
import team404.project.model.MailMessage;
import team404.project.model.User;
import team404.project.model.details.UserDetailsImpl;
import team404.project.model.dto.FriendRequestDto;
import team404.project.service.interfaces.FriendRequestService;
import team404.project.service.interfaces.MailSenderService;
import team404.project.service.interfaces.TemplateProcessor;
import team404.project.service.interfaces.UserService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class FriendsController {

    @Autowired
    UserService userService;
    @Autowired
    FriendRequestService friendRequestService;
    @Autowired
    MailSenderService mailSenderService;
    @Autowired
    TemplateProcessor templateProcessor;

    @GetMapping("/friends")
    public ModelAndView getFriendsPage(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ModelAndView modelAndView = new ModelAndView("friends");
        modelAndView.addObject("friends", userService.getById(userDetails.getUser().getId()).getFriends());
        return modelAndView;
    }

    @PostMapping("/friends")
    public ModelAndView addFriend(FriendRequestDto friendRequestDto, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        User userFromDB;
        // TODO: 07.04.2020 Вынести логику
        if ((userFromDB = userService.getUserByEmail(friendRequestDto.getEmail())) != null && !userDetails.getUser().getEmail().equals(friendRequestDto.getEmail())) {
            friendRequestService.create(FriendRequest.builder()
                    .receiver(userService.getUserByEmail(friendRequestDto.getEmail()))
                    .sender(userService.getUserByEmail(userDetails.getUser().getEmail()))
                    .message(friendRequestDto.getMessage())
                    .date(LocalDate.now())
                    .build());
        } else if (userFromDB == null) {
            Map<String, Object> input = new HashMap<>();
            input.put("message", friendRequestDto.getMessage());
            mailSenderService.sendMail(MailMessage.builder()
            .mailTo(friendRequestDto.getEmail())
            .subject("Invitation")
            .text(templateProcessor.processTemplate("invite.ftl", input))
            .build());
        } else {
            modelAndView.setViewName("redirect:/friends?error");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/friends");
        return modelAndView;
    }

    @PostMapping("/friendRequest")
    public ModelAndView friendRequest(Authentication authentication, @RequestParam("request") Integer requestId, @RequestParam("status") String status, @RequestParam("sender") Integer senderId) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ModelAndView modelAndView = new ModelAndView();

        User currentUser = userService.getById(userDetails.getUser().getId());
        User sender = userService.getById(senderId);
        sender.getFriends().add(currentUser);
        currentUser.getFriends().add(sender);

        userService.save(currentUser);
        userService.save(sender);
        friendRequestService.deleteById(requestId);
        modelAndView.setViewName("redirect:/profile");
        return modelAndView;
    }
}
