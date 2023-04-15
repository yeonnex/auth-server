package authserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter
public class User {
    @Id
    private String username;
    private String password;

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public void checkPassword(String reqPassword, PasswordEncoder passwordEncoder) {
        if (! (passwordEncoder.matches(reqPassword, this.password))) {
            throw new IllegalArgumentException("Bad credentials.");
        }
    }
}
