package bitcamp.myapp.service;

import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DefaultUserService implements UserService {

  private final UserDao userDao;

  @Transactional
  public void add(User user) throws Exception {
    userDao.insert(user);
  }

  public List<User> list() throws Exception {
    return userDao.list();
  }

  public User get(int userNo) throws Exception {
    return userDao.findBy(userNo);
  }

  public User exists(String email, String password) throws Exception {
    return userDao.findByEmailAndPassword(email, password);
  }

  @Transactional
  public boolean update(User user) throws Exception {
    return userDao.update(user);
  }

  @Transactional
  public boolean delete(int userNo) throws Exception {
    return userDao.delete(userNo);
  }
}
