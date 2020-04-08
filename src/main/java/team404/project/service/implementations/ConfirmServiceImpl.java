package team404.project.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import team404.project.model.AccountStatus;
import team404.project.model.ConfirmCode;
import team404.project.model.User;
import team404.project.repository.interfaces.ConfirmCodeRepository;
import team404.project.repository.interfaces.UserRepository;
import team404.project.service.interfaces.ConfirmService;

@Service
public class ConfirmServiceImpl implements ConfirmService {

    @Autowired
    ConfirmCodeRepository confirmCodeRepository;
    @Autowired
    @Qualifier("userRepositoryJPAImpl")
    UserRepository userRepository;

    public void confirm(String code) {
        ConfirmCode confirmCode = confirmCodeRepository.getByCode(code);
        User user;
        if (confirmCode != null) {
            user = confirmCode.getUser();
            user.setAccountStatus(AccountStatus.CONFIRMED);
            userRepository.setStatus(user.getId(), AccountStatus.CONFIRMED);
            confirmCodeRepository.delete(confirmCode);
        }
    }
}
