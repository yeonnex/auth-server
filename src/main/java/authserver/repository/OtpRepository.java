package authserver.repository;

import authserver.entity.Otp;
import authserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, String> {
    Optional<Otp> findByUsername(String username);
}
