package authserver.controller;

import authserver.entity.Otp;
import authserver.entity.User;
import authserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/user/add")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PostMapping("/user/auth")
    public void authUser(@RequestBody User user) {
        userService.auth(user);
    }

    @PostMapping("/otp/check")
    public void otpCheck(@RequestBody Otp otp) {
        userService.checkOtp(otp);
    }

}
