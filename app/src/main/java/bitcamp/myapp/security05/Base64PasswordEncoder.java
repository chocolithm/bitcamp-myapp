package bitcamp.myapp.security05;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

// 사용자가 입력한 암호와 DB에 저장된 암호를 비교하는 일을 수행
public class Base64PasswordEncoder implements PasswordEncoder {

  private static final Log log = LogFactory.getLog(Base64PasswordEncoder.class);

  @Override
  public String encode(CharSequence rawPassword) {
    // 입력된 암호를 Base64 포맷으로 인코딩 하여 리턴한다.
    return Base64.getEncoder().encodeToString(
            rawPassword.toString().getBytes(StandardCharsets.UTF_8));
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    log.debug("입력 암호와 DB 암호가 같은지 비교한다.");
    log.debug("입력 암호(인코딩 전) ===> " + rawPassword);
    log.debug("입력 암호(인코딩 후) ===> " + this.encode(rawPassword));
    log.debug("DB에서 가져온 암호 ===> " + encodedPassword);
    return encodedPassword.equals(this.encode(rawPassword));
  }
}
