package message;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    public UserRepository(MariaDbDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertUser(String username) {
        jdbcTemplate.update("insert into users(username) values(?)", username);
    }

    public Optional<User> findUserByName(String userName) {
        User result = getUserByName(userName);
        if (result != null) {
            return Optional.of(result);
        }
        return Optional.empty();
    }

    private User getUserByName(String userName) {
        try {
            return jdbcTemplate.queryForObject("select * from users where username=?",
                    (rs, rowNum) -> new User(rs.getLong("id"),
                            rs.getString("username")), userName);
        } catch (EmptyResultDataAccessException era) {
            return null;
        }
    }

}
