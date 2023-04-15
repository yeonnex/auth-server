package authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // 애플리케이션의 엔드포인트를 직접 실행할 수 있게 CSRF 비활성화
        httpSecurity.csrf().disable();

        // TODO 비즈니스논리서버와 본 애플리케이션 서버(인증서버) 통신시 요청 보안 검증 필요.
        //      -  대칭키 or 비대칭키 사용하여 구현하기
        httpSecurity.authorizeHttpRequests().anyRequest().permitAll();

        return httpSecurity.build();
    }
}
