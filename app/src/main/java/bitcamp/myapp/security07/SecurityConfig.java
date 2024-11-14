package bitcamp.myapp.security07;

import bitcamp.myapp.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {

  private static final Log log = LogFactory.getLog(SecurityConfig.class);

  public SecurityConfig() {
    log.debug("SecurityConfig 객체 생성됨!");
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .authorizeHttpRequests((authorize) -> authorize
                    .anyRequest().authenticated()
            )
            .formLogin(formLoginConfigurer -> {
              formLoginConfigurer
                      .loginPage("/auth/form") // 로그인 폼을 제공하는 URL
                      .loginProcessingUrl("/auth/login") // 클라이언트가 로그인을 하기 위해 요청하는 URL (페이지 컨트롤러와 상관없다)
                      .usernameParameter("email") // 로그인 수행할 때 사용할 사용자 아이디 또는 이메일(principal) 파라미터 명
                      .passwordParameter("password") // 로그인 수행할 때 사용할 사용자 암호(credential) 파라미터 명
                      .successForwardUrl("/auth/success") // 로그인 성공 후 포워딩 할 URL
                      .permitAll(); // 모든 권한 부여
            });
    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService(UserService userService) {
    return new DbUserDetailsService(userService);
  }

  // Spring Security에서 제공하는 암호 인코더 사용하기
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder() {
      @Override
      public boolean matches(CharSequence rawPassword, String encodedPassword) {
        log.debug("입력 암호(인코딩 전) ===> " + rawPassword);
        log.debug("입력 암호(인코딩 후) ===> " + this.encode(rawPassword));
        log.debug("DB에서 가져온 암호 ===> " + encodedPassword);
        return super.matches(rawPassword, encodedPassword);
      }
    };
  }
}
