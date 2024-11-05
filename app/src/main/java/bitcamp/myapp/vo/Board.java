package bitcamp.myapp.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Board implements Serializable {

  private static final long serialVersionUID = 1L;

  private int no;
  private String title;
  private String content;
  private User writer;
  private Date createdDate;
  private int viewCount;
  private List<AttachedFile> attachedFiles;
}
