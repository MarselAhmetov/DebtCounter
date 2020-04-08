package team404.project.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.FriendRequest;
import team404.project.model.MailMessage;
import team404.project.model.User;
import team404.project.model.details.UserDetailsImpl;
import team404.project.model.dto.FriendRequestDto;
import team404.project.service.interfaces.FriendRequestService;
import team404.project.service.interfaces.MailSenderService;
import team404.project.service.interfaces.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class FriendsRestController {

    @Autowired
    UserService userService;
    @Autowired
    FriendRequestService friendRequestService;
    @Autowired
    MailSenderService mailSenderService;

    @GetMapping("/friends")
    public ResponseEntity<List<User>> getFriendsPage(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<User> response = userService.getById(userDetails.getUser().getId()).getFriends();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/friends")
    public ResponseEntity<Object> addFriend(HttpServletResponse httpServletResponse, FriendRequestDto friendRequestDto, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        User userFromDB;
        if ((userFromDB = userService.getUserByEmail(friendRequestDto.getEmail())) != null && !userDetails.getUser().getEmail().equals(friendRequestDto.getEmail())) {
            friendRequestService.create(FriendRequest.builder()
                    .receiver(userService.getUserByEmail(friendRequestDto.getEmail()))
                    .sender(userService.getUserByEmail(userDetails.getUser().getEmail()))
                    .message(friendRequestDto.getMessage())
                    .date(LocalDate.now())
                    .build());
        } else if (userFromDB == null) {
            mailSenderService.sendMail(MailMessage.builder()
                    .mailTo(friendRequestDto.getEmail())
                    .subject("Invitation")
                    .text("Your friend invites you in our service. His message: \n" + friendRequestDto.getMessage())
                    .build());
        } else {
            try {
                httpServletResponse.sendRedirect("/friends?error");
            } catch (IOException e) {
                System.out.println();
                throw new IllegalArgumentException(e);
            }
            return ResponseEntity.ok().build();
        }
        try {
            httpServletResponse.sendRedirect("/friends");
        } catch (IOException e) {
            System.out.println();
            throw new IllegalArgumentException(e);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/friendRequest")
    public ResponseEntity<Object> friendRequest(HttpServletResponse response, Authentication authentication, @RequestParam("request") Integer requestId, @RequestParam("status") String status, @RequestParam("sender") Integer senderId) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User currentUser = userService.getById(userDetails.getUser().getId());
        User sender = userService.getById(senderId);
        sender.getFriends().add(currentUser);
        currentUser.getFriends().add(sender);

        userService.save(currentUser);
        userService.save(sender);
        friendRequestService.deleteById(requestId);
        try {
            response.sendRedirect("/profile");
        } catch (IOException e) {
            System.out.println();
            throw new IllegalArgumentException(e);
        }
        return ResponseEntity.ok().build();
    }
}
