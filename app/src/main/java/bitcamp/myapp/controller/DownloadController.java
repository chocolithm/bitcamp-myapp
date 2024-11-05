package bitcamp.myapp.controller;

import bitcamp.myapp.service.BoardService;
import bitcamp.myapp.service.StorageService;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.io.OutputStream;

@RequiredArgsConstructor
@Controller
public class DownloadController {

  private final BoardService boardService;
  private final StorageService storageService;

  @GetMapping("/download")
  public HttpHeaders download(
          String path,
          int fileNo,
          HttpSession session,
          OutputStream out) throws Exception {

    HttpHeaders headers = new HttpHeaders();

    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인 하지 않았습니다.");
    }

    AttachedFile attachedFile = boardService.getAttachedFile(fileNo);

    headers.add("Content-Disposition",
            String.format("attachment; filename=\"%s\"", attachedFile.getOriginFilename())
    );

    storageService.download(path + "/" + attachedFile.getFilename(), out);

    return headers;
  }

}
