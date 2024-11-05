package bitcamp.myapp.vo;

import lombok.Data;

@Data
public class AttachedFile {

  private int fileNo;
  private String filename;
  private String originFilename;
  private int boardNo;
}
