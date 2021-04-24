package user;

import com.github.javafaker.Bool;
import com.github.javafaker.Options;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {

    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private Gender gender;
    private LocalDate birthDate;
    private boolean enabled;

    public User(String username, String password, String name, String email, Options options, Date birthday, Bool bool) {
        this.username = username;
        this.password = DigestUtils.md5Hex(password);
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
        this.enabled = enabled;
    }

    public static enum Gender {
        FEMALE,
        MALE
    }



}
