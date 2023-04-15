package authserver.service;

import authserver.entity.Otp;
import authserver.entity.User;
import authserver.repository.OtpRepository;
import authserver.repository.UserRepository;
import authserver.util.GenerateCodeUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final OtpRepository otpRepository;
    private final PasswordEncoder passwordEncoder;
    /**
     * DB 에 사용자를 저장한다.
     * @param user
     */
    @Transactional
    public void addUser(User user) {
        user.encodePassword(passwordEncoder);
        userRepository.save(user);
    }

    @Transactional
    public void auth(User user) {
        User findUser = userRepository.findByUsername(user.getUsername()).orElseThrow(EntityNotFoundException::new);
        // 비밀번호 확인
        findUser.checkPassword(user.getPassword(), passwordEncoder);
        // 비밀번호가 맞다면 OTP 생성하여 저장
        renewOtp(user);
    }

    @Transactional
    public void checkOtp(Otp otp) {
        // TODO 현재 예외 발생시 클라이언트 응답으로 500 에러 내려감. 컨트롤러 어드바이스로 403 FORBIDDEN 에러 내려주기
        Otp findOtp = otpRepository.findByUsername(otp.getUsername()).orElseThrow(EntityNotFoundException::new);
        findOtp.checkOtp(otp.getCode());
    }

    private void renewOtp(User user) {
        // OTP 를 위한 임의의 수 생성
        String otpCode = GenerateCodeUtil.generateCode();
        // 유저 이름으로 otp 엔티티 조회
        otpRepository.findByUsername(user.getUsername())
                .ifPresentOrElse(otp -> otp.renewOptCode(otpCode), () -> {
                    Otp otp = new Otp(user.getUsername(), otpCode);
                    otpRepository.save(otp);
                });
    }
}
