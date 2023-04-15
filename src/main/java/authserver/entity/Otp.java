package authserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Otp {
    @Id
    private String username;
    private String code;

    public void renewOptCode(String otpCode) {
        this.code = otpCode;
    }

    public void checkOtp(String code) {
        if (! (Objects.equals(code, this.code))) throw new IllegalArgumentException("Bad credentials in code.");
    }
}
