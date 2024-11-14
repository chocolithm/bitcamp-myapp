package bitcamp.myapp.security04;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

// 사용자가 입력한 암호와 DB에 저장된 암호를 비교하는 일을 수행
public class SimplePasswordEncoder implements PasswordEncoder {

  private static final Log log = LogFactory.getLog(SimplePasswordEncoder.class);

  @Override
  public String encode(CharSequence rawPassword) {
    // 사용자가 입력한 암호를 암호화하여 리턴한다.
    // 현재는 암호화하여 리턴하지 않고 암호화하기 전 상태 그대로 리턴한다.
    return rawPassword.toString();
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    log.debug("입력 암호와 DB 암호가 같은지 비교한다.");
    // DB에 보관된 암호와 사용자가 입력한 암호가 같은지를 비교하여 리턴한다.
    // 현재는 암호화를 따로 하지 않기 때문에 원래 암호 그대로 비교한다.
    log.debug("입력 암호(인코딩 전) ===> " + rawPassword);
    log.debug("입력 암호(인코딩 후) ===> " + this.encode(rawPassword));
    log.debug("DB에서 가져온 암호 ===> " + encodedPassword);
    return encodedPassword.equals(this.encode(rawPassword));
  }
}
