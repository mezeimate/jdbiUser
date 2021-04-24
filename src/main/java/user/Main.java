package user;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import com.github.javafaker.Faker;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class Main {

    public static void main (String[] args){
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:users");
        jdbi.installPlugin(new SqlObjectPlugin());
        var faker = new Faker(new Locale("hu"));
        //User user = User.builder().username(faker.name().username()).password(faker.internet().password()).name(faker.name().fullName()).gender(faker.options()).birthDate(faker.date().birthday()).enabled(faker.bool()).build();

        List<User> felhasznalok = jdbi.withExtension(UserDAO.class, dao -> {
            dao.createTab();
            //dao.insertUser(new User(faker.name().username(), faker.internet().password(), faker.name().fullName(),
            //        faker.internet().emailAddress(), faker.options(), faker.date().birthday(), faker.bool()));

            dao.insertUser(User.builder().username(faker.name().username()).password(faker.internet().password())
                    .name(faker.name().fullName()).gender(faker.options()).birthDate(faker
                    .date().birthday()).enabled(faker.bool()).build());

            dao.insertUser(User.builder().username(faker.name().username()).password(faker.internet().password())
                    .name(faker.name().fullName()).gender(faker.options()).birthDate(faker
                    .date().birthday()).enabled(faker.bool()).build());

            dao.insertUser(User.builder().username(faker.name().username()).password(faker.internet().password())
                    .name(faker.name().fullName()).gender(faker.options()).birthDate(faker
                    .date().birthday()).enabled(faker.bool()).build());

            dao.loadUser("nev");
            dao.loadUserId("2");



            return  dao.addList();
        });
        felhasznalok.forEach(System.out::println);

    }
}
