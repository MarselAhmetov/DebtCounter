package team404.project.repository.implementations.JDBC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import team404.project.model.AccountStatus;
import team404.project.model.Role;
import team404.project.model.User;
import team404.project.repository.interfaces.UserRepository;

import java.util.List;
import java.util.Optional;

@Repository("userRepositoryJDBCImpl")
public class UserRepositoryJDBCImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_UPDATE_USER = "update project2.user set account_status = ? where project2.user.id = ?;";
    private static final String SQL_INSERT_USER = "insert into project2.user (account_status, email, password, role, username) values (?,?,?,?,?)";
    private static final String SQL_SELECT_BY_EMAIL = "select * from project2.user where user.email = ?";
    private static final String SQL_SELECT_BY_USERNAME = "select * from project2.user where user.username = ?";
    public RowMapper<User> userRowMapper = (row, rowNum) ->
            User.builder()
                    .id(row.getInt("user.id"))
                    .email(row.getString("user.email"))
                    .username(row.getString("user.username"))
                    .password(row.getString("user.password"))
                    .role(Role.valueOf(row.getString("user.role")))
                    .accountStatus(AccountStatus.valueOf(row.getString("user.account_status")))
                    .build();

    @Override
    public User getByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, new Object[]{email}, userRowMapper);
    }

    @Override
    public User getByUsername(String username) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_USERNAME, new Object[]{username}, userRowMapper);
    }

    @Override
    public void setStatus(Integer id, AccountStatus accountStatus) {
        jdbcTemplate.update(SQL_UPDATE_USER, accountStatus.toString(), id);
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update(SQL_INSERT_USER, user.getAccountStatus().toString(), user.getEmail(), user.getPassword(), user.getRole().toString(), user.getUsername());
    }

    @Override
    public void saveAll(List<User> users) {

    }

    @Override
    public Optional<User> findById(Integer var1) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer var1) {
        return false;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Iterable<User> findAllById(Iterable<Integer> var1) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer var1) {

    }

    @Override
    public void delete(User var1) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> var1) {

    }

    @Override
    public void deleteAll() {

    }
}
