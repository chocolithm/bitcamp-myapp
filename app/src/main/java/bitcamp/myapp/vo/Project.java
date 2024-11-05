package bitcamp.myapp.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Data
public class Project implements Serializable {

  private static final long serialVersionUID = 1L;

  private int no;
  private String title;
  private String description;
  private Date startDate;
  private Date endDate;
  private List<User> members;
}
