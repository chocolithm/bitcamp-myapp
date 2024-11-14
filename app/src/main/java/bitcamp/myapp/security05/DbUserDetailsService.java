package bitcamp.myapp.security05;

import bitcamp.myapp.service.UserService;
import bitcamp.myapp.vo.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DbUserDetailsService implements UserDetailsService {

  private static final Log log = LogFactory.getLog(DbUserDetailsService.class);

  UserService userService;

  public DbUserDetailsService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    try {
      User user = userService.get(email);
      if (user == null) {
        throw new UsernameNotFoundException("해당 사용자가 존재하지 않습니다.");
      }
      log.debug(user);

      // => Spring Security에서 제공하는 기본 암호 인코더를 사용한다.
      // => DB에서 암호를 가져 오면 이 기본 인코더를 사용하여 암호화 해서 보관한다.
      // return org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()

      // => Spring Security에서 제공하는 기본 암호 인코더를 사용하지 않는다.
      // => DB에서 가져온 암호는 그대로 보관한다.
      return org.springframework.security.core.userdetails.User.builder()
              .username(user.getEmail())
              .password(user.getPassword())
              .roles("USER")
              .build();

    } catch (Exception e) {
      throw new UsernameNotFoundException("해당 사용자가 존재하지 않습니다.");
    }
  }
}
