package bitcamp.myapp.security08;

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

      CustomUserDetails userDetails = new CustomUserDetails();
      userDetails.setNo(user.getNo());
      userDetails.setName(user.getName());
      userDetails.setEmail(user.getEmail());
      userDetails.setPassword(user.getPassword());
      userDetails.setTel(user.getTel());
      userDetails.setPhoto(user.getPhoto());

      return userDetails;

    } catch (Exception e) {
      throw new UsernameNotFoundException("해당 사용자가 존재하지 않습니다.");
    }
  }
}
