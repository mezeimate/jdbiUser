package user;
import ex8.LegoSet;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(User.class)
public interface UserDAO {

    @SqlUpdate("""
        CREATE TABLE users (
            id IDENTITY PRIMARY KEY,
            username VARCHAR NOT NULL,
            password VARCHAR NOT NULL,
            name VARCHAR NOT NULL,
            email VARCHAR NOT NULL,
            gender ENUM NOT NULL,
            birthDate DATE NOT NULL,
            enabled BOOLEAN NOT NULL
        )
        """
    )
    void createTab();

    @SqlUpdate("INSERT INTO users VALUES (:username, :password, :name, :email, :gender, :birthDate, :enabled)")
    int insertUser(@BindBean User user);

    @SqlUpdate("SELECT * FROM user WHERE id = :id")
    Optional<User> loadUserId(@Bind("id") String id);

    @SqlUpdate("SELECT * FROM user WHERE name = :name")
    Optional<User> loadUser(@Bind("name") String name);

    @SqlUpdate("DELETE FROM user WHERE username = :username AND password = :password AND name = :name AND email = :email AND gender = :gender AND birthDate = :birthDate AND enabled = :enabled")
    void delete(@Bind("username") String username,
                @Bind("password") String password,
                @Bind("name") String name,
                @Bind("email") String email,
                @Bind("gender") Enum gender,
                @Bind("birthDate") LocalDate birthDate,
                @Bind("enabled") boolean enabled );

    @SqlUpdate("SELECT * FROM user")
    List<User> addList();
}
