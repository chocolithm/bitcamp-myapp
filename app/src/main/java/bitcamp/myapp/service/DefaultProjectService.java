package bitcamp.myapp.service;

import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DefaultProjectService implements ProjectService {

  private final ProjectDao projectDao;

  @Transactional
  public void add(Project project) throws Exception {
    projectDao.insert(project);

    if (project.getMembers() != null && project.getMembers().size() > 0) {
      projectDao.insertMembers(project.getNo(), project.getMembers());
    }
  }

  public List<Project> list() throws Exception {
    return projectDao.list();
  }

  public Project get(int projectNo) throws Exception {
    return projectDao.findBy(projectNo);
  }

  @Transactional
  public boolean update(Project project) throws Exception {
    if (!projectDao.update(project)) {
      return false;
    }

    projectDao.deleteMembers(project.getNo());
    if (project.getMembers() != null && project.getMembers().size() > 0) {
      projectDao.insertMembers(project.getNo(), project.getMembers());
    }
    return true;
  }

  @Transactional
  public boolean delete(int projectNo) throws Exception {
    projectDao.deleteMembers(projectNo);
    if (!projectDao.delete(projectNo)) {
      return false;
    }
    return true;
  }
}
