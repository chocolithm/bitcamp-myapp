package bitcamp.myapp.service;

import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;

import java.util.List;

public interface BoardService {

  void add(Board board) throws Exception;

  List<Board> list(int pageNo, int pageSize) throws Exception;

  Board get(int boardNo) throws Exception;

  void increaseViewCount(int boardNo) throws Exception;

  int countAll() throws Exception;

  boolean update(Board board) throws Exception;

  void delete(int boardNo) throws Exception;

  AttachedFile getAttachedFile(int fileNo) throws Exception;

  boolean deleteAttachedFile(int fileNo) throws Exception;

}

