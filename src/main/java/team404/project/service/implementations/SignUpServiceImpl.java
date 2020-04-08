package team404.project.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team404.project.model.*;
import team404.project.model.dto.SignUpDto;
import team404.project.repository.interfaces.ConfirmCodeRepository;
import team404.project.repository.interfaces.UserRepository;
import team404.project.service.interfaces.MailSenderService;
import team404.project.service.interfaces.SignUpService;
import team404.project.service.interfaces.TemplateProcessor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    @Qualifier("userRepositoryJPAImpl")
    UserRepository userRepository;
    @Autowired
    ConfirmCodeRepository confirmCodeRepository;
    @Autowired
    MailSenderService mailSenderService;
    @Autowired
    TemplateProcessor templateProcessor;

    public void signUp(SignUpDto signUpDto) {
        User user = User.builder()
                .email(signUpDto.getEmail())
                .username(signUpDto.getUsername())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .role(Role.USER)
                .accountStatus(AccountStatus.NOT_CONFIRMED)
                .build();
        userRepository.save(user);

        ConfirmCode confirmCode = ConfirmCode.builder()
                .user(userRepository.getByUsername(signUpDto.getUsername()))
                .code(UUID.randomUUID().toString())
                .build();
        confirmCodeRepository.save(confirmCode);

        Map<String, Object> input = new HashMap<>();
        input.put("code", confirmCode.getCode());
        MailMessage mailMessage = MailMessage.builder()
                .mailTo(user.getEmail())
                .subject("Account confirmation")
                .text(templateProcessor.processTemplate("email.ftl", input))
                .build();
        mailSenderService.sendMail(mailMessage);
    }
}
