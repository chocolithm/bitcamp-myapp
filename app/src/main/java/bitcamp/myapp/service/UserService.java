package bitcamp.myapp.service;

import bitcamp.myapp.vo.User;

import java.util.List;

public interface UserService {

  void add(User user) throws Exception;

  List<User> list() throws Exception;

  User get(int userNo) throws Exception;

  User exists(String email, String password) throws Exception;

  boolean update(User user) throws Exception;

  boolean delete(int userNo) throws Exception;
}
