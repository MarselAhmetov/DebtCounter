package team404.project.repository.interfaces;


import team404.project.model.AccountStatus;
import team404.project.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User getByEmail(String email);
    User getByUsername(String username);
    void setStatus(Integer id, AccountStatus accountStatus);
}
