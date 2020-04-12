package team404.project.service.interfaces;

import team404.project.model.User;

import java.util.List;

public interface UserService {
    User getUserByEmail(String email);
    void save(User user);
    User getById(Integer id);
    List<User> findAll();
    User getByUsername(String username);
}
