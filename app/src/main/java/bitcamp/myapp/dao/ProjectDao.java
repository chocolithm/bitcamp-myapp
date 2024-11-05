package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectDao {

  boolean insert(Project project) throws Exception;

  List<Project> list() throws Exception;

  Project findBy(int no) throws Exception;

  boolean update(Project project) throws Exception;

  boolean delete(int no) throws Exception;

  boolean insertMembers(@Param("projectNo") int projectNo, @Param("members") List<User> members) throws Exception;

  List<User> getMembers(int projectNo) throws Exception;

  boolean deleteMembers(int projectNo) throws Exception;
}
