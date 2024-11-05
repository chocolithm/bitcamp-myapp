package bitcamp.myapp.controller;

import bitcamp.myapp.service.ProjectService;
import bitcamp.myapp.service.UserService;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/project")
@SessionAttributes("project")
public class ProjectController {

  private final ProjectService projectService;
  private final UserService userService;

  @GetMapping("form1")
  public String form1() {
    return "project/form1";
  }

  @PostMapping("form2")
  public ModelAndView form2(Project project) throws Exception {
    List<User> users = userService.list();

    ModelAndView mv = new ModelAndView();
    mv.addObject("project", project);
    mv.addObject("users", users);
    mv.setViewName("project/form2");
    return mv;
  }

  @PostMapping("form3")
  public String form3(
          int[] memberNos,
          @ModelAttribute Project project) throws Exception {

    if (memberNos.length > 0) {
      ArrayList<User> members = new ArrayList<>();
      for (int memberNo : memberNos) {
        User user = userService.get(memberNo);
        members.add(user);
      }
      project.setMembers(members);
    }

    return "project/form3";
  }

  @PostMapping("add")
  public String add(@ModelAttribute Project project, SessionStatus sessionStatus) throws Exception {
    projectService.add(project);
    sessionStatus.setComplete(); // 작업이 끝났으니 세션에 임시 보관했던 값을 제거하라. @SessionAttributes 에 등록된 이름의 값.
    return "redirect:list";
  }

  @GetMapping("list")
  public ModelAndView list() throws Exception {
    List<Project> list = projectService.list();

    ModelAndView mv = new ModelAndView();
    mv.addObject("list", list);
    mv.setViewName("project/list");
    return mv;
  }

  @GetMapping("view")
  public ModelAndView view(int no) throws Exception {
    ModelAndView mv = new ModelAndView();

    Project project = projectService.get(no);
    mv.addObject("project", project);

    List<User> users = userService.list();
    mv.addObject("users", users);
    mv.setViewName("project/view");
    return mv;
  }

  @PostMapping("update")
  public String update(
          Project project,
          int[] memberNos) throws Exception {

    if (memberNos.length > 0) {
      ArrayList<User> members = new ArrayList<>();
      for (int memberNo : memberNos) {
        members.add(new User(memberNo));
      }
      project.setMembers(members);
    }

    if (!projectService.update(project)) {
      throw new Exception("없는 프로젝트입니다!");
    }
    return "redirect:list";
  }

  @GetMapping("delete")
  public String delete(int no) throws Exception {
    if (projectService.delete(no)) {
      return "redirect:list";
    } else {
      throw new Exception("없는 프로젝트입니다.");
    }
  }
}
